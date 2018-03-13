package oo.atom.codegen.javassist.cps;

import oo.atom.tests.Assertion;

import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Asserts that attempt to load a certain class from
 * {@link javassist.ClassPool}, obtained from
 * {@link ClassPoolSource} under test, succeeds.
 *
 * @author Kapralov Sergey
 */
public class AssertClassPoolSourceCanLoadAClass implements Assertion {
    private final ClassPoolSource source;
    private final String className;

    /**
     * Ctor.
     *
     * @param source {@link ClassPoolSource} under test.
     * @param className class name to load.
     */
    public AssertClassPoolSourceCanLoadAClass(ClassPoolSource source, String className) {
        this.source = source;
        this.className = className;
    }

    @Override
    public final void check() throws Exception {
        assertThatCode(() -> source.classPool().get(className)).doesNotThrowAnyException();
    }
}
