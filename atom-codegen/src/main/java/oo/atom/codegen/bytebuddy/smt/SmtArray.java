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
package oo.atom.codegen.bytebuddy.smt;

import io.vavr.collection.List;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import oo.atom.r.RInferred;
import oo.atom.r.RTransformed;
import oo.atom.r.Result;

class SmtArrayInference implements Result.Inference {
    private final List<Result<StackManipulation>> members;

    public SmtArrayInference(List<Result<StackManipulation>> members) {
        this.members = members;
    }

    @Override
    public final Result<StackManipulation> taskResult() {
        return new RTransformed<>(
            members, 
            list -> ArrayFactory.forType(TypeDescription.Generic.OBJECT).withValues(
                list.toJavaList()
            )
        );
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class SmtArray extends RInferred<StackManipulation> implements StackManipulationToken {
    public SmtArray(List<Result<StackManipulation>> members) {
        super(
            new SmtArrayInference(members)
        );
    }

    public SmtArray(Result<StackManipulation>... members) {
        this(List.of(members));
    }

}
