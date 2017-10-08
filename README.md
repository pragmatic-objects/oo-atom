# OO-atom

[![Build Status](https://img.shields.io/travis/project-avral/oo-atom/master.svg)](https://travis-ci.org/project-avral/oo-atom)
[![PDD status](http://www.0pdd.com/svg?name=project-avral/oo-atom)](http://www.0pdd.com/p?name=project-avral/oo-atom)

## Disclaimer
The project is currently on early stage. Not all of the planned features are currently implemented, and everything may be changed in future.

## Intro

OO-atom is a desperate attempt to make Java programming more predictable and transparent. Instead of fighting the boilerplate code by hiding it, introducing hidden coupling, like it is done by Java EE, Spring, Hibernate and other "magical" solutions, OO-atom greets clean and open design by taking the most from Java SE, and the best from OOP.

The project is inspired by the guidelines from the books "[Elegant Objects](http://www.yegor256.com/elegant-objects.html)", written by Yegor Bugayenko, and the materials from his [blog](http://www.yegor256.com/tag/oop.html). However, several changes and additions were made to initial concept - see [this]() page for the list of differences.

The project is built around the term named Atom. Atom is a Java class, which strictly follows a set of [requirements](ATOM_SPECIFICATION.md). For all Atoms, OO-atom seeks for the best ways of improving quality, maintainability, test coverage and performance.

OO-atom project is designed on the following principles:
- it is transparent. It never dictates the architecture of your applications.
- it is flexible. You are free to use it with any solution from a large Java ecosystem
- it operates at compile-time only. It never brings ad-hoc runtime dependencies to your application.

# Quick start with Maven

1. Add OO-atom repository to your project's POM file:

```
    <repositories>
        <repository>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
            <id>bintray-releases</id>
            <url>http://dl.bintray.com/skapral/oo-maven</url>
        </repository>
    </repositories>
```

2. Make your project parent from `atom-starter`.

```
    <parent>
        <groupId>oo</groupId>
        <artifactId>atom-starter</artifactId>
        <version>x.y.z</version>
    </parent>
```

3. (optional) Include a couple of handy artifacts to your project's dependencies list

```
    <dependency>
        <groupId>oo</groupId>
        <artifactId>atom-api</artifactId>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>oo</groupId>
        <artifactId>atom-tests</artifactId>
        <scope>provided</scope>
    </dependency>
```

4. Study the [samples](atom-samples)



