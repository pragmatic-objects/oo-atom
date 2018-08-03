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
package com.pragmaticobjects.oo.atom.codegen.bytebuddy.bt;

import com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt.SmtReturnInteger;
import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * Tests suite for {@link BtGenerateMethod}
 * @author Kapralov Sergey
 */
public class BtGenerateMethodTest extends TestsSuite {
    /**
     * Ctor.
     */
    public BtGenerateMethodTest() {
        super(
            new TestCase(
                "double transition causes no side effects",
                new AssertClassToHaveCertainMethodsAfterBuilderTransition(
                    new BtSequence(
                        new BtGenerateMethod(named("bar"), type -> new SmtReturnInteger(0)),
                        new BtGenerateMethod(named("bar"), type -> new SmtReturnInteger(0))
                    ),
                    Foo.class,
                    "bar"
                )
            )
        );
    }

    //CHECKSTYLE:OFF
    private static class Foo {
        public int bar() {
            return 0;
        }
    }
}
