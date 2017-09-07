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
package oo.atom.r;

import io.vavr.collection.List;

/**
 * Inference for {@link RBind}
 * 
 * @author Kapralov Sergey
 * @param <X> Source type
 * @param <T> Transition type
 */
class RBindInference<X, T> implements Result.Inference<T> {
    private final Result<X> source;
    private final ResultTransition<X, T> bindFunction;

    /**
     * Ctor.
     * @param source Source result
     * @param bindFunction Transition function
     */
    public RBindInference(Result<X> source, ResultTransition<X, T> bindFunction) {
        this.source = source;
        this.bindFunction = bindFunction;
    }

    @Override
    public final Result<T> result() {
        List<String> issues = source.issues();
        if(!issues.isEmpty()) {
            return new RFailure<T>(issues);
        } else {
            return bindFunction.transitionResult(source.value().get());
        }
    }
}

/**
 * Represents monadic transition on source result.
 * If source result is successful, represents a result after applying transition to its value.
 * Propagates issues in case of erroneous result.
 * 
 * @author Kapralov Sergey
 */
public class RBind<X, T> extends RInferred<T> implements Result<T> {
    /**
     * Ctor.
     * @param source Source result
     * @param bindFunction Transition function
     */
    public RBind(Result<X> source, ResultTransition<X, T> bindFunction) {
        super(
            new RBindInference<>(source, bindFunction)
        );
    }
}
