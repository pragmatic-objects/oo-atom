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
package com.pragmaticobjects.oo.atom.codegen.bytebuddy.matchers;

import io.vavr.control.Option;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * Matches classes, super-class of which is matching certain matcher.
 *
 * @author Kapralov Sergey
 */
public class Extending implements ElementMatcher<TypeDescription> {
    private final ElementMatcher<TypeDescription> superClassMatcher;

    /**
     * Ctor.
     *
     * @param superClassMatcher Matcher
     */
    public Extending(ElementMatcher<TypeDescription> superClassMatcher) {
        this.superClassMatcher = superClassMatcher;
    }

    @Override
    public final boolean matches(TypeDescription target) {
        Option<TypeDescription> optSuperClass = Option
                .of(target.getSuperClass())
                .map(TypeDescription.Generic::asErasure);
        return optSuperClass
                .map(sc -> superClassMatcher.matches(sc))
                .getOrElse(Boolean.FALSE);
    }
}
