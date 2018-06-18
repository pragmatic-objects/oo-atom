package com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt;

import net.bytebuddy.implementation.bytecode.StackManipulation;

/**
 * Inferred {@link StackManipulationToken}
 *
 * @author Kapralov Sergey
 */
public class SmtInferred implements StackManipulationToken {
    private final Inference inference;

    /**
     * Ctor.
     *
     * @param inference {@link StackManipulationToken} inference
     */
    public SmtInferred(Inference inference) {
        this.inference = inference;
    }

    @Override
    public final StackManipulation stackManipulation() {
        return inference.stackManipulationToken().stackManipulation();
    }
}
