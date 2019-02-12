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

import static net.bytebuddy.matcher.ElementMatchers.isSynthetic;
import static net.bytebuddy.matcher.ElementMatchers.named;

import com.pragmaticobjects.oo.atom.codegen.bytebuddy.matchers.ConjunctionMatcher;
import com.pragmaticobjects.oo.atom.codegen.javassist.templates.Atoms;

import net.bytebuddy.description.type.TypeDescription;

/**
 * Generates invocation of method {@link Atoms#atom$toString$natural}.
 *
 * @see Atoms
 * @author Kapralov Sergey
 */
public class SmtInvokeAtomToStringNatural extends SmtInvokeMethod {
    /**
     * Ctor.
     *
     * @param type Type to call method on.
     */
    public SmtInvokeAtomToStringNatural(final TypeDescription type) {
        super(
            type,
            new ConjunctionMatcher<>(
                isSynthetic(),
                named("atom$toString$natural")
            )
        );
    }
}
