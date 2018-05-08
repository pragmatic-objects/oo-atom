package oo.atom.codegen.cn;

import oo.atom.tests.Assertion;
import org.assertj.core.api.Assertions;

public class AssertZeroClassNames implements Assertion {
    private final ClassNames classNames;

    public AssertZeroClassNames(ClassNames classNames) {
        this.classNames = classNames;
    }

    @Override
    public final void check() throws Exception {
        Assertions.assertThat(classNames.classNames()).isEmpty();
    }
}
