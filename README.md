# Rundeck Plugin Bootstrap

[![Build Status](https://travis-ci.org/rundeck/plugin-bootstrap.svg?branch=master)](https://travis-ci.org/rundeck/plugin-bootstrap)

Bootstrap your Rundeck plugin development with this easy command line utility. 

Download the tar or zip distribution, cd to the bin directory, and run:

./rundeck-plugin-bootstrap -n MyRundeckPlugin -t java -s Notification -d /tmp

A Java/Script notification plugin will be created at /tmp/myrundeckplugin. You can cd into that directory,
run `gradle build` and you will have an installable plugin that you can put in your Rundeck installation.


## Existing service plugins enabled on boostrap-plugin

Java Plugins:
* ResourceModelSource
* Notification
* WorkflowStep
* WorkflowNodeStep
* LogFilter
* NodeExecutor

Script Plugins:
* ResourceModelSource
* WorkflowNodeStep
* RemoteScriptNodeStep
* NodeExecutor
* FileCopier
* NodeExecutorFileCopier: Generate both, Node Executor and File Copier service 

## Other Examples: 

Create a script plugin:

./rundeck-plugin-bootstrap -n MyNodeExecutorPlugin -t script -s NodeExecutor -d /tmp

Create a UI script plugin:

./rundeck-plugin-bootstrap -n MyUIPlugin -t ui -s UI -d /tmp
