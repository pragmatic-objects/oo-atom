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

package oo.atom.codegen.stage;

import oo.atom.codegen.cn.ClassNames;
import oo.atom.codegen.cp.ClassPath;

import java.nio.file.Path;

/**
 * Executes stage, overriding the classes it is applied on.
 *
 * @author Kapralov Sergey
 */
public class OverrideClassesStage implements Stage {
    private final Stage stage;
    private final ClassNames classNames;

    /**
     * Ctor.
     * @param stage Stage.
     * @param classNames Classes for this stage to apply on.
     */
    public OverrideClassesStage(final Stage stage, final ClassNames classNames) {
        this.stage = stage;
        this.classNames = classNames;
    }

    @Override
    public final void apply(final ClassPath classPath, final ClassNames classNames, final Path workingDirectory) {
        stage.apply(classPath, this.classNames, workingDirectory);
    }
}
