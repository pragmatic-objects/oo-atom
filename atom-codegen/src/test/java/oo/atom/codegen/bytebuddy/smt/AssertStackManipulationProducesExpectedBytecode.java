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

import java.util.function.Consumer;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;
import oo.atom.codegen.bytebuddy.smt.internals.MethodVisitorRecorder;
import oo.atom.tests.Assertion;
import static org.assertj.core.api.Assertions.assertThat;
import org.easymock.EasyMock;

/**
 * Asserts that the stack manipulation produces the expected bytecode sequence. 
 * More strictly saying, asserts that the call `apply` on stack sequence causes 
 * the same invocations on ASM's {@link net.bytebuddy.jar.asm.MethodVisitor} as 
 * in the pre-defined pattern.
 * 
 * @author Kapralov Sergey
 */
public class AssertStackManipulationProducesExpectedBytecode implements Assertion {
    /**
     * Assertion description.
     */
    private final String description;
    /**
     * Stack manipulation to assert on.
     */
    private final StackManipulation sm;
    /**
     * Expected pattern.
     */
    private final Consumer<MethodVisitor> pattern;

    /**
     * Ctor.
     * 
     * @param description
     * @param sm
     * @param pattern 
     */
    public AssertStackManipulationProducesExpectedBytecode(String description, StackManipulation sm, Consumer<MethodVisitor> pattern) {
        this.description = description;
        this.sm = sm;
        this.pattern = pattern;
    }

    @Override
    public final String description() {
        return description;
    }

    @Override
    public final void check() throws Exception {
        Implementation.Context implementationContext = EasyMock.createMock(
            Implementation.Context.class
        );
        
        // Expect
        final MethodVisitorRecorder patternMvr = new MethodVisitorRecorder();
        final MethodVisitorRecorder actualMvr = new MethodVisitorRecorder();
        
        pattern.accept(patternMvr);
        patternMvr.trace();
        
        sm.apply(actualMvr, implementationContext);
        actualMvr.trace();
        
        assertThat(actualMvr).isEqualTo(patternMvr);
    }
}
