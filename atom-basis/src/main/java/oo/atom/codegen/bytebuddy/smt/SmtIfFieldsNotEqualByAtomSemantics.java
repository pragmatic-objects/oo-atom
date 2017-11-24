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
package oo.atom.codegen.bytebuddy.smt;

import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.matcher.ElementMatchers;
import oo.atom.codegen.bytebuddy.smt.c.Condition;
import oo.atom.r.RInferred;
import oo.atom.r.Result;

class SmtIfFieldsNotEqualByAtomSemanticsInference implements Result.Inference<StackManipulation> {
    private final FieldDescription field;
    private final StackManipulationToken smt;

    public SmtIfFieldsNotEqualByAtomSemanticsInference(FieldDescription field, StackManipulationToken smt) {
        this.field = field;
        this.smt = smt;
    }

    @Override
    public final Result<StackManipulation> result() {
        final TypeDescription fieldType = field.getType().asErasure();
        final TypeDescription ownerType = field.getDeclaringType().asErasure();
        if(fieldType.isArray()) {
            // @todo #78 Applying equality seemantics for arrays was not a good idea. To reconsider later.
            return new SmtIfNotDeeplyEqual(smt);
        } else {
            final MethodDescription.InDefinedShape atom$equals = ownerType.getDeclaredMethods()
                .filter(ElementMatchers.isSynthetic())
                .filter(ElementMatchers.named("atom$equal"))
                .getOnly();
            return new SmtCombined(
                new SmtStatic(
                    MethodInvocation.invoke(
                        atom$equals
                    )
                ),
                new SmtIf(
                    Condition.IS_FALSE,
                    smt
                )
            );
        }
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class SmtIfFieldsNotEqualByAtomSemantics extends RInferred<StackManipulation> implements StackManipulationToken {
    public SmtIfFieldsNotEqualByAtomSemantics(FieldDescription field, StackManipulationToken smt) {
        super(
            new SmtIfFieldsNotEqualByAtomSemanticsInference(
                field,
                smt
            )
        );
    }
}
