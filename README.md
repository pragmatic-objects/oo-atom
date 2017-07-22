# oo-atom

## Introduction

The project's purpose is to provide an alternate way of designing applications and libraries on Java. The concept is built around the term "Atom". Atom is a Java class which meets the following requirements:

- [ ] All fields of atom are private final
- [ ] All methods of atom are final.
- [ ] Atom has only one primary constructor, aka constructor which does fields initialization
- [ ] Atom may have additional non-primary constructors
- [ ] In primary constructor of atom, the only thing which is allowed is to do fields initialization from constructor arguments.
- [ ] In non-primary constructors of atom, it is only allowed to create new objects with `new` operator, reference the constructor's arguments and delegate to another constructors.
- [ ] Atoms may be extended, but sub-atoms are only allowed to declare the new constructors. Another term for such subclasses is "Alias" (TODO: describe in detail)
- [ ] Atoms may have static fields, but they must always be private final. They can be initialized either inline, or using static initializers.
- [ ] Static methods are prohibited in atoms. Exception from the rule are Java Enumerations.
- [ ] Atoms cannot be abstract classes

## Purpose

Java classes organized in such way have interesting characteristics, which oo-atom assumes and uses for bytecode transformation:
TODO: describe

## Explaination

TODO: describe

## Patterns

TODO: describe

## Compatibility

TODO: describe
