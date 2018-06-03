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
package com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt.c;

import com.pragmaticobjects.oo.atom.codegen.bytebuddy.branching.*;
import com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt.SmtBranch;
import net.bytebuddy.jar.asm.Label;

import java.util.function.Function;

/**
 * Enumeration for possible conditions for {@link SmtBranch}
 *
 * @author Kapralov Sergey
 */
public enum Condition {
    IS_TRUE(l -> new BIfEq(l)),
    IS_FALSE(l -> new BIfNe(l)),
    IS_REF_EQUAL(l -> new BIfAcmpEq(l)),
    IS_REF_NOT_EQUAL(l -> new BIfAcmpNe(l));

    private final Function<Label, Branching> branchingByLabel;

    /**
     * Ctor.
     * @param branchingByLabel Function for wrapping ASM label to {@link Branching}
     */
    private Condition(Function<Label, Branching> branchingByLabel) {
        this.branchingByLabel = branchingByLabel;
    }

    /**
     * @param label label for conditional opcode
     * @return {@link Branching} instance
     */
    public final Branching branching(Label label) {
        return branchingByLabel.apply(label);
    }
}
