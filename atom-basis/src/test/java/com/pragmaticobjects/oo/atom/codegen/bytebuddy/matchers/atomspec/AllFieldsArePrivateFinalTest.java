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
package com.pragmaticobjects.oo.atom.codegen.bytebuddy.matchers.atomspec;

import com.pragmaticobjects.oo.atom.codegen.bytebuddy.matchers.AssertThatTypeDoesNotMatch;
import com.pragmaticobjects.oo.atom.codegen.bytebuddy.matchers.AssertThatTypeMatches;
import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;
import net.bytebuddy.description.type.TypeDescription;

/**
 * Tests suite for {@link AllFieldsArePrivateFinal}
 *
 * @author Kapralov Sergey
 */
public class AllFieldsArePrivateFinalTest extends TestsSuite {
    /**
     * Ctor.
     */
    public AllFieldsArePrivateFinalTest() {
        super(
            new TestCase(
                "match type with private final fields only",
                new AssertThatTypeMatches(
                    new TypeDescription.ForLoadedType(Foo.class),
                    new AllFieldsArePrivateFinal()
                )
            ),
            new TestCase(
                "match type with no fields",
                new AssertThatTypeMatches(
                    new TypeDescription.ForLoadedType(Faz.class),
                    new AllFieldsArePrivateFinal()
                )
            ),
            new TestCase(
                "mismatch type when at least one field is non-final",
                new AssertThatTypeDoesNotMatch(
                    new TypeDescription.ForLoadedType(Bar.class),
                    new AllFieldsArePrivateFinal()
                )
            ),
            new TestCase(
                "mismatch type when at least one field is non-private",
                new AssertThatTypeDoesNotMatch(
                    new TypeDescription.ForLoadedType(Baz.class),
                    new AllFieldsArePrivateFinal()
                )
            ),
            new TestCase(
                "match enumeration type",
                new AssertThatTypeMatches(
                    new TypeDescription.ForLoadedType(Haz.class),
                    new AllFieldsArePrivateFinal()
                )
            )
        );
    }

    //CHECKSTYLE:OFF
    private static class Foo {
        private final Object method1;
        private final Object method2;
        private final Object method3;

        public Foo(Object method1, Object method2, Object method3) {
            this.method1 = method1;
            this.method2 = method2;
            this.method3 = method3;
        }
    }
    
    private static class Bar {
        private final Object method1;
        private Object method2;
        private final Object method3;

        public Bar(Object method1, Object method2, Object method3) {
            this.method1 = method1;
            this.method2 = method2;
            this.method3 = method3;
        }
    }
    
    private static class Baz {
        private final Object method1;
        final Object method2;
        private final Object method3;

        public Baz(Object method1, Object method2, Object method3) {
            this.method1 = method1;
            this.method2 = method2;
            this.method3 = method3;
        }
    }
    
    private static class Faz {
    }
    
    private static enum Haz {
        ONE(1), TWO(2), THREE(3);
        
        private final int origin;

        private Haz(int origin) {
            this.origin = origin;
        }

        public final int getOrigin() {
            return origin;
        }
    }
}
