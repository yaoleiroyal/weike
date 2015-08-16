#!/bin/sh

mvn clean package -DskipTests -P prod

target_war=./target/h2double.war
if [ ! -d "$target_war" ]; then
    scp target/h2double.war dev:
else
    echo 'the war file is can not found'
fi
