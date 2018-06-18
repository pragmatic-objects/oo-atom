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

import io.vavr.API;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;

import java.lang.reflect.Method;

import static io.vavr.API.*;


/**
 * Stack manipulation token which boxes some primitive-type value on stack.
 * Fails in case when the passed type is not primitive.
 * 
 * @author Kapralov Sergey
 */
public class SmtBox implements StackManipulationToken {
    private static final Method BOOLEAN_VALUEOF;
    private static final Method BYTE_VALUEOF;
    private static final Method CHAR_VALUEOF;
    private static final Method SHORT_VALUEOF;
    private static final Method INT_VALUEOF;
    private static final Method LONG_VALUEOF;
    private static final Method FLOAT_VALUEOF;
    private static final Method DOUBLE_VALUEOF;

    static {
        try {
            BOOLEAN_VALUEOF = Boolean.class.getMethod("valueOf", boolean.class);
            BYTE_VALUEOF = Byte.class.getMethod("valueOf", byte.class);
            CHAR_VALUEOF = Double.class.getMethod("valueOf", double.class);
            SHORT_VALUEOF = Short.class.getMethod("valueOf", short.class);
            INT_VALUEOF = Integer.class.getMethod("valueOf", int.class);
            LONG_VALUEOF = Long.class.getMethod("valueOf", long.class);
            FLOAT_VALUEOF = Float.class.getMethod("valueOf", float.class);
            DOUBLE_VALUEOF = Double.class.getMethod("valueOf", double.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private final TypeDescription type;

    /**
     * Ctor.
     * 
     * @param type a type to box
     */
    public SmtBox(TypeDescription type) {
        this.type = type;
    }

    @Override
    public final StackManipulation stackManipulation() {
        return Match(type).<StackManipulation>of(API.Case(
                $(t -> t.represents(boolean.class)),
                MethodInvocation.invoke(new MethodDescription.ForLoadedMethod(BOOLEAN_VALUEOF))
            ),
            Case(
                $(t -> t.represents(byte.class)),
                MethodInvocation.invoke(new MethodDescription.ForLoadedMethod(BYTE_VALUEOF))
            ),
            Case(
                $(t -> t.represents(char.class)),
                MethodInvocation.invoke(new MethodDescription.ForLoadedMethod(CHAR_VALUEOF))
            ),
            Case(
                $(t -> t.represents(short.class)),
                MethodInvocation.invoke(new MethodDescription.ForLoadedMethod(SHORT_VALUEOF))
            ),
            Case(
                $(t -> t.represents(int.class)),
                MethodInvocation.invoke(new MethodDescription.ForLoadedMethod(INT_VALUEOF))
            ),
            Case(
                $(t -> t.represents(long.class)),
                MethodInvocation.invoke(new MethodDescription.ForLoadedMethod(LONG_VALUEOF))
            ),
            Case(
                $(t -> t.represents(float.class)),
                MethodInvocation.invoke(new MethodDescription.ForLoadedMethod(FLOAT_VALUEOF))
            ),
            Case(
                $(t -> t.represents(double.class)),
                MethodInvocation.invoke(new MethodDescription.ForLoadedMethod(DOUBLE_VALUEOF))
            ),
            Case(
                $(),
                () -> {
                    throw new RuntimeException(
                        String.format("Attempt to box non-primitive type %s", type.getName())
                    );
                }
            )
        );
    }
}

