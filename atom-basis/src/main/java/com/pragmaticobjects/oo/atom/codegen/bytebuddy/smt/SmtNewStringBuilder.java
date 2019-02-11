/*
 * MIT License
 *
 * Copyright (c) 2018 Kapralov Sergey
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt;

import com.pragmaticobjects.oo.atom.codegen.bytebuddy.matchers.ConjunctionMatcher;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * Places new instance of StringBuilder on top of the stack
 *
 * @author Kapralov Sergey
 */
public class SmtNewStringBuilder extends SmtCombined {
    /**
     * Ctor.
     */
    public SmtNewStringBuilder() {
        super(
            new SmtStatic(
                TypeCreation.of(
                    new TypeDescription.ForLoadedType(
                        StringBuilder.class
                    )
                )
            ),
            new SmtStatic(
                Duplication.SINGLE
            ),
            new SmtInvokeMethod(
                new TypeDescription.ForLoadedType(StringBuilder.class),
                new ConjunctionMatcher<>(
                    ElementMatchers.isDefaultConstructor()
                )
            )
        );
    }
}
