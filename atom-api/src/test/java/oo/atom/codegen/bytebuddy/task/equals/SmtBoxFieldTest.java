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
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;
import oo.atom.codegen.bytebuddy.task.utils.SmtAssumeTaskToGenerateBytecode;
import org.junit.Test;
import static org.mockito.Mockito.verify;



class Foo {
    private final Object nonPrimitive;
    private final int primitive;

    public Foo(Object nonPrimitive, int primitive) {
        this.nonPrimitive = nonPrimitive;
        this.primitive = primitive;
    }
}



/**
 *
 * @author Kapralov Sergey
 */
public class SmtBoxFieldTest {
    
    private static final Method INT_VALUEOF;
    
    static {
        try {
            INT_VALUEOF = Integer.class.getMethod("valueOf", int.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Test
    public void doNothingWithNonPrimitiveFields() throws Exception {
        new SmtAssumeTaskToGenerateBytecode(
                new SmtBoxField(
                        new FieldDescription.ForLoadedField(
                                Foo.class.getDeclaredField("nonPrimitive")
                        )
                ),
                mv -> {}
        ).check();
    }
    
    @Test
    public void boxesPrimitiveFields() throws Exception {
        new SmtAssumeTaskToGenerateBytecode(
                new SmtBoxField(
                        new FieldDescription.ForLoadedField(
                                Foo.class.getDeclaredField("primitive")
                        )
                ),
                mv -> {
                    verify(mv).visitMethodInsn(
                            Opcodes.INVOKESTATIC, 
                            Type.getInternalName(Integer.class), 
                            "valueOf", 
                            Type.getMethodDescriptor(INT_VALUEOF), 
                            false
                    );
                }
        ).check();
    }
}
