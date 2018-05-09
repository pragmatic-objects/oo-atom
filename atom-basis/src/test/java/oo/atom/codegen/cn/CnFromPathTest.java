package oo.atom.codegen.cn;

import oo.atom.tests.TestCase;
import oo.atom.tests.TestsSuite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class CnFromPathTest extends TestsSuite {
    private static final Path PATH_WITH_MODULE_INFO;

    static {
        try {
            PATH_WITH_MODULE_INFO = Files.createTempDirectory("module-info-path");
            Path moduleInfo = PATH_WITH_MODULE_INFO.resolve("module-info.class");
            Files.createFile(moduleInfo);
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public CnFromPathTest() {
        super(
            new TestCase(
                "doesn't fail if the directory is absent",
                new AssertZeroClassNames(
                    new CnFromPath(Paths.get("/some-unexisting-path"))
                )
            ),
            new TestCase(
                "ignores module-info.class",
                new AssertZeroClassNames(
                    new CnFromPath(PATH_WITH_MODULE_INFO)
                )
            )
        );
    }
}
