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

import io.vavr.collection.List;
import io.vavr.control.Try;

import java.util.function.BinaryOperator;

/**
 * Represents combination of several results to one. In case when some result is
 * erroneous, resulting result will be erroneous too, containing aggregated list of all issues.
 * 
 * WARNING: empty list of results is not allowed for {@link RCombined}. Check
 * {@link RCombinedOrDefault} as an alternative for such cases.
 * 
 * @author Kapralov Sergey
 * @param <T> result's type
 */
public class RCombined<T> implements Result<T> {
    private final List<Result<T>> results;
    private final BinaryOperator<T> combineOperator;

    /**
     * Ctor.
     * @param combineOperator Combination function
     * @param results Results to combine
     */
    public RCombined(BinaryOperator<T> combineOperator, List<Result<T>> results) {
        this.results = results;
        this.combineOperator = combineOperator;
    }

    /**
     * Ctor.
     * @param combineOperator Combination function
     * @param results Results to combine
     */
    public RCombined(BinaryOperator<T> combineOperator, Result<T>... results) {
        this(combineOperator, List.of(results));
    }

    @Override
    public final Try<T> value() {
        if(results.isEmpty()) {
            throw new IllegalStateException("Attempt to combine an empty set");
        }
        return results.map(Result::value)
                .reduce((t1, t2) -> {
                    if(t1.isFailure() || t2.isFailure()) {
                        return Try.failure(
                            new RuntimeException(
                                String.join("\r\n", issues())
                            )
                        );
                    }
                    return Try.success(
                        combineOperator.apply(t1.get(), t2.get())
                    );
                });
    }
    
    @Override
    public final List<String> issues() {
        if(results.isEmpty()) {
            throw new IllegalStateException("Attempt to combine an empty set");
        }
        return results.flatMap(Result::issues);
    }
}
