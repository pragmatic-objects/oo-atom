/*
 * The MIT License
 *
 * Copyright 2019 skapral.
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
package com.pragmaticobjects.oo.atom.codegen.javassist.templates;

import com.pragmaticobjects.oo.atom.anno.NotAtom;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests suite for {@link Atoms}
 * 
 * @author skapral
 */
public class AtomsTest {
    //CHECKSTYLE:OFF
    @Test
    public final void nonAtomEqualsShouldNeverBeCalled() {
        NonAtom nonAtom = new NonAtom();
        Assertions.assertThatCode(() -> {
            Atoms.atom$equal(nonAtom, nonAtom);
        }).doesNotThrowAnyException();
    }
    
    @Test
    public final void nonAtomHashCodeShouldNeverBeCalled() {
        NonAtom nonAtom = new NonAtom();
        Assertions.assertThatCode(() -> {
            Atoms.atom$hashCode(nonAtom);
        }).doesNotThrowAnyException();
    }
    
    @NotAtom
    public final class NonAtom {
        @Override
        public boolean equals(Object obj) {
            throw new UnsupportedOperationException("Shouldn't be called");
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("Shouldn't be called");
        }
    }
}
