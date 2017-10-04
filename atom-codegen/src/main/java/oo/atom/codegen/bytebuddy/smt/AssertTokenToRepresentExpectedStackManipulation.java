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

import java.util.function.Supplier;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import oo.atom.tests.Assertion;
import oo.atom.tests.AssertInferred;

/**
 * Inference for {@link oo.atom.codegen.bytebuddy.smt.AssertTokenToRepresentExpectedStackManipulation} assertion
 * 
 * @author Kapralov Sergey
 */
class AssertTokenToRepresentExpectedStackManipulationInference implements Assertion.Inference {
    private final StackManipulationToken smt;
    private final Supplier<StackManipulation> pattern;

    /**
     * Ctor.
     * 
     * @param smt
     * @param pattern 
     */
    public AssertTokenToRepresentExpectedStackManipulationInference(StackManipulationToken smt, Supplier<StackManipulation> pattern) {
        this.smt = smt;
        this.pattern = pattern;
    }

    @Override
    public final Assertion assertion() {
        return new AssertStackManipulationsAreSame(
            smt.value().get(),
            pattern.get()
        );
    }
}

/**
 * Assert that stack manipulation token represents expected bytecode sequence.
 * 
 * @author Kapralov Sergey
 */
public class AssertTokenToRepresentExpectedStackManipulation extends AssertInferred implements Assertion {
    /**
     * Ctor.
     * 
     * @param smt Stack manipulation to assert on
     * @param pattern Bytecode pattern to expect
     */
    public AssertTokenToRepresentExpectedStackManipulation(StackManipulationToken smt, Supplier<StackManipulation> pattern) {
        super(
            new AssertTokenToRepresentExpectedStackManipulationInference(
                smt,
                pattern
            )
        );
    }
}
