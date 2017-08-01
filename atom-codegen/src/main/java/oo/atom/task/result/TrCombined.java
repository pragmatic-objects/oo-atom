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
package oo.atom.task.result;

import java.util.function.BinaryOperator;
import javaslang.collection.List;
import javaslang.control.Try;

/**
 *
 * @author Kapralov Sergey
 */
public class TrCombined<T> implements TaskResult<T> {
    private final List<TaskResult<T>> taskResults;
    private final BinaryOperator<T> combineOperator;
    
    public TrCombined(List<TaskResult<T>> taskResults, BinaryOperator<T> combineOperator) {
        this.taskResults = taskResults;
        this.combineOperator = combineOperator;
    }
    
    @Override
    public final Try<T> outcome() {
        return taskResults.map(TaskResult::outcome)
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
        return taskResults.flatMap(TaskResult::issues);
    }
}
