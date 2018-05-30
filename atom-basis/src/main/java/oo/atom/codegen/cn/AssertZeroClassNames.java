package oo.atom.codegen.cn;

import oo.atom.tests.Assertion;
import org.assertj.core.api.Assertions;

/**
 * Assertion that passes if {@link ClassNames} set is empty.
 *
 * @author Kapralov Sergey
 */
public class AssertZeroClassNames implements Assertion {
    private final ClassNames classNames;

    /**
     * Ctor.
     *
     * @param classNames {@link ClassNames} under test
     */
    public AssertZeroClassNames(ClassNames classNames) {
        this.classNames = classNames;
    }

    @Override
    public final void check() throws Exception {
        Assertions.assertThat(classNames.classNames()).isEmpty();
    }
}
