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

import oo.atom.codegen.bcel.plugin.Plugin;
import oo.atom.codegen.cn.ClassNames;
import oo.atom.codegen.cp.ClassPath;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.util.ClassPathRepository;


import java.nio.file.Path;

/**
 * BCEL-backed stage.
 *
 * @author Kapralov Sergey
 */
public class BcelStage implements Stage {
    private final Plugin plugin;

    /**
     * Ctor
     * @param plugin {@link BcelStage}
     */
    public BcelStage(final Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public final void apply(final ClassPath classPath, final ClassNames classNames, final Path workingDirectory) {
        final String cpString = classPath.paths()
            .map(Object::toString)
            .reduce((s1, s2) -> s1 + ":" + s2);

        final ClassPathRepository classPathRepository = new ClassPathRepository(
            new org.apache.bcel.util.ClassPath(
                cpString
            )
        );

        try {
            for (String className : classNames.classNames()) {
                final JavaClass javaClass = classPathRepository.loadClass(className);
                final ClassGen classGen = new ClassGen(javaClass);
                plugin.operateOn(classGen,javaClass, classPathRepository);
            }
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
