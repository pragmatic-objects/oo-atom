/*
 * MIT License
 *
 * Copyright (c) 2018 Kapralov Sergey
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt;

import com.pragmaticobjects.oo.atom.codegen.bytebuddy.matchers.ConjunctionMatcher;
import io.vavr.collection.List;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * Places string, collected from all stringified Atom fields on to of the stack.
 */
public class SmtAtomFieldsToString extends SmtInferred {
    /**
     * Ctor.
     * @param type Atom type
     */
    public SmtAtomFieldsToString(TypeDescription type) {
        super(
            new Inference(
                type
            )
        );
    }

    /**
     * {@link SmtAtomFieldsToString} inference
     *
     * @author Kapralov Sergey
     */
    private static class Inference implements StackManipulationToken.Inference {
        private final TypeDescription type;

        /**
         * Ctor.
         * @param type Type
         */
        public Inference(TypeDescription type) {
            this.type = type;
        }

        @Override
        public final StackManipulationToken stackManipulationToken() {
            return new SmtCombined(
                new SmtStatic(
                    new TextConstant(", ")
                ),
                new SmtArray(
                    new TypeDescription.ForLoadedType(CharSequence.class),
                    List.of(type)
                        .flatMap(TypeDescription::getDeclaredFields)
                        .filter(f -> !f.isStatic())
                        .map(f -> new SmtAtomFieldToString(type, f))
                        .toJavaArray(SmtAtomFieldToString.class)
                ),
                new SmtInvokeMethod(
                    new TypeDescription.ForLoadedType(String.class),
                    new ConjunctionMatcher<>(
                        ElementMatchers.named("join"),
                        ElementMatchers.takesArguments(2),
                        ElementMatchers.takesArgument(0, CharSequence.class),
                        ElementMatchers.takesArgument(1, CharSequence[].class),
                        ElementMatchers.returns(String.class)
                    )
                )
            );
        }
    }
}
