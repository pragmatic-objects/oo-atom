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
 * Asserts that result represents some certain value (values equality is done by
 * {@link Object#equals(java.lang.Object)} method).
 * 
 * @author Kapralov Sergey
 * @param <T> result's type
 */
public class AssertResultHoldsExpectedValue<T> implements Assertion {
    private final Result<T> taskResult;
    private final T value;

    /**
     * Ctor.
     * 
     * @param result A result to assert on.
     * @param value Expected value hold by result.
     */
    public AssertResultHoldsExpectedValue(Result<T> result, T value) {
        this.taskResult = result;
        this.value = value;
    }

    @Override
    public final void check() throws Exception {
        assertThat(taskResult.value()).isEqualTo(Try.success(value));
    }
}
