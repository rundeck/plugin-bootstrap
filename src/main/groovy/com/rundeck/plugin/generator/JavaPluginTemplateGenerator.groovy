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
import org.apache.commons.text.WordUtils

import java.time.Instant

class JavaPluginTemplateGenerator extends AbstractTemplateGenerator {
    private static final String TEMPLATE_BASE = "templates/java-plugin/"
    private static final String JAVA_STRUCTURE = "java-plugin.structure"

    private static final List ALLOWED_TEMPLATES = ["ResourceModelSource","Notification","WorkflowStep","WorkflowNodeStep"]

    @Override
    Map makeTemplateProperties(final String pluginName, final String providedService) {
        Map templateProperties = new HashMap()
        templateProperties["pluginName"] = pluginName
        templateProperties["sanitizedPluginName"] = GeneratorUtils.sanitizedPluginName(pluginName)
        templateProperties["javaPluginClass"] = validJavaPluginClassFromName(pluginName)
        templateProperties["providedService"] = providedService
        templateProperties["currentDate"] = Instant.now().toString()
        templateProperties["pluginLang"] = "java"
        templateProperties["rundeckVersion"] = "3.0.x"
        return templateProperties
    }

    @Override
    URL getPluginStructure(String providedService) {
        return getClass().getClassLoader().getResource("${TEMPLATE_BASE}${providedService.toLowerCase()}/$JAVA_STRUCTURE")
    }

    @Override
    String getTemplateBase() {
        return TEMPLATE_BASE
    }

    @Override
    void preTemplateValidations(String providedService) {
        if(!ALLOWED_TEMPLATES.contains(providedService))throw new Exception("Only "+ALLOWED_TEMPLATES.toString()+" plugins generation are supported at this time")
    }

    @Override
    String resolveTemplateName(final String providedService, final String templateName) {
        return "${getTemplateBase()}${providedService.toLowerCase()}/$templateName"
    }

    private def validJavaPluginClassFromName(final String artifactName) {
        return WordUtils.capitalizeFully(artifactName.replaceAll("[^a-zA-Z\\s]", "")).replace(" ", "")
    }

}
