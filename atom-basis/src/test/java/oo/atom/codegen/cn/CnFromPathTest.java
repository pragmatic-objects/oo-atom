package oo.atom.codegen.cn;

import oo.atom.tests.TestCase;
import oo.atom.tests.TestsSuite;

import java.nio.file.Paths;

class CnFromPathTest extends TestsSuite {
    public CnFromPathTest() {
        super(
            new TestCase(
                "doesn't fail if the directory is absent",
                new AssertZeroClassNames(
                    new CnFromPath(Paths.get("/some-unexisting-path"))
                )
            )
        );
    }
}
