## Atom specification

*Note: checked items are validated and enforced by OO-atom project's current version.*

*Atom interface* is Java interface, annotated with `@oo.atom.anno.Atom` annotation.
All implementors of the Atom interface must be Atoms.

*Atom* is a Java class, marked with `@oo.atom.anno.Atom` annotation, which follows the set of rules, provided below:

- [x] All fields are private final
- [x] All methods are final.
- [ ] It has only one primary constructor
- [ ] It may have several supplementary constructors
- [x] It may have static fields, but they must always be private final. They can be initialized either inline, or using static initializer.
- [x] Static methods are prohibited. Exception from the rule are Java Enumerations, synthetic methods, and other cases, when Java compiler generates static methods implicitly.
- [x] It cannot be abstract classes

Additional requirements are set for Atom's constructors. The first rule for Atom constructors is:
- [ ] No logic is allowed in constructors. No method calls, no operators, no validations and exceptions throwing, only referencing arguments, initializing fields, and delegating to another Atom constructors.

Atom constructors are divided to primary and supplementary.
- [ ] Primary constructors are constructors, which do Atom fields initialization. There can be only one primary constructor per Atom. Atom aliases doesn't have a primary constructor.
- [ ] Supplementary constructors are constructors, which delegate to another constructors, located either in this class, or in parent.

It is allowed to extend Atom classes. But implementation inheritance is strictly prohibited in Atoms concept, that's why classes, inherited from Atoms, must follow this set of requirements:
- [x] No new fields declaration
- [x] No new methods declaration
- [x] No overrides or new interface implementation
- [x] The only things which are allowed are supplementary constructors

A class which extends Atom and follows the rules above is named *Atom alias*.

Atoms define a set of requirements on equality. The rules are:
- [x] Two atoms are equal, if and only if they are instance of the same class and their fields are equal.

The fields of Atom classes are compared following these rules:
- [x] If field is of Atom type, it is compared by `equals` method.
- [x] If field is of non-Atom type, it is compared by reference, unless the object, referenced by a field is annotated by `@Atom` annotation.
- [x] Atom aliases must always delegate calling `equals` and `hashCode` methods to super class.

