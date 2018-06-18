package com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt;

import com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt.internals.MethodVisitorRecorder;
import com.pragmaticobjects.oo.atom.tests.Assertion;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.MethodVisitor;
import org.easymock.EasyMock;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Asserts that the stack manipulation token produces the expected bytecode sequence.
 *
 * @author Kapralov Sergey
 */
public class AssertTokenToGenerateAByteCode implements Assertion {
    private final StackManipulationToken smt;
    private final Consumer<MethodVisitor> pattern;

    /**
     * Ctor.
     *
     * @param smt Stack manipulation token under test
     * @param pattern Expected pattern.
     */
    public AssertTokenToGenerateAByteCode(StackManipulationToken smt, Consumer<MethodVisitor> pattern) {
        this.smt = smt;
        this.pattern = pattern;
    }

    @Override
    public final void check() throws Exception {
        Implementation.Context implementationContext = EasyMock.createMock(
            Implementation.Context.class
        );

        final MethodVisitorRecorder patternMvr = new MethodVisitorRecorder();
        final MethodVisitorRecorder actualMvr = new MethodVisitorRecorder();

        pattern.accept(patternMvr);
        System.out.println("Expectation:");
        patternMvr.trace();

        smt.stackManipulation().apply(actualMvr, implementationContext);
        System.out.println("Reality:");
        actualMvr.trace();

        assertThat(actualMvr).isEqualTo(patternMvr);
    }
}
