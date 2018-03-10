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

import io.vavr.collection.List;
import java.lang.reflect.Method;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import oo.atom.tests.Assertion;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Assertion that passes if the class, produced by {@link BuilderTransition} under the test
 * has declared methods with certain names.
 * 
 * @author Kapralov Sergey
 */
public class AssertClassToHaveCertainMethodsAfterBuilderTransition implements Assertion {
    private final BuilderTransition bt;
    private final Class<?> clazz;
    private final List<String> methodNames;

    /**
     * Ctor.
     *
     * @param bt {@link BuilderTransition} under test.
     * @param clazz A class to instrument.
     * @param methodNames Method names to check after instrumentation.
     */
    public AssertClassToHaveCertainMethodsAfterBuilderTransition(BuilderTransition bt, Class<?> clazz, List<String> methodNames) {
        this.bt = bt;
        this.clazz = clazz;
        this.methodNames = methodNames;
    }

    /**
     * Ctor.
     *
     * @param bt {@link BuilderTransition} under test.
     * @param clazz A class to instrument.
     * @param methodNames Method names to check after instrumentation.
     */
    public AssertClassToHaveCertainMethodsAfterBuilderTransition(BuilderTransition bt, Class<?> clazz, String... methodNames) {
        this(
            bt,
            clazz,
            List.of(methodNames)
        );
    }

    @Override
    public final void check() throws Exception {
        final TypeDescription typeDescription = new TypeDescription.ForLoadedType(clazz);
        final DynamicType.Builder<?> subclass = new ByteBuddy().redefine(clazz);
        final DynamicType.Unloaded<?> make = bt
                .transitionResult(subclass, typeDescription)
                .value()
                .get()
                .make();
        final Class<?> newClazz = make.load(new AnonymousClassLoader()).getLoaded();
        assertThat(
            List.of(newClazz.getDeclaredMethods()).map(Method::getName)
        ).containsOnlyElementsOf(
            methodNames
        );
    }
    
    private static final class AnonymousClassLoader extends ClassLoader {}
}
