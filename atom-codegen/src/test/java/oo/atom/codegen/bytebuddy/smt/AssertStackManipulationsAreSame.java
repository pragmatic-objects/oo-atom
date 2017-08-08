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
import net.bytebuddy.jar.asm.MethodVisitor;
import oo.atom.tests.Assertion;
import org.easymock.EasyMock;

/**
 *
 * @author Kapralov Sergey
 */
public class AssertStackManipulationsAreSame implements Assertion {
    private final String description;
    private final StackManipulation entity;
    private final StackManipulation pattern;

    public AssertStackManipulationsAreSame(String description, StackManipulation entity, StackManipulation pattern) {
        this.description = description;
        this.entity = entity;
        this.pattern = pattern;
    }

    @Override
    public final String description() {
        return description;
    }
    
    @Override
    public final void check() throws Exception {
        MethodVisitor methodVisitor = EasyMock.createStrictMock(
                MethodVisitor.class
        );
        Implementation.Context implementationContext = EasyMock.createMock(
                Implementation.Context.class
        );
        
        // Expect
        pattern.apply(methodVisitor, implementationContext);
        EasyMock.replay(methodVisitor);
        
        entity.apply(methodVisitor, implementationContext);
        EasyMock.verify(methodVisitor);
    }
}
