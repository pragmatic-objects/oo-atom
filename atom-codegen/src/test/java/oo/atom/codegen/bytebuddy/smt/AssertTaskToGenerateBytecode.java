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

import net.bytebuddy.implementation.bytecode.StackManipulation;
import oo.atom.codegen.bytebuddy.smt.bp.BytecodePattern;
import oo.atom.tests.Assertion;
import oo.atom.tests.InferredAssertion;
import oo.atom.r.Result;

class AssertTaskToGenerateBytecodeInference implements Assertion.Inference {
    private final String description;
    private final Result<StackManipulation> task;
    private final BytecodePattern sample;

    public AssertTaskToGenerateBytecodeInference(String description, Result<StackManipulation> task, BytecodePattern sample) {
        this.description = description;
        this.task = task;
        this.sample = sample;
    }

    @Override

    public final Assertion assertion() {
        return new AssertStackManipulationsAreSame(
            description,
            task.outcome().get(),
            sample
        );
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class AssertTaskToGenerateBytecode extends InferredAssertion implements Assertion {

    public AssertTaskToGenerateBytecode(String description, Result<StackManipulation> task, BytecodePattern sample) {
        super(
            new AssertTaskToGenerateBytecodeInference(
                description,
                task,
                sample
            )
        );
    }
}
