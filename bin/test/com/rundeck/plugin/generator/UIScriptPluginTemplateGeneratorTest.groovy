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


class UIScriptPluginTemplateGeneratorTest extends Specification {
    def "Generate UI Script Plugin From Template"() {
        setup:
        File destDir = File.createTempDir()
        println destDir.absolutePath

        when:
        UIScriptPluginTemplateGenerator generator = new UIScriptPluginTemplateGenerator()
        generator.createTemplate("UI Script Plugin","UI",destDir.absolutePath)

        then:
        new File(destDir,"ui-script-plugin/plugin.yaml").exists()
        new File(destDir,"ui-script-plugin/resources/js/ui-script-plugin-init.js").exists()
        new File(destDir,"ui-script-plugin/resources/js/main.js").exists()
        new File(destDir,"ui-script-plugin/resources/css/ui-script-plugin-styles.css").exists()
        new File(destDir,"ui-script-plugin/resources/icon.png").exists()
        new File(destDir,"ui-script-plugin/README.md").exists()

    }
}
