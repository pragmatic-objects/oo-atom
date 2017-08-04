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

import java.util.function.Supplier;
import net.bytebuddy.description.type.TypeDescription;
import oo.atom.codegen.bytebuddy.matchers.AssertThatTypeDoesNotMatch;
import oo.atom.codegen.bytebuddy.matchers.AssertThatTypeMatches;
import oo.atom.tests.AssertionsSuite;

/**
 *
 * @author Kapralov Sergey
 */
public class HasNoStaticMethodsTest extends AssertionsSuite {
    public HasNoStaticMethodsTest() {
        super(
            new AssertThatTypeMatches(
                "match type with no static methods",
                new TypeDescription.ForLoadedType(Foo.class),
                new HasNoStaticMethods()
            ),
            new AssertThatTypeMatches(
                "match type with lambdas",
                new TypeDescription.ForLoadedType(Baz.class),
                new HasNoStaticMethods()
            ),
            new AssertThatTypeMatches(
                "match enumeration types",
                new TypeDescription.ForLoadedType(Faz.class),
                new HasNoStaticMethods()
            ),
            new AssertThatTypeDoesNotMatch(
                "mismatch type with at least one static method",
                new TypeDescription.ForLoadedType(Bar.class),
                new HasNoStaticMethods()
            )
        );
    }

    private static class Foo {

        private static final Object item = new Object();
    }

    private static class Bar {
        private static final Object item() {
            return new Object();
        }
    }
    
    private static class Baz {
        private final Object item() {
            Supplier supplier = () -> new Object();
            return supplier.get();
        }
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
