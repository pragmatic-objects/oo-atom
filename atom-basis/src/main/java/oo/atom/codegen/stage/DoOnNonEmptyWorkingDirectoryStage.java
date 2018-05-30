/*
 * The MIT License
 *
 * Copyright 2018 Kapralov Sergey.
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Applies stage only to non-empty woking directory
 *
 * @author Kapralov Sergey
 */
public class DoOnNonEmptyWorkingDirectoryStage implements Stage {
    private final Stage stage;

    /**
     * Ctor.
     *
     * @param stage {@link Stage}
     */
    public DoOnNonEmptyWorkingDirectoryStage(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public final void apply(final ClassPath classPath, final ClassNames classNames, final Path workingDirectory) {
        try {
            if (Files.list(workingDirectory).findAny().isPresent()) {
                stage.apply(classPath, classNames, workingDirectory);
            }
        } catch(IOException ex) {
            throw new RuntimeException("Failed attempt to access path " + workingDirectory.toString(), ex);
        }
    }
}
