/*
 * The MIT License
 *
 * Copyright 2017 Kapralov Sergey.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package oo.atom.codegen.javapoet;

import oo.atom.tests.TestCase;
import oo.atom.tests.TestsSuite;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.nio.file.Paths;

class JpAnnotationTest extends TestsSuite {
    public JpAnnotationTest() {
        super(
            new TestCase(
                "generates annotation",
                new AssertJavapoetProducesCertainSourceCode(
                    new JpAnnotation(
                        "oo.test",
                        "TestAnno",
                        RetentionPolicy.RUNTIME,
                        ElementType.TYPE
                    ),
                    Paths.get("oo", "test", "TestAnno.java"),
                    String.join(
                        "\r\n",
                        "package oo.test;",
                        "",
                        "import static java.lang.annotation.ElementType.*;",
                        "import static java.lang.annotation.RetentionPolicy.RUNTIME;",
                        "",
                        "import java.lang.annotation.Retention;",
                        "import java.lang.annotation.Target;",
                        "",
                        "@Retention(RUNTIME)",
                        "@Target({TYPE})",
                        "public @interface TestAnno {",
                        "}"
                    )
                )
            ),
            new TestCase(
                "generates annotation with multiple targets",
                new AssertJavapoetProducesCertainSourceCode(
                    new JpAnnotation(
                        "oo.test",
                        "TestAnno",
                        RetentionPolicy.RUNTIME,
                        ElementType.TYPE, ElementType.METHOD
                        ),
                    Paths.get("oo", "test", "TestAnno.java"),
                    String.join(
                        "\r\n",
                        "package oo.test;",
                        "",
                        "import static java.lang.annotation.ElementType.*;",
                        "import static java.lang.annotation.RetentionPolicy.RUNTIME;",
                        "",
                        "import java.lang.annotation.Retention;",
                        "import java.lang.annotation.Target;",
                        "",
                        "@Retention(RUNTIME)",
                        "@Target({METHOD, TYPE})",
                        "public @interface TestAnno {",
                        "}"
                    )
                )
            )
        );
    }
}