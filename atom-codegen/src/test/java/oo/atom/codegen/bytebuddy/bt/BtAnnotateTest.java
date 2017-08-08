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

import oo.atom.codegen.bytebuddy.bt.BtAnnotate;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import oo.atom.anno.Atom;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class BtAnnotateTest {
    @Test
    public void annotatesType() throws Exception {
        final TypeDescription type = new TypeDescription.ForLoadedType(Foo.class);
        final DynamicType.Builder<?> subclass = new ByteBuddy().redefine(Foo.class);
        final DynamicType.Unloaded<?> make = new BtAnnotate(type).transitionResult(subclass).outcome().get().make();
        final Class<?> clazz = make.load(new ClassLoader() {}).getLoaded();
        assertThat(
            clazz.getAnnotation(Atom.class)
        ).isNotNull();
    }

    @Test
    public void preventsDuplicateAnnotation() throws Exception {
        final TypeDescription type = new TypeDescription.ForLoadedType(Bar.class);
        final DynamicType.Builder<?> subclass = new ByteBuddy().redefine(Bar.class);

        assertThatCode(() -> {
            final DynamicType.Unloaded<?> make = new BtAnnotate(type).transitionResult(subclass).outcome().get().make();
            final Class<?> clazz = make.load(new ClassLoader() {}).getLoaded();
            final Atom annotation = clazz.getAnnotation(Atom.class);
        }).doesNotThrowAnyException();
    }

    private static class Foo {
    }

    @Atom
    private static class Bar {
    }
}
