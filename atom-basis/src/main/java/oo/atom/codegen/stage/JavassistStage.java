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

import javassist.ClassPool;
import javassist.CtClass;
import oo.atom.codegen.cn.ClassNames;
import oo.atom.codegen.cp.ClassPath;
import oo.atom.codegen.javassist.cps.ClassPoolSource;
import oo.atom.codegen.javassist.cps.CpsFromClassPath;
import oo.atom.codegen.javassist.plugin.Plugin;
import org.apache.commons.io.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Javassist-powered stage
 *
 * @author Kapralov Sergey
 */
public class JavassistStage implements Stage {
    private final Plugin task;

    /**
     * Ctor.
     *
     * @param task task
     */
    public JavassistStage(final Plugin task) {
        this.task = task;
    }

    @Override
    public final void apply(final ClassPath classPath, final ClassNames classNames, final Path workingDirectory) {
        try {
            final ClassPoolSource cps = new CpsFromClassPath(classPath);
            final ClassPool classPool = cps.classPool();
            final Path tempPath = Files.createTempDirectory(".atom");
            for (String className : classNames.classNames()) {
                final CtClass ctClass = classPool.get(className);
                ctClass.defrost();
                task.operateOn(ctClass, classPool);
                ctClass.writeFile(tempPath.toString());
            }
            FileUtils.copyDirectory(tempPath.toFile(), workingDirectory.toFile());
            FileUtils.deleteDirectory(tempPath.toFile());
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
