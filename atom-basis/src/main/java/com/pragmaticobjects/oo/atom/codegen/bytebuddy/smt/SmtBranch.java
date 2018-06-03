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

import com.pragmaticobjects.oo.atom.codegen.bytebuddy.branching.BMark;
import com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt.c.Condition;
import com.pragmaticobjects.oo.atom.r.RInferred;
import com.pragmaticobjects.oo.atom.r.Result;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.Label;

/**
 * {@link SmtBranch} instruction
 *
 * @author Kapralov Sergey
 */
class SmtBranchInference implements Result.Inference<StackManipulation> {
    private final Condition condition;
    private final StackManipulationToken successBranch;

    /**
     * Ctor.
     *
     * @param condition condition.
     * @param successBranch branch for success case.
     */
    public SmtBranchInference(Condition condition, StackManipulationToken successBranch) {
        this.condition = condition;
        this.successBranch = successBranch;
    }    

    @Override
    public final Result<StackManipulation> result() {
        Label label = new Label();
        return new SmtCombined(
            new SmtStatic(condition.branching(label)),
            successBranch,
            new SmtStatic(new BMark(label))
        );
    }
}


/**
 * Generates bytecode for branching statements.
 *
 * @author Kapralov Sergey
 */
public class SmtBranch extends RInferred<StackManipulation> implements StackManipulationToken {
    /**
     * Ctor.
     *
     * @param condition condition.
     * @param successBranch branch for success case.
     */
    public SmtBranch(Condition condition, StackManipulationToken successBranch) {
        super(
            new SmtBranchInference(
                condition,
                successBranch
            )
        );
    }
}
