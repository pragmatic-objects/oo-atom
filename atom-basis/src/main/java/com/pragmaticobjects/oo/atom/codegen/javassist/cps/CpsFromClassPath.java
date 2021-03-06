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

package com.pragmaticobjects.oo.atom.codegen.javassist.cps;

import com.pragmaticobjects.oo.atom.codegen.cp.ClassPath;

/**
 * {@link CpsFromClassPath} inference.
 *
 * @author Kapralov Sergey
 */
class CpsFromClassPathInference implements ClassPoolSource.Inference {
    private final ClassPath classPath;

    /**
     * Ctor.
     *
     * @param classPath class path.
     */
    public CpsFromClassPathInference(final ClassPath classPath) {
        this.classPath = classPath;
    }

    @Override
    public final ClassPoolSource classPoolSource() {
        return new CpsWithPaths(
            new CpsDefault(),
            classPath.paths()
        );
    }
}

/**
 * {@link javassist.ClassPool} source for classes located in certain {@link ClassPath}.
 *
 * @author Kapralov Sergey
 */
public class CpsFromClassPath extends CpsInferred implements ClassPoolSource {
    /**
     * Ctor.
     *
     * @param classPath class path.
     */
    public CpsFromClassPath(final ClassPath classPath) {
        super(
            new CpsFromClassPathInference(
                classPath
            )
        );
    }
}
