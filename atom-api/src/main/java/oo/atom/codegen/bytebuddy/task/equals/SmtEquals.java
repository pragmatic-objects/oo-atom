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

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import oo.atom.anno.api.task.Task;

/**
 *
 * @author Kapralov Sergey
 */
public class SmtEquals extends SmtCombined implements Task<StackManipulation> {
    public SmtEquals(TypeDescription type) {
        super(
                new SmtLoadReference(0),
                new SmtLoadReference(1),
                new SmtIfEqualByReference(false, new SmtReturn(1)),
                new SmtLoadReference(1),
                new SmtIfNull(false, new SmtReturn(0)),
                new SmtLoadReference(0),
                new SmtGetClass(),
                new SmtLoadReference(1),
                new SmtGetClass(),
                new SmtIfEqualByReference(true, new SmtReturn(0)),
                new SmtFieldsEquality(type),
                new SmtReturn(1)
        );
    }
}
