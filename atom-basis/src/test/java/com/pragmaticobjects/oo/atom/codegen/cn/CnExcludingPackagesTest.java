package com.pragmaticobjects.oo.atom.codegen.cn;

import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;

/**
 * Tests suite for {@link CnExcludingPackages}
 *
 * @author Kapralov Sergey
 */
class CnExcludingPackagesTest extends TestsSuite {
    /**
     * Ctor.
     */
    public CnExcludingPackagesTest() {
        super(
            new TestCase(
                "Excluding nothing",
                new AssertClassNamesContainCertainNames(
                    new CnExcludingPackages(
                        new CnExplicit(
                            "com.package1.Foo",
                            "com.package2.Bar"
                        )
                    ),
                    "com.package1.Foo",
                    "com.package2.Bar"
                )
            ),
            new TestCase(
                "Excluding a single package",
                new AssertClassNamesContainCertainNames(
                    new CnExcludingPackages(
                        new CnExplicit(
                            "com.package1.Foo",
                            "com.package2.Bar"
                        ),
                        "com.package1"
                    ),
                    "com.package2.Bar"
                )
            ),
            new TestCase(
                "Excluding non-existing package",
                new AssertClassNamesContainCertainNames(
                    new CnExcludingPackages(
                        new CnExplicit(
                            "com.package1.Foo",
                            "com.package2.Bar"
                        ),
                        "com.package"
                    ),
                    "com.package1.Foo",
                    "com.package2.Bar"
                )
            )
        );
    }
}