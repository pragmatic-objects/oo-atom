# OO-atom

[![Build Status (Travis)](https://img.shields.io/travis/pragmatic-objects/oo-atom/master.svg)](https://travis-ci.org/pragmatic-objects/oo-atom)
[![Build status (AppVeyor)](https://ci.appveyor.com/api/projects/status/sumvi0c7teo9oq94?svg=true)](https://ci.appveyor.com/project/skapral/oo-atom)
[![Codecov](https://codecov.io/gh/pragmatic-objects/oo-atom/branch/master/graph/badge.svg)](https://codecov.io/gh/pragmatic-objects/oo-atom)

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

Add `atom-maven-plugin` to your project's pom file:

```
<plugin>
    <groupId>com.pragmaticobjects.oo.atom</groupId>
    <artifactId>atom-maven-plugin</artifactId>
    <version>x.y.z</version>
    <executions>
        <execution>
            <goals>
                <goal>generate-annotations</goal>
                <goal>instrument</goal>
                <goal>instrument-tests</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
