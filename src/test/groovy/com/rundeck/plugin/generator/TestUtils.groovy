/*
 * Copyright 2018 Rundeck, Inc. (http://rundeck.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rundeck.plugin.generator


class TestUtils {
    static int buildGradle(File baseDir) {
        ProcessBuilder pb = new ProcessBuilder("gradle", "build")
        pb.directory(baseDir)
        pb.redirectErrorStream(true)  // Redirect error stream to standard output
        Process p = pb.start()
        p.inputStream.eachLine { println it }  // Print each line of output
        p.waitFor()
        return p.exitValue()
    }
}
