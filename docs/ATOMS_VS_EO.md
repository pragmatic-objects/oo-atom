# Atoms VS Elegant Objects

Atoms derived very much from Elegant Objects. However, the purpose of the OO-atom project is a bit different. While 
Elegant Objects raise problems of today's OOP *design* and proposes practical recommendations for solving them, OO-atom 
looks at the same problems from *Java bytecode* perspective. How Java code, written in "elegant" object-oriented way, 
may be proved for correctness? How can it be optimized? How to write "elegant" objects and at the same time embrace and 
use existing solutions in Java ecosystem?

Also, in particular, there are some significant conceptual differences between Elegant Objects and OO-atom which worth 
to be mentioned:

## Inheritance vs subtyping
First, according to "Elegant Objects", classes are supposed to be either final or abstract 
("Elegant Objects" vol. 1, section 4.3). This restriction comes from intention to eliminate implementation inheritance 
as a kind. While the purpose is fine per-se, restriction to make non-final classes introduces two problems with
supplementary constructors:

- Because class can't be non-final, the only suitable place to put in supplementary constructor is the class itself.
This leads to uncontrollably growing number of constructors in one class in cases, when there are plenty of possible 
ways to instantiate an object. Check [this]() for example.

- Client code may sometimes introduce new way to instantiate an object. But since the class of the object may be only 
final, there is no way to declare new constructors. This usually leads to duplication of composition structures 
on client side. Check [this]() for example.

Alternate way out of this may be to give client code an ability to define new ways of creating an object. Define new 
constructors. With this idea in mind, [Atom aliases]() were introduced. They allow to distribute and maintain constructors 
in more flexible way and at the same time keep implementation inheritance not possible.

Notice: closest original concept to atom aliases is [decorator envelopes](https://www.yegor256.com/2017/01/31/decorating-envelopes.html).
Atom aliases were introduced as more formalized alternative term with strict [requirements](ATOM_SPECIFICATION.md).

## Equals/Hash code
Second, OO-atom provides new vision on solving mistake, related to `equals`/`hashCode` presence in Java's
`java.lang.Object`. Instead of proposing the [alternate](http://www.yegor256.com/2017/07/11/how-to-redesign-equals.html)
ways of designing code of these methods, OO-atom proposes more cardinal solution. Main idea is that developer must not 
ever have a way to define or override Atoms equality based on field's state, because for Atoms it is already defined and
the same for all Atoms.

OO-atom takes idea of object identity, explained in "Elegant Objects" vol 1. sections 2.1, 2.2 as a basis for defining 
equality semantics between two Atoms. *Two Atoms are equal if and only if they are instance of the same class and their 
fields are equal*. Then, it generates these methods for each Atom during the classes instrumentation stage, so details 
of this comparison remain behind the scenes.

## Exceptions

According "Elegant Objects" vol.1 sec. 4.2, only unchecked exceptions should be avoided in object-oriented code. 
However, this rule cannot be applied practically.

The reason are interfaces. If we defined the rule so that only checked exceptions must be used in Atoms, then it would 
mean that these exceptions must be declared in the interface of an Atom. But there is no knowledge about the nature of 
implementors - there can be plenty of them and they can equally be safe or unsafe. Having exceptions defined in 
interface signature creates unreasonable coupling between the interfaces implementors and clients.

That's why it was decided to omit this rule from Atom specification.
