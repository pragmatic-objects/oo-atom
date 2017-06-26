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
package oo.atom.codegen.bytebuddy.task.hashcode;

import java.lang.reflect.Method;
import java.util.Objects;
import javaslang.collection.List;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import oo.atom.codegen.bytebuddy.task.sm.StackManipulationTask;
import oo.atom.codegen.bytebuddy.task.sm.result.SmtrSuccess;
import oo.atom.codegen.bytebuddy.task.sm.result.StackManipulationTaskResult;

/**
 *
 * @author Kapralov Sergey
 */
public class SmtHashCode implements StackManipulationTask {

    private static final Method OBJECTS_HASH;

    static {
        try {
            OBJECTS_HASH = Objects.class.getMethod("hash", Object[].class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private final TypeDescription type;

    public SmtHashCode(TypeDescription type) {
        this.type = type;
    }

    @Override
    public StackManipulationTaskResult result() {
        List<StackManipulation> stackManipulations = List.empty();

        for (FieldDescription field : type.getDeclaredFields()) {
            stackManipulations = stackManipulations.append(
                    new StackManipulation.Compound(
                            MethodVariableAccess.loadThis(),
                            FieldAccess.forField(field).read()
                    )
            );
        }

        stackManipulations = List.of(
            ArrayFactory.forType(TypeDescription.Generic.OBJECT).withValues(
                stackManipulations.toJavaList()
            )
        ).append(
            new StackManipulation.Compound(
                MethodInvocation.invoke(new MethodDescription.ForLoadedMethod(OBJECTS_HASH)),
                MethodReturn.INTEGER
            )
        );
        
        return new SmtrSuccess(new StackManipulation.Compound(stackManipulations.toJavaList()));
    }
}
