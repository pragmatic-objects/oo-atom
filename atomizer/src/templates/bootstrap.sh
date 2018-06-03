#!/bin/bash -
JAVA="java";
if [ ! -z ${JAVA_HOME} ]; then
    JAVA="${JAVA_HOME}/bin/java";
fi

echo "${JAVA} -cp $0:$1 com.pragmaticobjects.oo.atom.codegen.AtomizerMain"
exec ${JAVA} -cp "$0:$1" com.pragmaticobjects.oo.atom.codegen.AtomizerMain