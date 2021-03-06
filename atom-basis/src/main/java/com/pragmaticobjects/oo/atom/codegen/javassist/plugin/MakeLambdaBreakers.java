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

package com.pragmaticobjects.oo.atom.codegen.javassist.plugin;

import com.pragmaticobjects.oo.atom.anno.Atom;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * Detects Atom interfaces with one method and adds lambda-breakers on them:
 * lambda-breaker is just default implementation of the interface's method which does nothing but throws
 * exception. This trick doesn't allow to use Atom interface as lambda.
 * See <a href="https://stackoverflow.com/questions/47565979/restrict-lambdas-on-certain-interfaces">https://stackoverflow.com/questions/47565979/restrict-lambdas-on-certain-interfaces</a>
 *
 * @author Kapralov Sergey
 */
public class MakeLambdaBreakers implements Plugin {
    @Override
    public final void operateOn(final CtClass clazz, final ClassPool classPool) {
        try {
            if (clazz.isInterface() && clazz.hasAnnotation(Atom.class) && clazz.getDeclaredMethods().length == 1) {
                final CtMethod ctMethod = clazz.getDeclaredMethods()[0];
                ctMethod.setBody("{ throw new RuntimeException(); }");
            }
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
