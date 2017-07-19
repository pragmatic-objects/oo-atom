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
package oo.atom.codegen.bytebuddy.task.builder;

import javaslang.control.Try;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatcher;
import oo.atom.task.Task;
import oo.atom.codegen.bytebuddy.matchers.IsAtom;

/**
 *
 * @author Kapralov Sergey
 */
public class BtValidateAtom implements Task<DynamicType.Builder<?>> {
    private static final ElementMatcher<TypeDescription> IS_ATOM = new IsAtom();
    
    private final TypeDescription type;
    private final Task<DynamicType.Builder<?>> task;

    public BtValidateAtom(TypeDescription type, Task<DynamicType.Builder<?>> task) {
        this.type = type;
        this.task = task;
    }
    
    @Override
    public final Try<DynamicType.Builder<?>> result() {
        if(IS_ATOM.matches(type)) {
            return task.result();
        } else {
            return Try.failure(
                new RuntimeException(
                    String.format("%s is not atom", type.getName())
                )
            );
            /*return task.result();*/
        }
    }
}
