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
package oo.atom.codegen.bytebuddy.task.builder;

import javaslang.collection.List;
import javaslang.control.Option;
import net.bytebuddy.dynamic.DynamicType;
import oo.atom.codegen.bytebuddy.task.builder.result.BtrSuccess;
import oo.atom.codegen.bytebuddy.task.builder.result.BuilderTaskResult;

/**
 *
 * @author Kapralov Sergey
 */
public class BtChain implements BuilderTask {
    private final DynamicType.Builder<?> builder;
    private final List<BuilderTaskLink> links;

    public BtChain(DynamicType.Builder<?> builder, List<BuilderTaskLink> links) {
        this.builder = builder;
        this.links = links;
    }
    
    public BtChain(DynamicType.Builder<?> builder, BuilderTaskLink... links) {
        this(builder, List.of(links));
    }
    

    @Override
    public BuilderTaskResult result() {
        return links.<BuilderTaskResult>foldLeft(new BtrSuccess(builder), (btr, btl) -> {
            Option<BuilderTask> taskOpt = btr.item().map(btl::task);
            return taskOpt.map(BuilderTask::result).getOrElse(btr);
        });
    }
}
