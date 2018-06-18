package com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt;

import com.pragmaticobjects.oo.atom.tests.Assertion;
import org.assertj.core.api.Assertions;

/**
 * Asserts that the {@link StackManipulationToken} will fail on attempt to generate
 * bytecode.
 *
 * @author Kapralov Sergey
 */
public class AssertTokenToFail implements Assertion {
    private final StackManipulationToken token;

    /**
     * Ctor.
     *
     * @param token Token under test
     */
    public AssertTokenToFail(StackManipulationToken token) {
        this.token = token;
    }

    @Override
    public final void check() throws Exception {
        Assertions.assertThatThrownBy(() -> token.stackManipulation()).isNotNull();
    }
}
