# Rundeck Plugin Bootstrap

[![Build Status](https://travis-ci.org/rundeck/plugin-bootstrap.svg?branch=master)](https://travis-ci.org/rundeck/plugin-bootstrap)

Bootstrap your Rundeck plugin development with this easy command line utility. 


## Install


* From zip file: 
Download the tar or zip distribution, cd to the bin directory.

* From deb package:

```
sudo dpkg -i rundeck-plugin-bootstrap-X.Y.Z-1_all.deb
```

* From rpm package:

```
sudo rpm -i rundeck-plugin-bootstrap-X.Y.Z-1.noarch.rpm
```

## How to use it

Run the following command to get the available options
```
./rundeck-plugin-bootstrap help
```

The options available are:

* `--destinationDirectory or -d` : The directory in which the artifact directory will be generated
* `--pluginName or -n` : Plugin Name
* `--pluginType or -t` : Plugin Type
* `--serviceType or -s` : Rundeck Service Type


### Plugin Type options (`-t`)

The plugins that can be created with the bootstrap client are:
* `script`: it creates a script plugin
* `java`: it creates a java plugin
* `ui`: it creates a UI plugin

### Rundeck Service Type (`-s`)
Existing service plugins enabled on boostrap-plugin

#### for Java Plugins:
* ResourceModelSource
* Notification
* WorkflowStep
* WorkflowNodeStep
* LogFilter
* NodeExecutor
* Orchestrator

#### for Script Plugins:
* ResourceModelSource
* WorkflowNodeStep
* RemoteScriptNodeStep
* NodeExecutor
* FileCopier
* NodeExecutorFileCopier: Generate both, Node Executor and File Copier service 

#### for UI plugins
* UI

## Examples: 

* Create a script plugin:

```
rundeck-plugin-bootstrap -n MyNodeExecutorPlugin -t script -s NodeExecutor -d /tmp
```

A Script NodeExecutor plugin will be created at /tmp/mynodeexecutorplugin. 
You can cd into that directory, run `gradle build` and you will have an installable plugin that you can put in your Rundeck installation.


* Create a UI script plugin:

```
rundeck-plugin-bootstrap -n MyUIPlugin -t ui -s UI -d /tmp
```

* Create a notification java plugin:

```
rundeck-plugin-bootstrap -n MyRundeckNotificationPlugin -t java -s Notification -d /tmp

```
