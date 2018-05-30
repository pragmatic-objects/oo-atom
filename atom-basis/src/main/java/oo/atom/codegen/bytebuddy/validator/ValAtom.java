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

import oo.atom.codegen.bytebuddy.matchers.atomspec.*;

/**
 * A validator which validates that certain {@link net.bytebuddy.description.type.TypeDescription} is
 * Atom-compliant (by <a href="https://github.com/project-avral/oo-atom/blob/master/docs/ATOM_SPECIFICATION.md"></a>).
 *
 * @author Kapralov Sergey
 */
public class ValAtom extends ValComplex {
    /**
     * Ctor.
     */
    public ValAtom() {
        super(
            new ValSingle(new AllFieldsArePrivateFinal(), "All Atom's fields must be private final"),
            new ValSingle(new NoArrayFields(), "Fields of array type are restricted in Atoms"),
            new ValSingle(new AllMethodsAreFinal(), "All Atom's methods must be private final"),
            new ValSingle(new HasNoStaticMethods(), "Atom shouldn't have static methods"),
            new ValSingle(new IsNotAbstract(), "Atoms can't be abstract")
        );
    }
}
