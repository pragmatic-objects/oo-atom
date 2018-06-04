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

import com.pragmaticobjects.oo.atom.anno.NotAtom;
import com.pragmaticobjects.oo.atom.tests.AssertAtomsAreEqual;
import com.pragmaticobjects.oo.atom.tests.AssertAtomsAreNotEqual;
import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;

public class GenericAtomsTests extends TestsSuite {
    public GenericAtomsTests() {
        super(
            new TestCase(
                "generic item with atoms uses value equality",
                new AssertAtomsAreEqual(
                    new GenericItem<>(
                        new AtomItem()
                    ),
                    new GenericItem<>(
                        new AtomItem()
                    )
                )
            ),
            new TestCase(
                "generic item with non-atoms uses reference equality",
                new AssertAtomsAreNotEqual(
                    new GenericItem<>(
                        new NotAtomItem()
                    ),
                    new GenericItem<>(
                        new NotAtomItem()
                    )
                )
            )
        );
    }

    public static class AtomItem {
    }

    public static class GenericItem<T> {
        private final T item;

        public GenericItem(final T item) {
            this.item = item;
        }
    }

    @NotAtom
    public static class NotAtomItem {
    }
}
