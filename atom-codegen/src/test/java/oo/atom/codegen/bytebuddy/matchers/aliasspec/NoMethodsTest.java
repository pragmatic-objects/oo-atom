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
package oo.atom.codegen.bytebuddy.matchers.aliasspec;

import net.bytebuddy.description.type.TypeDescription;
import oo.atom.codegen.bytebuddy.bt.BtGenerateHashCode;
import oo.atom.codegen.bytebuddy.matchers.AssertThatTypeDoesNotMatch;
import oo.atom.codegen.bytebuddy.matchers.AssertThatTypeMatches;
import oo.atom.tests.AssertionsSuite;

/**
 *
 * @author Kapralov Sergey
 */
public class NoMethodsTest extends AssertionsSuite {

    public NoMethodsTest() {
        super(
            new AssertThatTypeMatches(
                "match type with no methods",
                new TypeDescription.ForLoadedType(Foo.class),
                new NoMethods()
            ),
            new AssertThatTypeDoesNotMatch(
                "mismatch type with at least one method",
                new TypeDescription.ForLoadedType(Bar.class),
                new NoMethods()
            ),
            new AssertThatTypeMatches(
                "match type ignoring methods declared in base class",
                new TypeDescription.ForLoadedType(Barr.class),
                new NoMethods()
            )
        );
    }

    private static class Foo {
    }

    private static class Bar {
        public final void method() {
        }
    }

    private static class Barr extends Bar {
    }
}
