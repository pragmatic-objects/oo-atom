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
package com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt;

import com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt.c.Condition;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.type.TypeDescription;

/**
 * Generates bytecode for loading field of objects compared in {@link Object#equals(Object)} on stack
 * and comparing them using Atom equality semantics
 * (see <a href="https://github.com/pragmatic-objects/oo-atom/blob/master/docs/ATOM_SPECIFICATION.md"></a>).
 *
 * @author Kapralov Sergey
 */
public class SmtCheckAtomFieldEquality extends SmtCombined {
    /**
     * Ctor.
     * @param type type of the compared objects
     * @param field field to compare
     */
    public SmtCheckAtomFieldEquality(final TypeDescription type, final FieldDescription field) {
        super(
            new SmtLoadPairOfFields(type, field),
            new SmtCompareAtomFields(field),
            new SmtBranch(
                Condition.IS_FALSE,
                new SmtReturnInteger(0)
            )
        );
    }
}
