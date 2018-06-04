package com.pragmaticobjects.oo.atom.codegen.bytebuddy.cfls;

import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;

import java.nio.file.Paths;

class CflsFromPathTest extends TestsSuite {
    private static final String TEST_CLASS_PATH = System.getProperty("user.dir") + "/target/test-classes";

    static {
        System.out.println("user.dir = " + TEST_CLASS_PATH);
    }

    public CflsFromPathTest() {
        super(
            new TestCase(
                "doesn't fail if the path is absent",
                new AssertClassFileLocatorSourceDoesntResolveAClassName(
                    new CflsFromPath(
                        Paths.get("/some_unexisting_path")
                    ),
                    "SomeUnexistingClass"
                )
            ),
            new TestCase(
                "successfully resolves a class from path",
                new AssertClassFileLocatorSourceResolvesAClassName(
                    new CflsFromPath(
                        Paths.get(TEST_CLASS_PATH)
                    ),
                    Foo.class.getName()
                )
            ),
            new TestCase(
                "doesn't resolve absent class from path",
                new AssertClassFileLocatorSourceDoesntResolveAClassName(
                    new CflsFromPath(
                        Paths.get(TEST_CLASS_PATH)
                    ),
                    "SomeUnexistingClass"
                )
            )
        );
    }

    private static final class Foo {}
}
