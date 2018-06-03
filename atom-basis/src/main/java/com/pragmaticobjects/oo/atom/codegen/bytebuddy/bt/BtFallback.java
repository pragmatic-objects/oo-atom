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
package com.pragmaticobjects.oo.atom.codegen.bytebuddy.bt;

import com.pragmaticobjects.oo.atom.r.Result;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;

/**
 * Transition, which applies another transition if initial one has failed.
 *
 * @author Kapralov Sergey
 */
public class BtFallback implements BuilderTransition {
    private final BuilderTransition mainTransition;
    private final BuilderTransition fallbackTransition;

    /**
     * Ctor.
     *
     * @param mainTransition Transition to apply.
     * @param fallbackTransition Transition to apply {@link BtFallback#mainTransition} was unsuccessful.
     */
    public BtFallback(BuilderTransition mainTransition, BuilderTransition fallbackTransition) {
        this.mainTransition = mainTransition;
        this.fallbackTransition = fallbackTransition;
    }

    @Override
    public final Result<DynamicType.Builder<?>> transitionResult(DynamicType.Builder<?> source, TypeDescription typeDescription) {
        Result<DynamicType.Builder<?>> result = mainTransition.transitionResult(source, typeDescription);
        if(result.issues().nonEmpty()) {
            result = fallbackTransition.transitionResult(source, typeDescription);
        }
        return result;
    }
}
