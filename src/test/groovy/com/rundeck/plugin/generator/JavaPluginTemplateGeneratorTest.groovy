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

import spock.lang.Specification

class JavaPluginTemplateGeneratorTest extends Specification {
    def "Create Template"() {
        when:
        File tmpDir = File.createTempDir()
        JavaPluginTemplateGenerator generator = new JavaPluginTemplateGenerator()
        generator.createTemplate("My Great Plugin","Notification",tmpDir.absolutePath)
        int compileResult = TestUtils.buildGradle(new File(tmpDir,"my-great-plugin"))

        then:
        compileResult == 0
        new File(tmpDir,"/my-great-plugin/build.gradle").exists()
        new File(tmpDir,"/my-great-plugin/icon.png").exists()
        new File(tmpDir,"/my-great-plugin/README.md").exists()
        new File(tmpDir,"/my-great-plugin/src/main/java/com/plugin/mygreatplugin/MyGreatPlugin.java").exists()
        new File(tmpDir,"/my-great-plugin/src/test/groovy/com/plugin/mygreatplugin/MyGreatPluginSpec.groovy").exists()

    }

}
