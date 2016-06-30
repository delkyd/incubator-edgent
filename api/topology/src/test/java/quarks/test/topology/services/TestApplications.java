/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/
package quarks.test.topology.services;

import com.google.gson.JsonObject;

import quarks.function.BiConsumer;
import quarks.topology.Topology;
import quarks.topology.services.TopologyBuilder;

/**
 * Some dummy test applications that will be part of this
 * jar including having service provider entries.
 * Used for testing the application service.
 *
 */
public class TestApplications {
        
    private static abstract class App implements TopologyBuilder {

        @Override
        public BiConsumer<Topology, JsonObject> getBuilder() {
            return (t,c) -> t.strings(getName());
        }     
    }
    
    public static class AppOne extends App {
        @Override
        public String getName() {
            return "FirstJarApp";
        }
    }
    public static class AppTwo extends App {
        @Override
        public String getName() {
            return "SecondJarApp";
        }
    }
    public static class AppThree extends App {
        @Override
        public String getName() {
            return "ThirdJarApp";
        }
    }
}
