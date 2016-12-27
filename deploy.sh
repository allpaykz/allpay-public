#!/bin/bash

# deploy only on snapshots

if [[ "$(printf 'VERSION=${project.version}\n0\n' | mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate | grep '^VERSION='|sed 's/VERSION=//g')" == *'SNAPSHOT'* ]] ; then
    echo 'current version is SNAPSHOT, deploying it'
    mvn clean deploy -Prelease --settings settings.xml
else
    echo 'deploy skipped, current version is not SNAPSHOT manual deploy required, just run tests'
    mvn clean verify
fi
