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
package oo.atom.codegen.bytebuddy.bt;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.matcher.ElementMatcher;
import oo.atom.codegen.bytebuddy.matchers.IsAtom;
import oo.atom.r.RFailure;
import oo.atom.r.Result;
import oo.atom.r.ResultTransition;

/**
 *
 * @author Kapralov Sergey
 */
public class BtDoIfClassIsAtom implements ResultTransition<Builder<?>, Builder<?>> {
    private static final ElementMatcher<TypeDescription> IS_ATOM = new IsAtom();
    
    private final TypeDescription type;
    private final ResultTransition<Builder<?>, Builder<?>> task;

    public BtDoIfClassIsAtom(TypeDescription type, ResultTransition<Builder<?>, Builder<?>> task) {
        this.type = type;
        this.task = task;
    }

    @Override
    public final Result<Builder<?>> transitionResult(Builder<?> source) {
        if(IS_ATOM.matches(type)) {
            return task.transitionResult(source);
        } else {
            return new RFailure(
                String.format("%s is not atom", type.getName())
            );
        }
    }
}
