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

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import oo.atom.tests.Assertion;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Assertion that passes if {@link BuilderTransition} under the test produces a valid class.
 * The class is implied as valid if it is successfully loaded by JVM and passes verification
 * 
 * @author Kapralov Sergey
 */
public class AssertBuilderTransitionIsNotCorruptingClass implements Assertion {
    private final String description;
    private final BuilderTransition bt;
    private final Class<?> type;

    public AssertBuilderTransitionIsNotCorruptingClass(String description, BuilderTransition bt, Class<?> type) {
        this.description = description;
        this.bt = bt;
        this.type = type;
    }

    @Override
    public final String description() {
        return description;
    }

    @Override
    public final void check() throws Exception {
        final DynamicType.Builder<?> subclass = new ByteBuddy().redefine(type);
        final TypeDescription typeDescription = new TypeDescription.ForLoadedType(type);
        assertThatCode(() -> {
            final DynamicType.Unloaded<?> make = bt
                    .transitionResult(subclass, typeDescription)
                    .value()
                    .get().make();
            final Class<?> clazz = make.load(new AnonymousClassLoader()).getLoaded();
            clazz.getMethods(); // Initiate validation.
        }).doesNotThrowAnyException();
    }

    private static final class AnonymousClassLoader extends ClassLoader {}
}
