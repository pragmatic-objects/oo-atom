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
package com.pragmaticobjects.oo.atom.r;

/**
 * Two result transitions, chained together. Result value is passed through
 * the first transition, then the second one.
 * 
 * @author Kapralov Sergey
 * @param <X> source type
 * @param <S> intermediate type
 * @param <T> target type
 */
public class RtChain<X, S, T> implements ResultTransition<X, T> {
    private final ResultTransition<X, S> first;
    private final ResultTransition<S, T> second;

    /**
     * Ctor.
     * @param first First transition
     * @param second Second transition.
     */
    public RtChain(ResultTransition<X, S> first, ResultTransition<S, T> second) {
        this.first = first;
        this.second = second;
    }
    
    @Override
    public final Result<T> transitionResult(X source) {
        return new RBind<>(
            first.transitionResult(source),
            second
        );
    }
}
