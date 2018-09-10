package com.rundeck.plugin

import com.lexicalscope.jewel.cli.Option
import com.rundeck.plugin.template.FilesystemArtifactTemplateGenerator
import com.rundeck.plugin.template.PluginType
import org.rundeck.toolbelt.Command
import org.rundeck.toolbelt.CommandRunFailure
import org.rundeck.toolbelt.SubCommand
import org.rundeck.toolbelt.ToolBelt
import org.rundeck.toolbelt.input.jewelcli.JewelInput

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

@SubCommand
class Generator {

    private static final List<String> VALID_PLUGIN_TYPES = ["java","script","ui"]

    public static void main(String[] args) throws IOException, CommandRunFailure {
        ToolBelt.with("verb", new JewelInput(), new Generator()).runMain(args, true);
    }

    @Command(description = "Create a Verb artifact")
    public void create(CreateOpts createOpts) {
        if(!VALID_PLUGIN_TYPES.contains(createOpts.pluginType)) {
            println "Artifact type must be one of: ${VALID_PLUGIN_TYPES.join("|")}"
            return
        }
        FilesystemArtifactTemplateGenerator generator = new FilesystemArtifactTemplateGenerator()
        println generator.generate(createOpts.pluginName,
                                          PluginType.valueOf(createOpts.pluginType),
                                          createOpts.serviceType,
                                          createOpts.destinationDirectory)
    }

    interface CreateOpts {
        @Option(shortName = "n",description = "Plugin Name")
        String getPluginName()
        @Option(shortName = "t",description = "Plugin Type")
        String getPluginType()
        @Option(shortName = "s",description = "Rundeck Service Type")
        String getServiceType()
        @Option(shortName = "d",description = "The directory in which the artifact directory will be generated")
        String getDestinationDirectory()
    }
}
