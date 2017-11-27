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
package oo.atom.it;

import oo.atom.anno.Atom;
import oo.atom.tests.AssertAtomsAreEqual;
import oo.atom.tests.AssertAtomsAreNotEqual;
import oo.atom.tests.TestCase;
import oo.atom.tests.TestsSuite;

import java.util.Objects;

/**
 *
 * @author Kapralov Sergey
 */
public class ExplicitEqualsHashcodeTest extends TestsSuite {
    public ExplicitEqualsHashcodeTest() {
        super(
            new TestCase(
                "non-atom classes keep original equality semantics",
                new AssertAtomsAreNotEqual(
                    new NotAtom(2, 2),
                    new NotAtom(2, 2)
                )
            ),
            new TestCase(
                "explicit atom classes keep original equality semantics",
                new AssertAtomsAreEqual(
                    new ExplicitAtom(2, 2),
                    new ExplicitAtom(4, 0)
                )
            ),
            new TestCase(
                "implicit atoms follow the specification",
                new AssertAtomsAreEqual(
                    new ImplicitAtom(2, 2),
                    new ImplicitAtom(2, 2)
                )
            )
        );
    }

    @Atom
    public static class ExplicitAtom {
        private final int a;
        private final int b;

        public ExplicitAtom(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public final boolean equals(Object obj) {
            if(obj instanceof ExplicitAtom) {
                ExplicitAtom atom = (ExplicitAtom) obj;
                return Objects.equals(atom.a + atom.b, a + b);
            } else {
                return false;
            }
        }

        @Override
        public final int hashCode() {
            return Objects.hash(a + b);
        }
    }

    public static class ImplicitAtom {
        private final int a;
        private final int b;

        public ImplicitAtom(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    @oo.atom.anno.NotAtom
    public static class NotAtom {
        private final int a;
        private final int b;

        public NotAtom(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
