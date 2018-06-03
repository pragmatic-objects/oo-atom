#!/bin/bash -

# TODO: check for TAG
if [ -z "${TAG}" ]; then
    echo "TAG must be set"
    exit 1
fi

git checkout $(git rev-parse HEAD)
mvn versions:set "-DnewVersion=${TAG}"
cd atom-starter
mvn versions:set "-DnewVersion=${TAG}"
mvn versions:set-property -Dproperty=atom.version "-DnewVersion=${TAG}"
cd -
mvn clean deploy -Pextras,ossrh
git commit -am "[release] oo-atom-${TAG}"
git tag -f ${TAG}
git push origin ${TAG}

