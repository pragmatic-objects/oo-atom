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
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import oo.atom.codegen.bytebuddy.task.utils.SmtAssumeTaskToFail;
import oo.atom.codegen.bytebuddy.task.utils.SmtAssumeTaskToGenerateBytecode;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class SmtBoxTest {
    private static final Method INT_VALUEOF;

    static {
        try {
            INT_VALUEOF = Integer.class.getMethod("valueOf", int.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    
    @Test
    public void boxNonPrimitive() throws Exception {
        new SmtAssumeTaskToFail(
                new SmtBox(
                        new TypeDescription.ForLoadedType(Object.class)
                )
        ).check();
    }
    
    @Test
    public void boxIntegerPrimitive() throws Exception {
        new SmtAssumeTaskToGenerateBytecode(
                new SmtBox(
                        new TypeDescription.ForLoadedType(int.class)
                ),
                MethodInvocation.invoke(new MethodDescription.ForLoadedMethod(INT_VALUEOF))
        ).check();
    }
}
