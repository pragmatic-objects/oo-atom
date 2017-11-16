# Fibonacci numbers, quick guide to the world of Atoms

This sample is a primitive implementation of calculating N-th number of Fibonacci row, written in pure object-oriented style, empowered with OO-atom. It demonstrates the base concepts, rules and patterns, commonly used in OO-Atom.

## Workflow

1. Each solution starts with defining a proper contract. [IntegerValue](src/main/java/oo/atom/samples/fibonacci/IntegerValue.java) is a *public contract*, representing some integral value. The role of the public contracts is discussed [here]()

2. [AssertIntegerValueIsMalformed](src/main/java/oo/atom/samples/fibonacci/AssertIntegerValueIsMalformed.java) and [AssertIntegerValueBoxesCertainInteger](src/main/java/oo/atom/samples/fibonacci/AssertIntegerValueBoxesCertainInteger.java) - these are assertions, used to test the `IntegerValue` implementors. The concept of reusable assertions will be covered [here]()

3. We defined the contract and the assertions to test the contract implementors. Now it is time to define the Atoms itself:
- [IvConstant](src/main/java/oo/atom/samples/fibonacci/IvConstant.java) - the simplest Atom-implementor of `IntegerValue` contract. Just a constant integer.
- [IvUndefined](src/main/java/oo/atom/samples/fibonacci/IvUndefined.java) - an Atom, which represents undefined integral value. It is useful when there is a need to represent results of calculations, inapplicable for integral values, like division by zero. In case of Fibonacci numbers, `IvUndefined` is used to represent unexisting values at negative position.
- [IvSum](src/main/java/oo/atom/samples/fibonacci/IvSum.java) - an Atom, representing a sum of two integrals. It demonstrates the usage of [object composition]() - a primary instrument for assembling more complex Atom objects from simpler ones.
- [IvInferred](src/main/java/oo/atom/samples/fibonacci/IvInferred.java) - it is a special kind of Atom - [Inferred Atom](). It is used for non-trivial cases of object composition.
- [IvFibonacci](src/main/java/oo/atom/samples/fibonacci/IvFibonacci.java) - at last, the implementation of an Atom, representing the Fibonacci number at some certain position. Conceptually, it is an [alias]() from `IvInferred`. Aliases are used to gracefully outline and reuse common object composition chains.

4. We defined the nesessary functionality. Now, it's time to test it. It is always possible to test all Atoms using classical approach, populated by mainstream frameworks like JUnit or TestNG. However, OO-atom provides an alternate way, which allows to achieve *ultimate* test coverage. Details are [here](). The actual tests for the Atoms are located [here](src/main/java/oo/atom/samples/fibonacci/).

TODO to be continued
