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

package oo.atom.codegen.bytebuddy;

import io.vavr.control.Option;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.pool.TypePool;
import oo.atom.anno.NotAtom;
import oo.atom.codegen.bytebuddy.plugin.EnforcingAtomPlugin;
import oo.atom.codegen.bytebuddy.plugin.Plugin;
import oo.atom.codegen.bytebuddy.tps.TpsFromClassLoader;
import oo.atom.codegen.bytebuddy.tps.TpsWithLazyResolution;
import oo.atom.codegen.bytebuddy.cfls.CflsFromClassPath;
import oo.atom.codegen.cn.ClassNames;
import oo.atom.codegen.cn.CnFromPath;
import oo.atom.codegen.cp.CpCombined;
import oo.atom.codegen.cp.CpExplicit;
import oo.atom.codegen.cp.CpFromString;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ByteBuddy instrumentation entry point.
 *
 * @author Kapralov Sergey
 */
@NotAtom
public final class Main {
    /**
     * Main entry point
     *
     * @param args Arguments
     * @throws Exception If something goes wrong.
     */
    public static void main(String... args) throws Exception {
        final Path workingDirectory = Option.of(System.getProperty("user.dir"))
            .map(Paths::get)
            .getOrElseThrow(RuntimeException::new);
        final ClassFileLocator cfl = new CflsFromClassPath(
            new CpCombined(
                new CpFromString(
                    System.getProperty("java.class.path")
                ),
                new CpExplicit(
                    workingDirectory
                )
            )
        ).classFileLocator();
        final TypePool tps = new TpsWithLazyResolution(
            cfl,
            new TpsFromClassLoader(Main.class.getClassLoader()).typePool()
        ).typePool();
        final ClassNames cn = new CnFromPath(
            workingDirectory
        );

        final Plugin plugin = new EnforcingAtomPlugin();
        for(String className : cn.classNames()) {
            final TypePool.Resolution resolution = tps.describe(className);
            if(resolution.isResolved()) {
                final TypeDescription td = resolution.resolve();
                final DynamicType.Builder<?> builder = new ByteBuddy().redefine(td, cfl);
                final DynamicType.Unloaded<?> unloaded = plugin.apply(builder, td).make();
                unloaded.saveIn(workingDirectory.toFile());
            } else {
                throw new RuntimeException();
            }
        }
    }
}
