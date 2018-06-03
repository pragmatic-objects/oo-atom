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

import com.pragmaticobjects.oo.atom.tests.Assertion;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;

import java.lang.annotation.Annotation;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Assertion which passes only if the class, produced by {@link BuilderTransition} under the test
 * is annotated with certain annotation.
 * 
 * @author Kapralov Sergey
 */
public class AssertBuilderTransitionToAnnotateAClass implements Assertion {
    private final BuilderTransition builderTransition;
    private final Class<?> type;
    private final Class<? extends Annotation> annotation;

    /**
     * Ctor.
     *
     * @param builderTransition {@link BuilderTransition} under test.
     * @param type A type to instrument.
     * @param annotation Annotation to check after transition is made.
     */
    public AssertBuilderTransitionToAnnotateAClass(BuilderTransition builderTransition, Class<?> type, Class<? extends Annotation> annotation) {
        this.builderTransition = builderTransition;
        this.type = type;
        this.annotation = annotation;
    }

    @Override
    public final void check() throws Exception {
        final TypeDescription typeDescription = new TypeDescription.ForLoadedType(type);
        final DynamicType.Builder<?> subclass = new ByteBuddy().redefine(type);
        final DynamicType.Unloaded<?> make = builderTransition
                .transitionResult(subclass, typeDescription)
                .value()
                .get()
                .make();
        final Class<?> clazz = make.load(new AnonymousClassLoader()).getLoaded();
        assertThat(clazz.getAnnotation(annotation))
                .withFailMessage("Expected annotation %s is missing on class %s", annotation.getName(), clazz.getName())
                .isNotNull();
    }

    /**
     * Anonymous class loader for isolating calls on {@link AssertBuilderTransitionToAnnotateAClass}
     *
     * @author Kapralov Sergey
     */
    private static final class AnonymousClassLoader extends ClassLoader {}
}
