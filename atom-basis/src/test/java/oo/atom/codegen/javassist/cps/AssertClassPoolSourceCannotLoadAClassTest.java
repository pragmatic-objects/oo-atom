package oo.atom.codegen.javassist.cps;

import oo.atom.anno.NotAtom;
import oo.atom.tests.AssertAssertionFails;
import oo.atom.tests.AssertAssertionPasses;
import oo.atom.tests.TestCase;
import oo.atom.tests.TestsSuite;

/**
 * Tests suite for {@link AssertClassPoolSourceCannotLoadAClass}.
 *
 * @author Kapralov Sergey
 */
public class AssertClassPoolSourceCannotLoadAClassTest extends TestsSuite {
    public AssertClassPoolSourceCannotLoadAClassTest() {
        super(
            new TestCase(
                "Nominal case",
                new AssertAssertionPasses(
                    new AssertClassPoolSourceCannotLoadAClass(
                        new CpsEmpty(),
                        FOO_NAME
                    )
                )
            ),
            new TestCase(
                "Fault case",
                new AssertAssertionFails(
                    new AssertClassPoolSourceCannotLoadAClass(
                        new CpsDefault(),
                        FOO_NAME
                    )
                )
            )
        );
    }

    @NotAtom
    private static final class Foo {}
    private static final String FOO_NAME = Foo.class.getName();
}