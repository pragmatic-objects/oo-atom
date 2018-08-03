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
package com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt;

import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.jar.asm.Opcodes;

import java.lang.reflect.Method;

/**
 * Tests suite for {@link SmtBox}
 * @author Kapralov Sergey
 */
public class SmtBoxTest extends TestsSuite {
    private static final Method INT_VALUEOF;

    static {
        try {
            INT_VALUEOF = Integer.class.getMethod("valueOf", int.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Ctor.
     */
    public SmtBoxTest() {
        super(
            new TestCase(
                "attempt to box non-primitive must fail",
                new AssertTokenToFail(
                    new SmtBox(
                        new TypeDescription.ForLoadedType(Object.class)
                    )
                )
            ),
            new TestCase(
                "can box integer primitive", 
                new AssertTokenToGenerateAByteCode(
                    new SmtBox(
                        new TypeDescription.ForLoadedType(int.class)
                    ),
                    mv -> {
                        mv.visitMethodInsn(
                            Opcodes.INVOKESTATIC,
                            "java/lang/Integer",
                            "valueOf",
                            "(I)Ljava/lang/Integer;",
                            false
                        );
                    }
                )
            )
        );
    }
}
