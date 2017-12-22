#!/bin/bash -

JAVA="java";
if [ ! -z ${JAVA_HOME} ]; then
    JAVA="${JAVA_HOME}/bin/java";
fi

exec ${JAVA} -jar $0 $@