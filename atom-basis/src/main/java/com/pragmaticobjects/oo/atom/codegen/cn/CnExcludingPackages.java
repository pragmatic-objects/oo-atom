package com.pragmaticobjects.oo.atom.codegen.cn;

import io.vavr.collection.List;

/**
 * Class names list, excluding classes from certain packages
 *
 * @author Kapralov Sergey
 */
public class CnExcludingPackages implements ClassNames {
    private final ClassNames delegate;
    private final List<String> packages;

    /**
     * Ctor.
     *
     * @param delegate Source class names
     * @param packages Packages to exclude
     */
    public CnExcludingPackages(ClassNames delegate, List<String> packages) {
        this.delegate = delegate;
        this.packages = packages;
    }

    /**
     * Ctor.
     *
     * @param delegate Source class names
     * @param packages Packages to exclude
     */
    public CnExcludingPackages(ClassNames delegate, String... packages) {
        this(
            delegate,
            List.of(packages)
        );
    }

    @Override
    public final List<String> classNames() {
        List<String> names = this.delegate.classNames();
        for(String pkg : packages) {
            names = names.filter(cn -> cn.startsWith(pkg + "."));
        }
        return names;
    }
}
