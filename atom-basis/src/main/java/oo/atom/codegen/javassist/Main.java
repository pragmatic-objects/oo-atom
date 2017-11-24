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

package oo.atom.codegen.javassist;

import io.vavr.control.Option;
import javassist.ClassPool;
import javassist.CtClass;
import oo.atom.anno.NotAtom;
import oo.atom.codegen.cn.ClassNames;
import oo.atom.codegen.cn.CnFromPath;
import oo.atom.codegen.cp.ClassPath;
import oo.atom.codegen.cp.CpCombined;
import oo.atom.codegen.cp.CpExplicit;
import oo.atom.codegen.cp.CpFromString;
import oo.atom.codegen.javassist.cps.ClassPoolSource;
import oo.atom.codegen.javassist.cps.CpsFromClassPath;
import oo.atom.codegen.javassist.plugin.InlineTemplates;
import oo.atom.codegen.javassist.plugin.Plugin;

import java.nio.file.Path;
import java.nio.file.Paths;

@NotAtom
public class Main {
    public static void main(String... args) throws Exception {
        final Path workingDirectory = Option.of(System.getProperty("user.dir"))
            .map(Paths::get)
            .getOrElseThrow(RuntimeException::new);
        final ClassPath classPath = new CpCombined(
            new CpFromString(
                System.getProperty("java.class.path")
            ),
            new CpExplicit(
                workingDirectory
            )
        );
        final ClassPoolSource cps = new CpsFromClassPath(classPath);
        final ClassPool classPool = cps.classPool();
        final ClassNames cn = new CnFromPath(
            workingDirectory
        );
        final Plugin plugin = new InlineTemplates();
        for(String className : cn.classNames()) {
            final CtClass ctClass = classPool.get(className);
            System.out.println("Transforming " + className + " - phase 2");
            plugin.operateOn(ctClass, classPool);
            ctClass.writeFile();
        }
    }
}
