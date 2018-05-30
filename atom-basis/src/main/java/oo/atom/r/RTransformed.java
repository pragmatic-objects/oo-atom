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

import java.util.function.Function;
import io.vavr.collection.List;
import io.vavr.control.Try;

/**
 * Represents a result, transformed from several source results. In case when one or
 * more of the results are erroneous, represents erroneous result with a combined set 
 * of issues.
 * 
 * @author Kapralov Sergey
 * @param <X> source type
 * @param <T> target type
 */
public class RTransformed<X, T> implements Result<T> {
    private final List<Result<X>> results;
    private final Function<? super List<X>, ? extends T> transformFunction;

    /**
     * Ctor.
     * 
     * @param results Results to transform.
     * @param transformFunction Transformation function.
     */
    public RTransformed(List<Result<X>> results, Function<? super List<X>, ? extends T> transformFunction) {
        this.results = results;
        this.transformFunction = transformFunction;
    }

    @Override
    public final Try<T> value() {
        List<Try<X>> outcomes = results.map(Result::value);
        if (!outcomes.filter(Try::isFailure).isEmpty()) {
            return Try.failure(
                    new RuntimeException(
                            String.join("\r\n", issues())
                    )
            );
        }
        return Try.success(
            outcomes.map(Try::get).transform(transformFunction)
        );
    }

    @Override
    public final List<String> issues() {
        return results.flatMap(Result::issues);
    }
}
