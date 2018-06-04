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

package com.pragmaticobjects.oo.atom.codegen.stage;

import com.pragmaticobjects.oo.atom.codegen.bytebuddy.cfls.CflsCompound;
import com.pragmaticobjects.oo.atom.codegen.bytebuddy.cfls.CflsExplicit;
import com.pragmaticobjects.oo.atom.codegen.bytebuddy.cfls.CflsFromClassPath;
import com.pragmaticobjects.oo.atom.codegen.bytebuddy.plugin.Plugin;
import com.pragmaticobjects.oo.atom.codegen.cn.ClassNames;
import com.pragmaticobjects.oo.atom.codegen.cp.ClassPath;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.pool.TypePool;

import java.nio.file.Path;

/**
 * Bytebuddy-powered stage
 *
 * @author Kapralov Sergey
 */
public class ByteBuddyStage implements Stage {
    private final Plugin task;

    /**
     * Ctor.
     *
     * @param task Task.
     */
    public ByteBuddyStage(final Plugin task) {
        this.task = task;
    }

    @Override
    public final void apply(final ClassPath classPath, final ClassNames classNames, final Path workingDirectory) {
        try {
            final ClassFileLocator cfl = new CflsCompound(
                new CflsFromClassPath(
                    classPath
                ),
                new CflsExplicit(
                    ClassFileLocator.ForClassLoader.ofClassPath()
                )
            ).classFileLocator();
            final TypePool tps = TypePool.Default.of(cfl);
            for (String className : classNames.classNames()) {
                final TypePool.Resolution resolution = tps.describe(className);
                if (resolution.isResolved()) {
                    final TypeDescription td = resolution.resolve();
                    final DynamicType.Builder<?> builder = new ByteBuddy().redefine(td, cfl);
                    final DynamicType.Unloaded<?> unloaded = task.apply(builder, td).make();
                    unloaded.saveIn(workingDirectory.toFile());
                } else {
                    throw new RuntimeException("Class " + className + " cannot be resolved");
                }
            }
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
