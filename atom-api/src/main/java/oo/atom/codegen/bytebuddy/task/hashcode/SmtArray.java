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
package oo.atom.codegen.bytebuddy.task.hashcode;

import javaslang.collection.List;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import oo.atom.anno.api.task.TFail;
import oo.atom.anno.api.task.TInferred;
import oo.atom.anno.api.task.TSucceed;
import oo.atom.anno.api.task.Task;
import oo.atom.anno.api.task.TaskInference;
import oo.atom.anno.api.task.issue.Issue;
import oo.atom.anno.api.task.result.TaskResult;

/**
 *
 * @author Kapralov Sergey
 */
public class SmtArray extends TInferred<StackManipulation> implements Task<StackManipulation> {

    public SmtArray(Task<StackManipulation>... subtasks) {
        super(new TaskInference<StackManipulation>() {
            @Override
            public final Task<StackManipulation> task() {
                List<TaskResult<StackManipulation>> results = List.of(subtasks)
                        .map(Task::result);
                        
                
                List<Issue> issues = results.flatMap(TaskResult::issues);
                boolean isNOK = issues.nonEmpty();
                
                if(isNOK) {
                    return new TFail<>(issues);
                } else {
                    return new TSucceed<>(
                            ArrayFactory.forType(TypeDescription.Generic.OBJECT).withValues(
                                results.map(TaskResult::item)
                                        .flatMap(o -> o)
                                        .toJavaList()
                            )
                    );
                }
            }
        });
    }
}
