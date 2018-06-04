#!/bin/bash -

# TODO: check for TAG
if [ -z "${TAG}" ]; then
    echo "TAG must be set"
    exit 1
fi

git checkout $(git rev-parse HEAD)
mvn versions:set "-DnewVersion=${TAG}"
mvn clean deploy -Pextras,ossrh
git commit -am "[release] oo-atom-${TAG}"
git tag -f ${TAG}

