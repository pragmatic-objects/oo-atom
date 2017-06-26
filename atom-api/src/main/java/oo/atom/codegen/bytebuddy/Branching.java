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
package oo.atom.codegen.bytebuddy;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

/**
 *
 * @author Kapralov Sergey
 */
public interface Branching extends StackManipulation {
    interface Derivative {
        Branching branching();
    }
    
    abstract class Abstract implements Branching {
        private final int sizeImpact;
        private final int maxSize;
        private final Label label;
        private final int opcode;

        public Abstract(int sizeImpact, int maxSize, Label label, int opcode) {
            this.sizeImpact = sizeImpact;
            this.maxSize = maxSize;
            this.label = label;
            this.opcode = opcode;
        }
        
        @Override
        public final StackManipulation.Size apply(MethodVisitor mv, Implementation.Context ctx) {
            mv.visitJumpInsn(opcode, label);
            return new StackManipulation.Size(sizeImpact, maxSize);
        }
        
        @Override
        public final boolean isValid() {
            return true;
        }
    }
    
    class Derived implements Branching {
        private final Derivative derivative;

        public Derived(Derivative derivative) {
            this.derivative = derivative;
        }

        @Override
        public final boolean isValid() {
            return derivative.branching().isValid();
        }

        @Override
        public final Size apply(MethodVisitor arg0, Implementation.Context arg1) {
            return derivative.branching().apply(arg0, arg1);
        }
    }

    class Mark implements Branching {
        private final Label label;

        public Mark(Label label) {
            this.label = label;
        }

        @Override
        public final StackManipulation.Size apply(MethodVisitor mv, Implementation.Context ctx) {
            mv.visitLabel(label);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            return new StackManipulation.Size(0, 0);
        }
        
        @Override
        public final boolean isValid() {
            return true;
        }
    }
    
    class IfNe extends Abstract {
        public IfNe(Label label) {
            super(-1, 0, label, Opcodes.IFNE);
        }
    }
    
    class IfEq extends Abstract {
        public IfEq(Label label) {
            super(-1, 0, label, Opcodes.IFEQ);
        }
    }
    
    class IsZero extends Derived implements Branching {
        public IsZero(boolean isTrue, Label label) {
            super(new Derivative() {
                @Override
                public Branching branching() {
                    return isTrue ? new IfEq(label) : new IfNe(label);
                }
            });
        }
    }

    class IfIcmpNe extends Abstract {
        public IfIcmpNe(Label label) {
            super(-2, 0, label, Opcodes.IF_ICMPNE);
        }
    }

    class IfIcmpEq extends Abstract {
        public IfIcmpEq(Label label) {
            super(-2, 0, label, Opcodes.IF_ICMPEQ);
        }
    }
    
    class IfIcmp extends Derived implements Branching {
        public IfIcmp(boolean equals, Label label) {
            super(new Derivative() {
                @Override
                public Branching branching() {
                    return equals ? new IfIcmpEq(label) : new IfIcmpNe(label);
                }
            });
        }
    }

    class IfAcmpNe extends Abstract {
        public IfAcmpNe(Label label) {
            super(-2, 0, label, Opcodes.IF_ACMPNE);
        }
    }

    class IfAcmpEq extends Abstract {
        public IfAcmpEq(Label label) {
            super(-2, 0, label, Opcodes.IF_ACMPEQ);
        }
    }
    
    class IfAcmp extends Derived implements Branching {
        public IfAcmp(boolean equals, Label label) {
            super(new Derivative() {
                @Override
                public Branching branching() {
                    return equals ? new IfAcmpEq(label) : new IfAcmpNe(label);
                }
            });
        }
    }

    class IfNonNull extends Abstract {
        public IfNonNull(Label label) {
            super(-1, 0, label, Opcodes.IFNONNULL);
        }
    }
    
    class IfNull extends Abstract {
        public IfNull(Label label) {
            super(-1, 0, label, Opcodes.IFNULL);
        }
    }
    
    class IsNull extends Derived {
        public IsNull(boolean isTrue, Label label) {
            super(new Derivative() {
                @Override
                public Branching branching() {
                    return isTrue ? new IfNull(label) : new IfNonNull(label);
                }
            });
        }
        
    }
}