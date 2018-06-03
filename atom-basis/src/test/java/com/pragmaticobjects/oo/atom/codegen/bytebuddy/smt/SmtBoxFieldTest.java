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
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Tests suite for {@link com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt.SmtBoxField}
 * 
 * @author Kapralov Sergey
 */
public class SmtBoxFieldTest extends TestsSuite {
    private static final Method INT_VALUEOF;
    private static final Field NON_PRIMITIVE_FIELD;
    private static final Field PRIMITIVE_FIELD;

    static {
        try {
            INT_VALUEOF = Integer.class.getMethod("valueOf", int.class);
            NON_PRIMITIVE_FIELD = Foo.class.getDeclaredField("nonPrimitive");
            PRIMITIVE_FIELD = Foo.class.getDeclaredField("primitive");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public SmtBoxFieldTest() {
        super(
            new TestCase(
                "generates no bytecode for non-primitive fields",
                new AssertTokensToRepresentIdenticalBytecode(
                    new SmtBoxField(
                        new FieldDescription.ForLoadedField(
                            NON_PRIMITIVE_FIELD
                        )
                    ),
                    new SmtDoNothing()
                )
            ),
            new TestCase(
                "boxes primitive fields",
                new AssertTokenToRepresentExpectedStackManipulation(
                    new SmtBoxField(
                        new FieldDescription.ForLoadedField(
                            PRIMITIVE_FIELD
                        )
                    ),
                    () -> MethodInvocation.invoke(new MethodDescription.ForLoadedMethod(INT_VALUEOF))
                )
            )
        );
    }

    private static class Foo {

        private final Object nonPrimitive;
        private final int primitive;

        public Foo(Object nonPrimitive, int primitive) {
            this.nonPrimitive = nonPrimitive;
            this.primitive = primitive;
        }
    }
}
