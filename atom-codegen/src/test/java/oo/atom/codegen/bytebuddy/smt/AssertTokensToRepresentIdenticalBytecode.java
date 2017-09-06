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

import oo.atom.tests.Assertion;
import oo.atom.tests.InferredAssertion;

/**
 * Inference for {@link oo.atom.codegen.bytebuddy.smt.AssertTokensToRepresentIdenticalBytecode} assertion
 * 
 * @author Kapralov Sergey
 */
class AssertTokensToRepresentIdenticalBytecodeInference implements Assertion.Inference {
    private final String description;
    private final StackManipulationToken smt;
    private final StackManipulationToken smt2;

    /**
     * Ctor.
     * 
     * @param description assertion description
     * @param smt stack manipulation token to assert on
     * @param smt2 stack manipulation token to compare
     */
    public AssertTokensToRepresentIdenticalBytecodeInference(String description, StackManipulationToken smt, StackManipulationToken smt2) {
        this.description = description;
        this.smt = smt;
        this.smt2 = smt2;
    }

    @Override
    public final Assertion assertion() {
        return new AssertStackManipulationsAreSame(
            description,
            smt.outcome().get(),
            smt2.outcome().get()
        );
    }
}


/**
 * Asserts that two stack manipulation tokens represent the same bytecode sequence.
 * 
 * @author Kapralov Sergey
 */
public class AssertTokensToRepresentIdenticalBytecode extends InferredAssertion {
    /**
     * Ctor.
     * 
     * @param description assertion description
     * @param smt stack manipulation token to assert on
     * @param smt2 stack manipulation token to compare
     */
    public AssertTokensToRepresentIdenticalBytecode(String description, StackManipulationToken smt, StackManipulationToken smt2) {
        super(
            new AssertTokensToRepresentIdenticalBytecodeInference(
                description,
                smt,
                smt2
            )
        );
    }
}
