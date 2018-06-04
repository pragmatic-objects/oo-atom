package com.pragmaticobjects.oo.atom.codegen.cn;

import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class CnFromPathTest extends TestsSuite {
    private static final Path PATH_WITH_MODULE_INFO;
    private static final Path PATH_WITH_CLASSES;

    static {
        try {
            {
                PATH_WITH_MODULE_INFO = Files.createTempDirectory("module-info-path");
                Path moduleInfo = PATH_WITH_MODULE_INFO.resolve("module-info.class");
                Files.createFile(moduleInfo);
            }
            {
                PATH_WITH_CLASSES = Files.createTempDirectory("path-with-classes");
                Path class1 = PATH_WITH_CLASSES.resolve("com").resolve("example").resolve("First.class");
                Files.createDirectories(class1.getParent());
                Files.createFile(class1);
                Path class2 = PATH_WITH_CLASSES.resolve("com").resolve("example").resolve("Second.class");
                Files.createDirectories(class2.getParent());
                Files.createFile(class2);
            }
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public CnFromPathTest() {
        super(
            new TestCase(
                "scans all class names from provided path",
                new AssertClassNamesContainCertainNames(
                    new CnFromPath(PATH_WITH_CLASSES),
                    "com.example.First",
                    "com.example.Second"
                )
            ),
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
