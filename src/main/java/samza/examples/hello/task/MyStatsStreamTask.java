/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package samza.examples.hello.task;

import java.util.HashMap;
import java.util.Map;

import org.apache.samza.config.Config;
import org.apache.samza.storage.kv.KeyValueStore;
import org.apache.samza.system.IncomingMessageEnvelope;
import org.apache.samza.system.OutgoingMessageEnvelope;
import org.apache.samza.system.SystemStream;
import org.apache.samza.task.InitableTask;
import org.apache.samza.task.MessageCollector;
import org.apache.samza.task.StreamTask;
import org.apache.samza.task.TaskContext;
import org.apache.samza.task.TaskCoordinator;
import org.apache.samza.task.WindowableTask;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import samza.examples.hello.domain.PeopleSkillEvent;

import static samza.examples.hello.system.SkillUtils.hasFidgetSpinningSkills;
import static samza.examples.hello.system.SkillUtils.hasJavaAndBigDataSkills;

public class MyStatsStreamTask implements StreamTask, InitableTask, WindowableTask {
    private static final Logger LOG = LoggerFactory.getLogger(MyStatsStreamTask.class);
    
    private int javaWithBigDataCount = 0;
    private int fidgetCount = 0;
    
    private Map<String, Integer> counts = new HashMap<>();
    private KeyValueStore<String, Integer> store;
    
    public void init(Config config, TaskContext context) {
        this.store = (KeyValueStore<String, Integer>) context.getStore("my-stats");
    }
    
    @Override
    public void process(IncomingMessageEnvelope envelope, MessageCollector collector, TaskCoordinator coordinator) {
        Map<String, Object> editMessage = (Map<String, Object>) envelope.getMessage();
        LOG.info("----message received for stats : " + editMessage);
        
        final PeopleSkillEvent event = new ObjectMapper().convertValue(editMessage, PeopleSkillEvent.class);
        LOG.info(">>> mapped to people skill event >>>>>> : " + event);
    
    
        Integer editsAllTime = store.get("edits-all-time-count");
        if (editsAllTime == null) {
            editsAllTime = 0;
        }
        
        // This increases if a message arrives, otherwise not
        store.put("edits-all-time-count", editsAllTime + 1);
    
        if(hasJavaAndBigDataSkills(event.getSkills())) {
            
            javaWithBigDataCount += 1;
            
        } else if (hasFidgetSpinningSkills(event.getSkills())) {
            
            fidgetCount += 1;
    
        }
        
    }
    
    @Override
    public void window(MessageCollector collector, TaskCoordinator coordinator) {
        
        counts.put("javaAndBigData", javaWithBigDataCount);
        counts.put("fidgetSpinning", fidgetCount);
        
        counts.put("edits-all-time-count", store.get("edits-all-time-count"));
        
        collector.send(new OutgoingMessageEnvelope(new SystemStream("kafka", "my-stats"), counts));
        
        // Reset counts after the windowing.
        javaWithBigDataCount = 0;
        fidgetCount = 0;
        counts = new HashMap<>();
    }
}
