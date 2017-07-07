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
package oo.atom.plugin;

import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import oo.atom.anno.api.task.issue.Issue;
import oo.atom.anno.api.task.issue.x.IssuesFoundException;
import oo.atom.anno.api.task.result.TaskResult;
import oo.atom.codegen.bytebuddy.matchers.ShouldBeInstrumented;
import oo.atom.codegen.bytebuddy.task.builder.BtApplyPatch;


/**
 *
 * @author Kapralov Sergey
 */
public class AtomPlugin implements Plugin {
    @Override
    public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription td) {
        TaskResult<DynamicType.Builder<?>> result = new BtApplyPatch(builder, td).result();
        return result.item().getOrElseThrow(() -> new RuntimeException(
                new IssuesFoundException(result.issues().toJavaArray(Issue.class))
        ));
    }

    @Override
    public final boolean matches(TypeDescription td) {
        return new ShouldBeInstrumented().matches(td);
    }
}
