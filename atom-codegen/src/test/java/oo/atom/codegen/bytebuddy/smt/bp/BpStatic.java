package oo.atom.codegen.bytebuddy.smt.bp;

import net.bytebuddy.implementation.bytecode.StackManipulation;

public class BpStatic implements BytecodePattern {
    private final StackManipulation sm;

    public BpStatic(StackManipulation sm) {
        this.sm = sm;
    }

    @Override
    public final StackManipulation stackManipulation() {
        return sm;
    }
}
