/*
 * MIT License
 *
 * Copyright (c) 2018 Kapralov Sergey
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.pragmaticobjects.oo.atom.it;

import com.pragmaticobjects.oo.atom.tests.AssertAtomsToString;
import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;

/**
 * Tests suite for Atoms toString logic.
 *
 * @author Kapralov Sergey
 */
public class ToStringTest extends TestsSuite {
    /**
     * Ctor.
     */
    public ToStringTest() {
        super(
            new TestCase(
                "Simple atom case",
                new AssertAtomsToString(
                    new SimpleAtom(
                        new NotAtom(1, 2),
                        new NotAtom(3, 4)
                    ),
                    String.join(
                        "",
                        "{",
                        "\"@type\": \"com.pragmaticobjects.oo.atom.it.ToStringTest$SimpleAtom\", ",
                        "\"a\": \"1/2\", ",
                        "\"b\": \"3/4\"",
                        "}"
                    )
                )
            ),
            new TestCase(
                "Complex atom case",
                new AssertAtomsToString(
                    new ComplexAtom(
                        new SimpleAtom(
                            new NotAtom(1, 2),
                            new NotAtom(3, 4)
                        ),
                        new SimpleAtom(
                            new NotAtom(5, 6),
                            new NotAtom(7, 8)
                        )
                    ),
                    String.join(
                        "",
                        "{",
                        "\"@type\": \"com.pragmaticobjects.oo.atom.it.ToStringTest$ComplexAtom\", ",
                        "\"a\": {",
                        "\"@type\": \"com.pragmaticobjects.oo.atom.it.ToStringTest$SimpleAtom\", ",
                        "\"a\": \"1/2\", ",
                        "\"b\": \"3/4\"",
                        "}, ",
                        "\"b\": {",
                        "\"@type\": \"com.pragmaticobjects.oo.atom.it.ToStringTest$SimpleAtom\", ",
                        "\"a\": \"5/6\", ",
                        "\"b\": \"7/8\"",
                        "}",
                        "}"
                    )
                )
            )
        );
    }

    //CHECKSTYLE:OFF
    public static class SimpleAtom {
        private final NotAtom a;
        private final NotAtom b;

        public SimpleAtom(NotAtom a, NotAtom b) {
            this.a = a;
            this.b = b;
        }
    }

    public static class ComplexAtom {
        private final SimpleAtom a;
        private final SimpleAtom b;

        public ComplexAtom(SimpleAtom a, SimpleAtom b) {
            this.a = a;
            this.b = b;
        }
    }

    @com.pragmaticobjects.oo.atom.anno.NotAtom
    public static class NotAtom {
        private final int a;
        private final int b;

        public NotAtom(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return a + "/" + b;
        }
    }
}
