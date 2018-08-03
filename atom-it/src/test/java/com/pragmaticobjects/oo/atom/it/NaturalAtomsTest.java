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

package com.pragmaticobjects.oo.atom.it;

import com.pragmaticobjects.oo.atom.tests.AssertAtomsAreEqual;
import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;

/**
 * Tests related to natural atoms
 *
 * @author Kapralov Sergey
 */
public class NaturalAtomsTest extends TestsSuite {
    /**
     * Ctor.
     */
    public NaturalAtomsTest() {
        super(
            new TestCase(
                "atom with Integer",
                new AssertAtomsAreEqual(
                    new IntegerAtom(
                        new Integer(1)
                    ),
                    new IntegerAtom(
                        new Integer(1)
                    )
                )
            ),
            new TestCase(
                "atom with String",
                new AssertAtomsAreEqual(
                    new StringAtom(
                        "aaa"
                    ),
                    new StringAtom(
                        "aaa"
                    )
                )
            )
        );
    }

    //CHECKSTYLE:OFF
    private static class IntegerAtom {
        private final Integer i;

        public IntegerAtom(final Integer i) {
            this.i = i;
        }
    }

    private static class StringAtom {
        private final String i;

        public StringAtom(final String i) {
            this.i = i;
        }
    }
}
