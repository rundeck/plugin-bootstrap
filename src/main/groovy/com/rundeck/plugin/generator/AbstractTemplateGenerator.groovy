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

import com.rundeck.plugin.utils.GeneratorUtils
import groovy.text.GStringTemplateEngine


abstract class AbstractTemplateGenerator implements PluginTypeTemplateGenerator {

    GStringTemplateEngine engine = new GStringTemplateEngine()

    abstract Map makeTemplateProperties(String pluginName, String providedService) //create map of properties that will be used to resolve variables when templates are converted into plugin files
    abstract URL getPluginStructure(String providedService)  //the resource on the class path that defines the plugin structure
    abstract String getTemplateBase()  //the resource base directory from which plugin template files will be resolved
    abstract void preTemplateValidations(String providedService) //show throw exception on validation error
    abstract String resolveTemplateName(String providedService, String templateName)

    String createTemplate(String pluginName, String providedService, String destinationDir) {
        Map templateProperties = makeTemplateProperties(pluginName, providedService)
        preTemplateValidations(providedService)
        File destDir = new File(destinationDir + "/" + GeneratorUtils.sanitizedPluginName(pluginName))
        if(destDir.exists()) {
            throw new Exception("Destination dir: ${destDir.absolutePath} already exists. Aborting to avoid overwriting.")
        } else {
            destDir.mkdirs()
        }
        StringWriter structureWriter = new StringWriter()
        engine.createTemplate(getPluginStructure(providedService)).
                make(templateProperties).
                writeTo(structureWriter)

        structureWriter.toString().eachLine { entry ->
            if (entry.isEmpty()) return
            def entryParts = entry.split("->").toList()
            String templateName = entryParts.first()
            String destinationName = entryParts.last()
            String resolvedTemplateName = resolveTemplateName(providedService,templateName)
            URL template = getClass().getClassLoader().getResource(resolvedTemplateName)
            if(!template) {
                throw new Exception("Please check the template structure file. Unable to find template file: $resolvedTemplateName")
            }
            def destParts = destinationName.split("/").toList()
            String fileName = destParts.remove(destParts.size() - 1)
            String remainingPath = destParts.join("/")
            if (!remainingPath.isEmpty()) {
                new File(destDir, remainingPath).mkdirs()
            }
            File destFile = new File(destDir, remainingPath + "/" + fileName)

            FileWriter fileOut = new FileWriter(destFile)
            if(template.toString().endsWith(".template")) {
                engine.createTemplate(template).make(templateProperties).writeTo(fileOut)
            } else {
                fileOut << template.openStream()
            }
            fileOut.flush()
        }
        return "Plugin generated at: ${destDir.absolutePath}"
    }
}
