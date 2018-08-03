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

import com.pragmaticobjects.oo.atom.anno.Atom;
import com.pragmaticobjects.oo.atom.anno.NotAtom;
import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;
import net.bytebuddy.description.type.TypeDescription;

/**
 * Tests suite for {@link ImplementsInterfaceWhichMatches}
 *
 * @author Kapralov Sergey
 */
public class ImplementsInterfaceWhichMatchesTest extends TestsSuite {
    /**
     * Ctor.
     */
    public ImplementsInterfaceWhichMatchesTest() {
        super(
            new TestCase(
                "matches class implementing annotated interface",
                new AssertThatTypeMatches(
                    new TypeDescription.ForLoadedType(Foo.class),
                    new ImplementsInterfaceWhichMatches(
                        new AnnotatedAtom()
                    )
                )
            ),
            new TestCase(
                "mismatches class implementing interface annotated with another annotation",
                new AssertThatTypeDoesNotMatch(
                    new TypeDescription.ForLoadedType(Bar.class),
                    new ImplementsInterfaceWhichMatches(
                        new AnnotatedAtom()
                    )
                )
            ),
            new TestCase(
                "mismatches class implementing non-annotated interface",
                new AssertThatTypeDoesNotMatch(
                    new TypeDescription.ForLoadedType(Baz.class),
                    new ImplementsInterfaceWhichMatches(
                        new AnnotatedAtom()
                    )
                )
            ),
            new TestCase(
                "mismatches class which doesn't implement any interfaces",
                new AssertThatTypeDoesNotMatch(
                    new TypeDescription.ForLoadedType(Faz.class),
                    new ImplementsInterfaceWhichMatches(
                        new AnnotatedAtom()
                    )
                )
            ),
            new TestCase(
                "matches class implementing at least one annotated interface",
                new AssertThatTypeMatches(
                    new TypeDescription.ForLoadedType(Taz.class),
                    new ImplementsInterfaceWhichMatches(
                        new AnnotatedAtom()
                    )
                )
            )
        );
    }

    //CHECKSTYLE:OFF
    @Atom
    private interface AnnotatedAtomApi {}

    @NotAtom
    private interface AnnotatedNotAtomApi {}

    private interface SimpleApi {}

    private static class Foo implements AnnotatedAtomApi {}
    private static class Bar implements AnnotatedNotAtomApi {}
    private static class Baz implements SimpleApi {}
    private static class Faz {}
    private static class Taz implements AnnotatedAtomApi, AnnotatedNotAtomApi, SimpleApi {}
}
