package com.pragmaticobjects.oo.atom.codegen.javassist.cps;

import com.pragmaticobjects.oo.atom.tests.Assertion;
import javassist.NotFoundException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Asserts that attempt to load a certain class from
 * {@link javassist.ClassPool}, obtained from
 * {@link ClassPoolSource} under test, fails.
 *
 * @author Kapralov Sergey
 */
public class AssertClassPoolSourceCannotLoadAClass implements Assertion {
    private final ClassPoolSource source;
    private final String className;

    /**
     * Ctor.
     *
     * @param source {@link ClassPoolSource} under test.
     * @param className class name to load.
     */
    public AssertClassPoolSourceCannotLoadAClass(ClassPoolSource source, String className) {
        this.source = source;
        this.className = className;
    }

    @Override
    public final void check() throws Exception {
        assertThatThrownBy(() -> source.classPool().get(className)).isInstanceOf(NotFoundException.class);
    }
}
