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
package oo.atom.codegen.bytebuddy.task.equals;

import javaslang.collection.List;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import oo.atom.anno.api.task.Task;
import oo.atom.codegen.bytebuddy.task.sm.SmtCombined;
import oo.atom.codegen.bytebuddy.task.sm.SmtDerived;
import oo.atom.codegen.bytebuddy.task.sm.StackManipulationTaskDerivative;
import oo.atom.codegen.bytebuddy.task.sm.result.StackManipulationTaskResult;

/**
 *
 * @author Kapralov Sergey
 */
public class SmtFieldsEquality extends SmtDerived {

    public SmtFieldsEquality(final TypeDescription type) {
        super(new StackManipulationTaskDerivative() {
            @Override
            public Task<StackManipulation, StackManipulationTaskResult> task() {
                return new SmtCombined(
                        List.of(type)
                            .flatMap(TypeDescription::getDeclaredFields)
                            .filter(f -> !f.isStatic())
                            .map(f -> new SmtFieldEquality(type, f))
                            .toJavaArray(SmtFieldEquality.class)
                );
            }
        });
    }
}
