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
package com.pragmaticobjects.oo.atom.codegen.bytebuddy.branching;

import net.bytebuddy.jar.asm.Label;

/**
 * {@link BIfIcmp} inference.
 *
 * @author Kapralov Sergey
 */
class BIfIcmpInference implements BranchingInference {
    private final boolean equals;
    private final Label label;

    /**
     * Ctor.
     * @param equals IF_ICMPEQ or IF_ICMPNE?
     * @param label Opcode's label
     */
    public BIfIcmpInference(boolean equals, Label label) {
        this.equals = equals;
        this.label = label;
    }

    @Override
    public final Branching branching() {
        return equals ? new BIfIcmpEq(label) : new BIfIcmpNe(label);
    }
}

/**
 * IF_ICMP* branching inference.
 *
 * @author Kapralov Sergey
 */
public class BIfIcmp extends BInferred implements Branching {
    /**
     * Ctor.
     * @param equals IF_ICMPEQ or IF_ICMPNE?
     * @param label Opcode's label
     */
    public BIfIcmp(boolean equals, Label label) {
        super(new BIfIcmpInference(equals, label));
    }
    
}
