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

import java.lang.reflect.Method;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.jar.asm.Label;
import oo.atom.codegen.bytebuddy.branching.BIsZero;
import oo.atom.codegen.bytebuddy.branching.BMark;
import oo.atom.task.result.TaskResult;
import oo.atom.task.result.TaskResultTransition;
import oo.atom.task.result.TrBind;
import oo.atom.task.result.TrSuccess;

class TrtIfEqual implements TaskResultTransition<StackManipulation, StackManipulation> {
    private final static Method EQUALS;

    static {
        try {
            EQUALS = Object.class.getMethod("equals", Object.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private final boolean isTrue;

    public TrtIfEqual(boolean isTrue) {
        this.isTrue = isTrue;
    }

    @Override
    public final TaskResult<StackManipulation> transitionResult(StackManipulation sm) {
        final Label checkEnd = new Label();
        return new TrSuccess<>(
            new StackManipulation.Compound(
                MethodInvocation.invoke(new MethodDescription.ForLoadedMethod(EQUALS)),
                new BIsZero(!isTrue, checkEnd),
                sm,
                new BMark(checkEnd)
            )
        );
    }
}

/**
 *
 * @author Kapralov Sergey
 */
class SmtIfEqual extends TrBind<StackManipulation, StackManipulation> {
    public SmtIfEqual(boolean isTrue, TaskResult<StackManipulation> task) {
        super(
            task,
            new TrtIfEqual(isTrue)
        );
    }
}
