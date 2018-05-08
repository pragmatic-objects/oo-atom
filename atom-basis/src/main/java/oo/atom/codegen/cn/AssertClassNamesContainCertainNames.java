package oo.atom.codegen.cn;

import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import oo.atom.tests.Assertion;
import org.assertj.core.api.Assertions;

public class AssertClassNamesContainCertainNames implements Assertion {
    private final ClassNames classNames;
    private final Set<String> namesToCheck;

    public AssertClassNamesContainCertainNames(ClassNames classNames, Set<String> namesToCheck) {
        this.classNames = classNames;
        this.namesToCheck = namesToCheck;
    }

    public AssertClassNamesContainCertainNames(ClassNames classNames, String namesToCheck) {
        this(classNames, HashSet.of(namesToCheck));
    }

    @Override
    public final void check() throws Exception {
        List<String> extractedNames = classNames.classNames();
        Assertions.assertThat(namesToCheck).containsAll(extractedNames);
    }
}
