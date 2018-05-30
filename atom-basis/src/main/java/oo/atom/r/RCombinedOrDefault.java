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

import java.util.function.BinaryOperator;
import io.vavr.collection.List;

/**
 * Inference for {@link RCombinedOrDefault}
 * @author Kapralov Sergey
 * @param <T> 
 */
class RCombinedOrDefaultInference<T> implements Result.Inference<T> {
    private final BinaryOperator<T> combineOperator;
    private final Result<T> defaultResult;
    private final List<Result<T>> results;

    /**
     * Ctor.
     * @param combineOperator Combination function
     * @param defaultResult A result to fallback in case of empty result set.
     * @param results Results to combine
     */
    public RCombinedOrDefaultInference(BinaryOperator<T> combineOperator, Result<T> defaultResult, List<Result<T>> results) {
        this.combineOperator = combineOperator;
        this.defaultResult = defaultResult;
        this.results = results;
    }

    @Override
    public final Result<T> result() {
        if(results.isEmpty()) {
            return defaultResult;
        } else {
            return new RCombined<>(combineOperator, results);
        }
    }
}

/**
 * Represents result, combined from a set of results, or some predefined result
 * if results set is empty.
 * 
 * @author Kapralov Sergey
 * @see RCombined
 * @param <T> Result's type
 */
public class RCombinedOrDefault<T> extends RInferred<T> {
    /**
     * Ctor.
     * 
     * @param combineOperator Combination function
     * @param defaultResult A result to fallback in case of empty result set.
     * @param results Results to combine
     */
    public RCombinedOrDefault(BinaryOperator<T> combineOperator, Result<T> defaultResult, List<Result<T>> results) {
        super(
            new RCombinedOrDefaultInference(
                combineOperator,
                defaultResult,
                results
            )
        );
    }

    /**
     * Ctor.
     * 
     * @param combineOperator Combination function
     * @param defaultValue a value for default result
     * @param results Results to combine
     */
    public RCombinedOrDefault(BinaryOperator<T> combineOperator, T defaultValue, List<Result<T>> results) {
        this(
            combineOperator,
            new RSuccess<>(defaultValue),
            results
        );
    }

    /**
     * Ctor.
     * 
     * @param combineOperator Combination function
     * @param defaultResult A result to fallback in case of empty result set.
     * @param results Results to combine
     */
    public RCombinedOrDefault(BinaryOperator<T> combineOperator, Result<T> defaultResult, Result<T>... results) {
        this(
            combineOperator,
            defaultResult,
            List.of(results)
        );
    }

    /**
     * Ctor.
     * 
     * @param combineOperator Combination function
     * @param defaultValue a value for default result
     * @param results Results to combine
     */
    public RCombinedOrDefault(BinaryOperator<T> combineOperator, T defaultValue, Result<T>... results) {
        this(
            combineOperator,
            defaultValue,
            List.of(results)
        );
    }
}
