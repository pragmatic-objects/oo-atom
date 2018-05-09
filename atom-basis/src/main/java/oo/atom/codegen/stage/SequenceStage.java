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

import io.vavr.collection.List;
import oo.atom.codegen.cn.ClassNames;
import oo.atom.codegen.cp.ClassPath;

import java.nio.file.Path;

/**
 * Complex stage, consisting of several steps, executed sequentially.
 *
 * @author Kapralov Sergey
 */
public class SequenceStage implements Stage {
    private final List<Stage> substages;

    public SequenceStage(List<Stage> substages) {
        this.substages = substages;
    }

    public SequenceStage(Stage... substages) {
        this(List.of(substages));
    }

    @Override
    public final void apply(ClassPath classPath, ClassNames classNames, Path workingDirectory) {
        for(Stage stage : substages) {
            stage.apply(classPath, classNames, workingDirectory);
        }
    }
}
