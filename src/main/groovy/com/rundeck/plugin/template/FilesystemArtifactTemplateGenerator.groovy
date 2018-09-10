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
package com.rundeck.plugin.template

import com.rundeck.plugin.generator.PluginTypeTemplateGenerator
import com.rundeck.plugin.generator.JavaPluginTemplateGenerator
import com.rundeck.plugin.generator.ScriptPluginTemplateGenerator
import com.rundeck.plugin.generator.UIScriptPluginTemplateGenerator

class FilesystemArtifactTemplateGenerator implements PluginTemplateGenerator {
    @Override
    String generate(final String pluginName, final PluginType pluginType, final String providedService, final String destinationDir) {
        return getGenerator(pluginType).createTemplate(pluginName, providedService, destinationDir)
    }

    private PluginTypeTemplateGenerator getGenerator(PluginType pluginType) {
        switch(pluginType) {
            case PluginType.java: return new JavaPluginTemplateGenerator()
            case PluginType.script: return new ScriptPluginTemplateGenerator()
            case PluginType.ui: return new UIScriptPluginTemplateGenerator()
        }
    }
}
