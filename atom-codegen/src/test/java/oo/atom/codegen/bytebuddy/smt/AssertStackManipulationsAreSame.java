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

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import oo.atom.tests.Assertion;
import org.easymock.EasyMock;

/**
 * Asserts that both stack manipulations produces the same bytecode. More strictly saying,
 * asserts that the stack manipulations do the similar set of calls on ASM's {@link net.bytebuddy.jar.asm.MethodVisitor}
 * on {@link StackManipulation#apply(net.bytebuddy.jar.asm.MethodVisitor, net.bytebuddy.implementation.Implementation.Context)} is invoked.
 * 
 * @author Kapralov Sergey
 */
public class AssertStackManipulationsAreSame extends AssertStackManipulationProducesExpectedBytecode implements Assertion {
    /**
     * Ctor.
     * 
     * @param description Assertion description
     * @param sm Stack manipulation to assert on.
     * @param sm2 Stack manipulation to compare with
     */
    public AssertStackManipulationsAreSame(String description, StackManipulation sm, StackManipulation sm2) {
        super(
            description,
            sm,
            mv -> {
                Implementation.Context ctx = EasyMock.mock(Implementation.Context.class);
                sm2.apply(mv, ctx);
            }
        );
    }
}
