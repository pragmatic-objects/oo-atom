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
package oo.atom.codegen.bytebuddy.branching;

import net.bytebuddy.jar.asm.Label;

/**
 * {@link BIfAcmp} inference.
 *
 * @author Kapralov Sergey
 */
class BIfAcmpInference implements BranchingInference {
    private final boolean equals;
    private final Label label;

    public BIfAcmpInference(boolean equals, Label label) {
        this.equals = equals;
        this.label = label;
    }

    @Override
    public final Branching branching() {
        return equals ? new BIfAcmpEq(label) : new BIfAcmpNe(label);
    }
}

/**
 * IF_ACMP* branching instance.
 *
 * @author Kapralov Sergey
 */
public class BIfAcmp extends BInferred implements Branching {
    /**
     * Ctor.
     *
     * @param equals use IF_ACMPEQ or IF_ACMPNE opcode?
     * @param label opcode's target label.
     */
    public BIfAcmp(boolean equals, Label label) {
        super(
            new BIfAcmpInference(
                equals, 
                label
            )
        );
    }
}
