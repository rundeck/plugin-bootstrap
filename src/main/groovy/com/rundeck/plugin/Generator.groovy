package com.rundeck.plugin

import com.rundeck.plugin.template.FilesystemArtifactTemplateGenerator
import com.rundeck.plugin.template.PluginType
import com.rundeck.plugin.template.ServiceType
import picocli.CommandLine
import picocli.CommandLine.Option
import picocli.CommandLine.Command


import java.util.concurrent.Callable

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

@Command(description = "Create a Rundeck plugin artifact.",
        name = "plugin-bootstrap", mixinStandardHelpOptions = true, version = "1.1")
class Generator implements Callable<Void>{

    static void main(String[] args) throws Exception {
        try{
            CommandLine.call(new Generator(), args)
        }catch(Exception e){
            println(e.getMessage())
        }
    }

    @Option(names = [ "-n", "--pluginName" ], description = "Plugin Name." , required = true)
    String pluginName;
    @Option(names = [ "-t", "--pluginType" ] ,description = 'Plugin Type: ${COMPLETION-CANDIDATES}' , required = true)
    PluginType pluginType;
    @Option(names = [ "-s", "--serviceType" ],description = 'Rundeck Service Type: ${COMPLETION-CANDIDATES}', required = true)
    ServiceType serviceType
    @Option(names = [ "-d", "--destinationDirectory" ],description = "The directory in which the artifact directory will be generated", required = true)
    String destinationDirectory

    @Override
    Void call() throws Exception {
        FilesystemArtifactTemplateGenerator generator = new FilesystemArtifactTemplateGenerator()

        println generator.generate(this.pluginName,
                this.pluginType,
                this.serviceType.toString(),
                this.destinationDirectory)

        return null
    }
}
