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
import javaslang.control.Try;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.Label;
import oo.atom.codegen.bytebuddy.branching.BIsNull;
import oo.atom.codegen.bytebuddy.branching.BMark;
import oo.atom.task.result.TaskResult;
import oo.atom.task.result.TrBind;

class SmtIfNullTaskResult implements TaskResult<StackManipulation> {

    private final boolean isTrue;
    private final StackManipulation sm;

    public SmtIfNullTaskResult(boolean isTrue, StackManipulation sm) {
        this.isTrue = isTrue;
        this.sm = sm;
    }
    
    @Override
    public final Try<StackManipulation> outcome() {
        final Label checkEnd = new Label();
        return Try.success(
            new StackManipulation.Compound(
                new BIsNull(isTrue, checkEnd),
                sm,
                new BMark(checkEnd)
            )
        );
    }

    @Override
    public final List<String> issues() {
        return List.empty();
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class SmtIfNull extends TrBind<StackManipulation, StackManipulation> {
    public SmtIfNull(boolean isTrue, TaskResult<StackManipulation> task) {
        super(task, 
            sm -> new SmtIfNullTaskResult(isTrue, sm)
        );
    }
}
