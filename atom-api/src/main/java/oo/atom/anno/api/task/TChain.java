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
package oo.atom.anno.api.task;

import javaslang.collection.List;
import javaslang.control.Option;
import oo.atom.anno.api.task.result.TaskResult;

/**
 *
 * @author Kapralov Sergey
 */
public class TChain<V> implements Task<V> {
    private final TaskResult<V> value;
    private final List<TaskLink<V>> links;

    public TChain(TaskResult<V> value, List<TaskLink<V>> links) {
        this.value = value;
        this.links = links;
    }
    
    public TChain(TaskResult<V> value, TaskLink<V>... links) {
        this(value, List.of(links));
    }

    @Override
    public final TaskResult<V> result() {
        return links.foldLeft(value, (smtr, smtl) -> {
            Option<Task<V>> taskOpt = smtr.item().map(smtl::task);
            return taskOpt.map(Task::result).getOrElse(smtr);
        });
    }
}
