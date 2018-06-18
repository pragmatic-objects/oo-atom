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

import com.pragmaticobjects.oo.atom.codegen.bytebuddy.matchers.NaturalJavaAtom;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * Generates bytecode for comparing a field of two objects using atom equality semantics.
 *
 * @author Kapralov Sergey
 */
public class SmtCompareAtomFields extends SmtInferred implements StackManipulationToken {
    /**
     * Ctor.
     *
     * @param field Field.
     */
    public SmtCompareAtomFields(FieldDescription field) {
        super(
            new Inference(
                field
            )
        );
    }

    /**
     * {@link SmtCompareAtomFields} inference.
     *
     * @author Kapralov Sergey
     */
    private static class Inference implements StackManipulationToken.Inference {
        private final FieldDescription field;

        /**
         * Ctor.
         *
         * @param field Field.
         */
        public Inference(FieldDescription field) {
            this.field = field;
        }

        @Override
        public final StackManipulationToken stackManipulationToken() {
            final TypeDescription declaringType = field.getDeclaringType().asErasure();
            final TypeDescription fieldType = field.getType().asErasure();
            if(new NaturalJavaAtom().matches(fieldType)) {
                return new SmtInvokeMethod(
                    new TypeDescription.ForLoadedType(Object.class),
                    ElementMatchers.named("equals")
                );
            } else {
                return new SmtInvokeAtomEqual(declaringType);
            }
        }
    }
}
