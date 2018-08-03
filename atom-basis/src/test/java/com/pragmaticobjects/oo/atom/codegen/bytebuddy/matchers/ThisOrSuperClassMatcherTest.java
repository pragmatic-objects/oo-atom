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

package com.pragmaticobjects.oo.atom.codegen.bytebuddy.matchers;

import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * Tests suite for {@link ThisOrSuperClassMatcher}
 *
 * @author Kapralov Sergey
 */
public class ThisOrSuperClassMatcherTest extends TestsSuite {
    /**
     * Ctor.
     */
    public ThisOrSuperClassMatcherTest() {
        super(
            new TestCase(
                "matches on current class",
                new AssertThatTypeMatches(
                    new TypeDescription.ForLoadedType(Foo.class),
                    new ThisOrSuperClassMatcher(
                        new FooMatcher()
                    )
                )
            ),
            new TestCase(
                "matches on super class",
                new AssertThatTypeMatches(
                    new TypeDescription.ForLoadedType(Foo2.class),
                    new ThisOrSuperClassMatcher(
                        new FooMatcher()
                    )
                )
            ),
            new TestCase(
                "mismatches on current class",
                new AssertThatTypeDoesNotMatch(
                    new TypeDescription.ForLoadedType(Bar.class),
                    new ThisOrSuperClassMatcher(
                        new FooMatcher()
                    )
                )
            )
        );
    }

    //CHECKSTYLE:OFF
    private static class Foo {}
    private static class Bar {}
    private static class Foo2 extends Foo {}

    private static final class FooMatcher implements ElementMatcher<TypeDescription> {
        @Override
        public boolean matches(final TypeDescription target) {
            return target.represents(Foo.class);
        }
    }
}
