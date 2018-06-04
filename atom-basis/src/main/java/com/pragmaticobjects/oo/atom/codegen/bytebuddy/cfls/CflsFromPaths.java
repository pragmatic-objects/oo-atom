/*
 * The MIT License
 *
 * Copyright 2017 Kapralov Sergey.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.pragmaticobjects.oo.atom.codegen.bytebuddy.cfls;

import io.vavr.collection.List;

import java.nio.file.Path;

/**
 * {@link CflsFromPaths} inference.
 *
 * @author Kapralov Sergey
 */
class CflsFromPathsInference implements ClassFileLocatorSource.Inference {
    private final List<Path> paths;

    /**
     * Ctor.
     *
     * @param paths Paths
     */
    public CflsFromPathsInference(final List<Path> paths) {
        this.paths = paths;
    }

    @Override
    public final ClassFileLocatorSource classFileLocatorSource() {
        return paths
            .<ClassFileLocatorSource>map(CflsFromPath::new)
            .transform(CflsCompound::new);
    }
}

/**
 * Source from {@link net.bytebuddy.dynamic.ClassFileLocator}, made from provided paths.
 *
 * @author Kapralov Sergey
 */
public class CflsFromPaths extends CflsInferred implements ClassFileLocatorSource {
    /**
     * Ctor.
     *
     * @param paths Paths
     */
    public CflsFromPaths(final List<Path> paths) {
        super(
            new CflsFromPathsInference(
                paths
            )
        );
    }
}
