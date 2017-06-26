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
package oo.atom.codegen.bytebuddy.task.sm;

import javaslang.collection.List;
import javaslang.control.Option;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import oo.atom.codegen.bytebuddy.task.sm.result.SmtrSuccess;
import oo.atom.codegen.bytebuddy.task.sm.result.StackManipulationTaskResult;

/**
 *
 * @author Kapralov Sergey
 * @todo #1:15m/DEV SmtChain class must be outlined to a common task implementation
 */
public class SmtChain implements StackManipulationTask {
    private final StackManipulation sm;
    private final List<StackManipulationTaskLink> links;

    public SmtChain(StackManipulation sm, List<StackManipulationTaskLink> links) {
        this.sm = sm;
        this.links = links;
    }
    
    public SmtChain(StackManipulation sm, StackManipulationTaskLink... links) {
        this(sm, List.of(links));
    }
    
    @Override
    public StackManipulationTaskResult result() {
        return links.<StackManipulationTaskResult>foldLeft(new SmtrSuccess(sm), (smtr, smtl) -> {
            Option<StackManipulationTask> taskOpt = smtr.item().map(smtl::task);
            return taskOpt.map(StackManipulationTask::result).getOrElse(smtr);
        });
    }
}
