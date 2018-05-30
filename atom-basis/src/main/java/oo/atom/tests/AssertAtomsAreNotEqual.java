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
package oo.atom.tests;

import static org.assertj.core.api.Assertions.*;

/**
 * Passes if two objects are not equal.
 *
 * @author Kapralov Sergey
 */
public class AssertAtomsAreNotEqual implements Assertion {
    private final Object atom1;
    private final Object atom2;

    /**
     * Ctor.
     *
     * @param o1 Object to compare
     * @param o2 Object to compare
     */
    public AssertAtomsAreNotEqual(Object o1, Object o2) {
        this.atom1 = o1;
        this.atom2 = o2;
    }

    @Override
    public final void check() throws Exception {
        assertThat(atom1).isNotEqualTo(atom2);
    }
}
