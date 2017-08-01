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

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatcher;
import oo.atom.codegen.bytebuddy.matchers.IsAtom;
import oo.atom.codegen.bytebuddy.matchers.IsAtomAlias;
import oo.atom.task.TFail;
import oo.atom.task.TInferred;
import oo.atom.task.Task;
import oo.atom.task.TaskInference;

class BtApplyPatchInference implements TaskInference<DynamicType.Builder<?>> {
    private final static ElementMatcher<TypeDescription> IS_ATOM = new IsAtom();
    private final static ElementMatcher<TypeDescription> IS_ATOM_ALIAS = new IsAtomAlias();
    
    private final TypeDescription type;
    private final DynamicType.Builder<?> builder;

    public BtApplyPatchInference(TypeDescription type, DynamicType.Builder<?> builder) {
        this.type = type;
        this.builder = builder;
    }
    
    @Override
    public final Task<DynamicType.Builder<?>> task() {
        if(IS_ATOM_ALIAS.matches(type)) {
            return new BtApplyAtomAliasPatch(builder, type);
        } else if(IS_ATOM.matches(type)) {
            return new BtApplyAtomPatch(builder, type);
        } else {
            return new TFail<>(
                String.format("%s is not atom", type.getName())
            );
        }
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class BtApplyPatch extends TInferred<DynamicType.Builder<?>> implements Task<DynamicType.Builder<?>> {
    public BtApplyPatch(TypeDescription type, DynamicType.Builder<?> builder) {
        super(
            new BtApplyPatchInference(
                type,
                builder
            )
        );
    }
}
