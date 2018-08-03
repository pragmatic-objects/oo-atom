package com.pragmaticobjects.oo.atom.codegen.javassist.cps;

import com.pragmaticobjects.oo.atom.anno.NotAtom;
import com.pragmaticobjects.oo.atom.tests.AssertAssertionFails;
import com.pragmaticobjects.oo.atom.tests.AssertAssertionPasses;
import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;

/**
 * Tests suite for {@link AssertClassPoolSourceCanLoadAClassTest}.
 *
 * @author Kapralov Sergey
 */
public class AssertClassPoolSourceCanLoadAClassTest extends TestsSuite {
    /**
     * Ctor.
     */
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

    //CHECKSTYLE:OFF
    @NotAtom
    private static final class Foo {}
    private static final String FOO_NAME = Foo.class.getName();
}