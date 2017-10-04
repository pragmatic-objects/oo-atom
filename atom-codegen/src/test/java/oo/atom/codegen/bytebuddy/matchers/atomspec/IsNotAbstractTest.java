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
package oo.atom.codegen.bytebuddy.matchers.atomspec;

import net.bytebuddy.description.type.TypeDescription;
import oo.atom.codegen.bytebuddy.matchers.AssertThatTypeDoesNotMatch;
import oo.atom.codegen.bytebuddy.matchers.AssertThatTypeMatches;
import oo.atom.tests.TestCase;
import oo.atom.tests.TestsSuite;

/**
 *
 * @author Kapralov Sergey
 */
public class IsNotAbstractTest extends TestsSuite {
    public IsNotAbstractTest() {
        super(
            new TestCase(
                "match non-abstract classes",
                new AssertThatTypeMatches(
                    new TypeDescription.ForLoadedType(Foo.class),
                    new IsNotAbstract()
                )
            ),
            new TestCase(
                "mismatch abstract classes",
                new AssertThatTypeDoesNotMatch(
                    new TypeDescription.ForLoadedType(Bar.class),
                    new IsNotAbstract()
                )
            ),
            new TestCase(
                "match enumeration types",
                new AssertThatTypeMatches(
                    new TypeDescription.ForLoadedType(Faz.class),
                    new IsNotAbstract()
                )
            )
        );
    }
    
    private static class Foo {
    }
    
    private abstract static class Bar {
    }
    
    private static enum Faz {
        ONE(1), TWO(2), THREE(3);
        
        private final int origin;

        private Faz(int origin) {
            this.origin = origin;
        }

        public final int getOrigin() {
            return origin;
        }
    }
}
