package oo.atom.codegen.javassist.cps;

import oo.atom.anno.NotAtom;
import oo.atom.tests.AssertAssertionFails;
import oo.atom.tests.AssertAssertionPasses;
import oo.atom.tests.TestCase;
import oo.atom.tests.TestsSuite;

/**
 * Tests suite for {@link AssertClassPoolSourceCanLoadAClassTest}.
 *
 * @author Kapralov Sergey
 */
public class AssertClassPoolSourceCanLoadAClassTest extends TestsSuite {
    public AssertClassPoolSourceCanLoadAClassTest() {
        super(
            new TestCase(
                "Nominal case",
                new AssertAssertionPasses(
                    new AssertClassPoolSourceCanLoadAClass(
                        new CpsDefault(),
                        FOO_NAME
                    )
                )
            ),
            new TestCase(
                "Fault case",
                new AssertAssertionFails(
                    new AssertClassPoolSourceCanLoadAClass(
                        new CpsEmpty(),
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