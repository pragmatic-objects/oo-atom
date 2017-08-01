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
package oo.atom.codegen.bytebuddy.plugin;

import javaslang.collection.List;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatcher;
import oo.atom.task.Task;
import oo.atom.task.result.TaskResult;

/**
 *
 * @author Kapralov Sergey
 */
public class TaskPlugin implements Plugin {
    @FunctionalInterface
    public static interface TaskSource {
        Task<DynamicType.Builder<?>> taskFromPluginArguments(DynamicType.Builder<?> builder, TypeDescription typeDescription);
    }
    
    private final ElementMatcher<TypeDescription> matcher;
    private final TaskSource taskSource;

    public TaskPlugin(ElementMatcher<TypeDescription> matcher, TaskSource taskSource) {
        this.matcher = matcher;
        this.taskSource = taskSource;
    }

    @Override
    public final DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription) {
        TaskResult<DynamicType.Builder<?>> result = taskSource.taskFromPluginArguments(builder, typeDescription).result();
        List<String> issues = result.issues();
        if(issues.isEmpty()) {
            return result.outcome().get();
        } else {
            issues.map(str -> "ERROR: " + str).forEach(System.err::println);
            throw new RuntimeException("Plugin was failed. Details are in the Maven logs.");
        }
    }

    @Override
    public final boolean matches(TypeDescription target) {
        return matcher.matches(target);
    }
}
