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
package oo.atom.instrumentation;

import io.vavr.collection.List;
import oo.atom.codegen.javapoet.JavapoetSource;

import java.nio.file.Path;

/**
 * Instrumentation, which generates certain source code modules.
 *
 * @author Kapralov Sergey
 */
public class GenerateSources implements Instrumentation {
    private final Path workingDirectory;
    private final List<JavapoetSource> sources;

    /**
     * Ctor.
     *
     * @param workingDirectory Working directory.
     * @param sources Sources to generate.
     */
    public GenerateSources(Path workingDirectory, List<JavapoetSource> sources) {
        this.workingDirectory = workingDirectory;
        this.sources = sources;
    }

    /**
     * Ctor.
     *
     * @param workingDirectory Working directory.
     * @param sources Sources to generate.
     */
    public GenerateSources(Path workingDirectory, JavapoetSource... sources) {
        this(workingDirectory, List.of(sources));
    }

    @Override
    public final void apply() {
        sources.forEach(jp -> jp.saveIn(workingDirectory));
    }
}
