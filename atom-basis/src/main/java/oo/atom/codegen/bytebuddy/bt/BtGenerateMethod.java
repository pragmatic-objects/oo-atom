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
package oo.atom.codegen.bytebuddy.bt;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import oo.atom.anno.NotAtom;
import oo.atom.codegen.bytebuddy.smt.StackManipulationToken;
import oo.atom.r.RBind;
import oo.atom.r.RSuccess;
import oo.atom.r.Result;

class BtGenerateMethodBytecodeAppender implements ByteCodeAppender {
    private final StackManipulation sm;

    public BtGenerateMethodBytecodeAppender(StackManipulation sm) {
        this.sm = sm;
    }

    @Override
    public final ByteCodeAppender.Size apply(MethodVisitor mv, Implementation.Context ctx, MethodDescription method) {
        StackManipulation.Size size = sm.apply(mv, ctx);
        return new ByteCodeAppender.Size(size.getMaximalSize(), method.getStackSize());
    }
}

class BtGenerateMethodImplementation implements Implementation {

    private final StackManipulation sm;

    public BtGenerateMethodImplementation(StackManipulation sm) {
        this.sm = sm;
    }

    @Override
    public final ByteCodeAppender appender(Implementation.Target implementationTarget) {
        return new BtGenerateMethodBytecodeAppender(sm);
    }

    @Override
    public final InstrumentedType prepare(InstrumentedType instrumentedType) {
        return instrumentedType;
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class BtGenerateMethod implements BuilderTransition {
    @NotAtom
    interface StackManipulationTokenByTypeDescription {
        StackManipulationToken token(TypeDescription type);
    }

    private final ElementMatcher<? super MethodDescription> elementMatcher;
    private final StackManipulationTokenByTypeDescription methodBodySmt;

    public BtGenerateMethod(ElementMatcher<? super MethodDescription> elementMatcher, StackManipulationTokenByTypeDescription methodBodySmt) {
        this.elementMatcher = elementMatcher;
        this.methodBodySmt = methodBodySmt;
    }
    
    @Override
    public final Result<Builder<?>> transitionResult(Builder<?> source, TypeDescription typeDescription) {
        final StackManipulationToken token = methodBodySmt.token(typeDescription);
        return new RBind<>(token, sm -> {
            return new RSuccess<>(
                source
                    .method(elementMatcher)
                    .intercept(new BtGenerateMethodImplementation(sm))
            );
        });
    }
}
