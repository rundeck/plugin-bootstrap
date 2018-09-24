# Rundeck Plugin Bootstrap

[![Build Status](https://travis-ci.org/sjrd218/rundeck-plugin-bootstrap.svg?branch=master)](https://travis-ci.org/sjrd218/rundeck-plugin-bootstrap)

Bootstrap your Rundeck plugin development with this easy command line utility. 

Download the tar or zip distribution, cd to the bin directory, and run:

./rundeck-plugin-bootstrap -n MyRundeckPlugin -t java -s Notification -d /tmp

A Java notification plugin will be created at /tmp/myrundeckplugin. You can cd into that directory,
run `gradle build` and you will have an installable notification plugin that you can put in your Rundeck installation.

* Note: At this time only Notification plugins can be bootstrapped with this utility.

Other examples: 

Create a script plugin:

./rundeck-plugin-bootstrap -n MyNodeExecutorPlugin -t script -s NodeExecutor /tmp

Create a UI script plugin:

./rundeck-plugin-bootstrap -n MyUIPlugin -t ui -s UI /tmp
