/*
 * The MIT License
 *
 * Copyright 2018 Kapralov Sergey.
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

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

/**
 * Stack manipulation for {@link SmtInstanceOf}.
 *
 * @author Kapralov Sergey
 */
class InstanceOfStackManipulation implements StackManipulation {
    private final TypeDescription type;

    /**
     * Ctor.
     *
     * @param type Type description.
     */
    public InstanceOfStackManipulation(TypeDescription type) {
        this.type = type;
    }

    @Override
    public final boolean isValid() {
        return true;
    }

    @Override
    public final StackManipulation.Size apply(MethodVisitor mv, Implementation.Context cntxt) {
        mv.visitTypeInsn(Opcodes.INSTANCEOF, type.getInternalName());
        return new StackManipulation.Size(0, 0);
    }
}

/**
 * Generate INSTANCEOF opcode.
 *
 * @author Kapralov Sergey
 */
public class SmtInstanceOf implements StackManipulationToken {
    private final TypeDescription td;

    /**
     * Ctor.
     *
     * @param td Type description.
     */
    public SmtInstanceOf(final TypeDescription td) {
        this.td = td;
    }

    @Override
    public final StackManipulation stackManipulation() {
        return new InstanceOfStackManipulation(
            td
        );
    }
}
