package com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt;

import net.bytebuddy.implementation.bytecode.StackManipulation;

/**
 * Static {@link StackManipulationToken}
 *
 * @author Kapralov Sergey
 */
public class SmtStatic implements StackManipulationToken {
    private final StackManipulation stackManipulation;

    /**
     * Ctor.
     *
     * @param stackManipulation Stack manipulation to produce.
     */
    public SmtStatic(StackManipulation stackManipulation) {
        this.stackManipulation = stackManipulation;
    }

    @Override
    public final StackManipulation stackManipulation() {
        return stackManipulation;
    }
}
