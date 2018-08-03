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
import com.pragmaticobjects.oo.atom.tests.AssertAtomsAreNotEqual;
import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;


/**
 * Tests suite, testing atoms equality rules
 * 
 * @author Kapralov Sergey
 */
public class BasicTest extends TestsSuite {
    /**
     * Ctor.
     */
    public BasicTest() {
        super(
            new TestCase(
                "different atom objects with same fields are equal",
                new AssertAtomsAreEqual(
                    new Foo(4), 
                    new Foo(4)
                )
            ),
            new TestCase(
                "atom objects with different fields are not equal",
                new AssertAtomsAreNotEqual(
                    new Foo(4),
                    new Foo(5)
                )
            ),
            new TestCase(
                "atoms of different types are not equal", 
                new AssertAtomsAreNotEqual(
                    new Foo(4),
                    new Bar(4)
                )
            ),
            new TestCase(
                "alias atom and basis atom with same constructor arguments are equal",
                new AssertAtomsAreEqual(
                    new FooAlias(),
                    new Foo(42)
                )
            )
        );
    }

    //CHECKSTYLE:OFF
    public static class Bar {
        private final int a;

        public Bar(int a) {
            this.a = a;
        }
    }

    public static class Foo {
        private final int a;

        public Foo(int a) {
            this.a = a;
        }
    }

    public static class FooAlias extends Foo {
        public FooAlias() {
            super(42);
        }
    }
}
