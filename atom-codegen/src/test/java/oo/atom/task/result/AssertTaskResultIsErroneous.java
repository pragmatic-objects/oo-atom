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

import io.vavr.collection.List;
import oo.atom.tests.Assertion;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Kapralov Sergey
 */
public class AssertTaskResultIsErroneous implements Assertion {
    private final String description;
    private final TaskResult<?> taskResult;
    private final List<String> issues;
    
    public AssertTaskResultIsErroneous(String description, TaskResult<?> taskResult, List<String> issues) {
        this.description = description;
        this.taskResult = taskResult;
        this.issues = issues;
    }

    public AssertTaskResultIsErroneous(String description, TaskResult<?> taskResult, String... issues) {
        this(
            description,
            taskResult,
            List.of(issues)
        );
    }

    @Override
    public final String description() {
        return description;
    }

    @Override
    public final void check() throws Exception {
        assertThatThrownBy(() -> {
            taskResult.outcome().get();
        }).isInstanceOf(RuntimeException.class);
        assertThat(taskResult.issues())
                .containsExactlyElementsOf(issues);
    }
    
}
