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
package oo.atom.codegen.bytebuddy.validator;

import oo.atom.codegen.bytebuddy.matchers.aliasspec.NoFields;
import oo.atom.codegen.bytebuddy.matchers.aliasspec.NoMethods;

/**
 * A validator which validates that certain {@link net.bytebuddy.description.type.TypeDescription} is
 * compliant with Atom alias specification (by See <a href="https://github.com/project-avral/oo-atom/blob/master/docs/ATOM_SPECIFICATION.md"></a>).
 *
 * @author Kapralov Sergey
 */
public class ValAtomAlias extends ValComplex {
    /**
     * Ctor.
     */
    public ValAtomAlias() {
        super(
            new ValSingle(
                new NoFields(), "Atom alias contains new fields, while it shouldn't"
            ),
            new ValSingle(
                new NoMethods(), "Atom alias contains new declared methods, while it shouldn't"
            )
        );
    }
}
