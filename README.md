# OO-atom

[![Build Status (Travis)](https://img.shields.io/travis/project-avral/oo-atom/master.svg)](https://travis-ci.org/project-avral/oo-atom)
[![Build status (AppVeyor)](https://ci.appveyor.com/api/projects/status/sumvi0c7teo9oq94?svg=true)](https://ci.appveyor.com/project/skapral/oo-atom)

[![Codecov](https://codecov.io/gh/project-avral/oo-atom/branch/master/graph/badge.svg)](https://codecov.io/gh/project-avral/oo-atom)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b91e6bf2e7744333b93d2b8785fe056b)](https://www.codacy.com/app/skapral/oo-atom?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=project-avral/oo-atom&amp;utm_campaign=Badge_Grade)
[![PDD status](http://www.0pdd.com/svg?name=project-avral/oo-atom)](http://www.0pdd.com/p?name=project-avral/oo-atom)

## Disclaimer

The project is currently on early stage. Not all of the planned features are currently implemented, and everything
may be changed in future.

## Intro

OO-atom is a desperate attempt to make Java programming more predictable and transparent.
Instead of fighting the boilerplate code by hiding it, introducing hidden coupling, like it is done by Java EE, Spring, 
Hibernate and other "magical" solutions, OO-atom greets clean and open design by taking the most from Java SE, and the
best from OOP.

The project is built around the term named "Atom". Atom is a Java class, which strictly follows a set 
of [requirements](docs/ATOM_SPECIFICATION.md). Requirements were selected in such way that declarative object composition 
for the Atoms is trivial and common approach. Object composition is used as a main instrument for composing application
from a small cohesive classes, instead of dependencies injection by means of DI containers.

Also, for all Atoms, OO-atom seeks for the best ways of improving quality, maintainability, test coverage 
and performance.

The project is inspired by the guidelines from the books "[Elegant Objects](http://www.yegor256.com/elegant-objects.html)",
written by Yegor Bugayenko, and the materials from his [blog](http://www.yegor256.com/tag/oop.html).
However, several changes and additions were made to initial concept - see [this](docs/ATOMS_VS_EO.md) page for the 
list of differences.

OO-atom project is designed on the following principles:
- it is transparent. It never dictates the architecture of your applications.
- it is flexible. You are free to use it with any solution from a large Java ecosystem
- it operates at compile-time only. It never brings ad-hoc runtime dependencies to your application.

## Quick start with Maven

Make your project parent from `atom-starter`.

```
    <parent>
        <groupId>com.pragmaticobjects.oo.atom</groupId>
        <artifactId>atom-starter</artifactId>
        <version>x.y.z</version>
    </parent>
```

## How it works

The project provides a tool, called "Atomizer". It can be downloaded
[here](http://dl.bintray.com/skapral/oo-maven/oo/atomizer/0.0.11/).
The tool checks the classes in a directory for compliance with atom specification.
To use a tool on a classpath:
```
$ cd <directory with classes>; JAVA_HOME=<java home> atomizer.sh <classpath>

where <classpath> is a list of jars and directories delimited with ':'.
```

The tool is executed on compiled classes before packing them to JAR. Main procedures, done on classes, are defined 
[here](atom-basis/src/main/java/oo/atom/codegen/AtomizerMain.java).

