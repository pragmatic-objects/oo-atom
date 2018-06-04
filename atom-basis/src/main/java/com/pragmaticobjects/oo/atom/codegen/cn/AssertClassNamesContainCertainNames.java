package com.pragmaticobjects.oo.atom.codegen.cn;

import com.pragmaticobjects.oo.atom.tests.Assertion;
import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import org.assertj.core.api.Assertions;

/**
 * Assertion that passes if {@link ClassNames} contain certain class names
 *
 * @author Kapralov Sergey
 */
public class AssertClassNamesContainCertainNames implements Assertion {
    private final ClassNames classNames;
    private final Set<String> namesToCheck;

    /**
     * Ctor.
     *
     * @param classNames {@link ClassNames} under test
     * @param namesToCheck expected names
     */
    public AssertClassNamesContainCertainNames(ClassNames classNames, Set<String> namesToCheck) {
        this.classNames = classNames;
        this.namesToCheck = namesToCheck;
    }

    /**
     * Ctor.
     *
     * @param classNames {@link ClassNames} under test
     * @param namesToCheck expected names
     */
    public AssertClassNamesContainCertainNames(ClassNames classNames, String... namesToCheck) {
        this(classNames, HashSet.of(namesToCheck));
    }

    @Override
    public final void check() throws Exception {
        List<String> extractedNames = classNames.classNames();
        Assertions.assertThat(namesToCheck).containsAll(extractedNames);
    }
}
