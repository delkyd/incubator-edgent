#!groovy

/*
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
node('windows-2012-1') {

    currentBuild.result = "SUCCESS"

    echo 'Building Branch: ' + env.BRANCH_NAME

    // Setup the required environment variables.
    env.PATH = "${tool 'Maven 3 (latest)'}\\bin;${env.PATH}"
    dev java8Home = "${tool 'JDK 1.8 (latest)'}"

    // Make sure the feature branches don't change the SNAPSHOTS in Nexus.
    def mavenGoal = "install"
    def mavenLocalRepo = ""
    if(env.BRANCH_NAME == 'develop') {
        mavenGoal = "deploy"
    } else {
        mavenLocalRepo = "-Dmaven.repo.local=..\\.repository"
    }

    try {
        stage 'Checkout'
            echo 'Checking out branch ' + env.BRANCH_NAME
            checkout scm

        stage 'Build'
            echo 'Building'
            sh "${mvnHome}/bin/mvn -U clean ${mavenGoal} site:site ${mavenLocalRepo} -Djava8.home=${java8Home} -Pplatform-android,platform-java7,distribution,toolchain,apache-release"
    }


    catch (err) {
        currentBuild.result = "FAILURE"
/*            mail body: "project build error is here: ${env.BUILD_URL}" ,
            from: 'xxxx@yyyy.com',
            replyTo: 'dev@edgent.apache.org',
            subject: 'Autobuild for Branch ' env.BRANCH_NAME
            to: 'commits@edgent.apache.org'
*/
        throw err
    }

}