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

import com.pragmaticobjects.oo.atom.tests.Assertion;
import io.vavr.control.Try;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Asserts that {@link ResultTransition} transformes a value to {@link Result}
 * with expected value
 *
 * @author Kapralov Sergey
 * @param <T> source type
 * @param <X> target type
 */
public class AssertAValueAfterTransition<T, X> implements Assertion {
    private final T source;
    private final ResultTransition<T, X> transition;
    private final X expectation;

    /**
     * Ctor.
     *
     * @param source source value
     * @param transition transition under the test
     * @param expectation expected value of the output {@link Result}
     */
    public AssertAValueAfterTransition(final T source, final ResultTransition<T, X> transition, final X expectation) {
        this.source = source;
        this.transition = transition;
        this.expectation = expectation;
    }

    @Override
    public final void check() throws Exception {
        final Result<X> xResult = transition.transitionResult(source);
        assertThat(xResult.value()).isEqualTo(Try.success(expectation));
    }
}
