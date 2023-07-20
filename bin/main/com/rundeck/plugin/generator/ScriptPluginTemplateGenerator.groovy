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

import java.time.Instant


class ScriptPluginTemplateGenerator extends AbstractTemplateGenerator {
    private static final String TEMPLATE_BASE = "templates/script-plugin/"
    private static final String SCRIPT_STRUCTURE = "script-plugin.structure"

    private static final List<String> ALLOWED_SERVICE_TYPES = ["NodeExecutor","FileCopier","ResourceModelSource","WorkflowNodeStep","RemoteScriptNodeStep","NodeExecutorFileCopier","Option"]

    @Override
    Map makeTemplateProperties(final String pluginName, final String providedService) {
        Map templateProperties = [:]
        templateProperties["pluginName"] = pluginName
        templateProperties["sanitizedPluginName"] = GeneratorUtils.sanitizedPluginName(pluginName)
        templateProperties["providedService"] = providedService
        templateProperties["currentDate"] = Instant.now().toString()
        templateProperties["pluginLang"] = "script"
        return templateProperties
    }

    @Override
    URL getPluginStructure(final String providedService) {
        String path = this.getTemplatePath(providedService)
        return getClass().getClassLoader().getResource("${TEMPLATE_BASE}${path}/${SCRIPT_STRUCTURE}")
    }

    @Override
    String getTemplateBase() {
        return TEMPLATE_BASE
    }

    @Override
    void preTemplateValidations(String providedService) {
        if(!ALLOWED_SERVICE_TYPES.contains(providedService)) {
            throw new Exception("Script plugin does not support this service: ${providedService}. Allowed types are: ${ALLOWED_SERVICE_TYPES.join(", ")}")
        }
    }

    @Override
    String resolveTemplateName(final String providedService, final String templateName) {
        String path = this.getTemplatePath(providedService)
        return "${getTemplateBase()}${path}/$templateName"
    }

    String getTemplatePath(final String providedService){
        String path=""
        switch (providedService){
            case ["WorkflowNodeStep","RemoteScriptNodeStep"] :
                path="workflow"
                break
            case ["FileCopier","NodeExecutor","ResourceModelSource"]:
                path=providedService.toLowerCase()
                break
            case "NodeExecutorFileCopier":
                path="nodeexecutor-filecopier"
                break
            default:
                path=providedService.toLowerCase()
                break

        }

        return path
    }
}
