#!/bin/sh
exec java ${JAVA_OPTS} -Dspring.profiles.active=docker -jar "/root/app.jar"
