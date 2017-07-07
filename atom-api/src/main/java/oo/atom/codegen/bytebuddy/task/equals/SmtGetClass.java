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
package oo.atom.codegen.bytebuddy.task.equals;

import java.lang.reflect.Method;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import oo.atom.anno.api.task.Task;
import oo.atom.anno.api.task.result.TaskResult;
import oo.atom.anno.api.task.result.TrSuccess;

/**
 *
 * @author Kapralov Sergey
 */
public class SmtGetClass implements Task<StackManipulation> {
    private final static Method GETCLASS;

    static {
        try {
            GETCLASS = Object.class.getMethod("getClass");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public final TaskResult<StackManipulation> result() {
        return new TrSuccess<>(
                MethodInvocation.invoke(new MethodDescription.ForLoadedMethod(GETCLASS))
        );
    }
}
