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

package oo.atom.codegen.javassist.cps;

import io.vavr.collection.List;
import javassist.ClassPool;

import java.nio.file.Path;

/**
 * {@link ClassPoolSource} for classes located in certain paths.
 *
 * @author Kapralov Sergey
 */
public class CpsWithPaths implements ClassPoolSource {
    private final ClassPoolSource cps;
    private final List<Path> paths;

    /**
     * Ctor.
     *
     * @param cps delegating {@link ClassPoolSource}
     * @param paths list of paths where classes are located.
     */
    public CpsWithPaths(final ClassPoolSource cps, final List<Path> paths) {
        this.cps = cps;
        this.paths = paths;
    }

    @Override
    public final ClassPool classPool() {
        try {
            final ClassPool classPool = cps.classPool();
            for(Path path : paths) {
                classPool.appendClassPath(path.toString());
            }
            return classPool;
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
