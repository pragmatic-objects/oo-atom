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
package oo.atom.codegen.bytebuddy.task.sm.result;

import javaslang.collection.List;
import javaslang.control.Option;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import oo.atom.anno.api.task.issue.Issue;
import oo.atom.codegen.bytebuddy.task.sm.result.StackManipulationTaskResult;

/**
 *
 * @author Kapralov Sergey
 */
public class SmtrCombined implements StackManipulationTaskResult {

    private final StackManipulationTaskResult[] results;

    public SmtrCombined(StackManipulationTaskResult... results) {
        this.results = results;
    }

    @Override
    public Option<StackManipulation> item() {
        return List.of(results)
                .map(StackManipulationTaskResult::item)
                .map(o -> o.map(v -> List.of(v)))
                .reduce((opt1, opt2) -> {
                    if (opt1.isEmpty() || opt2.isEmpty()) {
                        return Option.none();
                    } else {
                        return opt1.map(l -> l.appendAll(opt2.get()));
                    }
                })
                .map(l -> new StackManipulation.Compound(l.toJavaList()));
    }

    @Override
    public List<Issue> issues() {
        return List.of(results)
                .flatMap(StackManipulationTaskResult::issues);
    }
}
