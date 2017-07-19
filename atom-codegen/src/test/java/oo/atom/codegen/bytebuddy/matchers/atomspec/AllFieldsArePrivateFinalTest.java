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
package oo.atom.codegen.bytebuddy.matchers.atomspec;

import net.bytebuddy.description.type.TypeDescription;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class AllFieldsArePrivateFinalTest {
    @Test
    public void trueIfAllFieldsArePrivateFinal() {
        assertThat(
                new AllFieldsArePrivateFinal().matches(
                        new TypeDescription.ForLoadedType(Foo.class)
                )
        ).isTrue();
    }
    
    @Test
    public void trueIfClassHasNoFields() {
        assertThat(
                new AllFieldsArePrivateFinal().matches(
                        new TypeDescription.ForLoadedType(Faz.class)
                )
        ).isTrue();
    }
    
    @Test
    public void falseIfAtLeastOneFieldIsNonFinal() {
        assertThat(
                new AllFieldsArePrivateFinal().matches(
                        new TypeDescription.ForLoadedType(Bar.class)
                )
        ).isFalse();
    }
    
    @Test
    public void falseIfAtLeastOneFieldIsNonPrivate() {
        assertThat(
                new AllFieldsArePrivateFinal().matches(
                        new TypeDescription.ForLoadedType(Baz.class)
                )
        ).isFalse();
    }
    
    @Test
    public void matcherSupportsEnums() {
        assertThat(
                new AllFieldsArePrivateFinal().matches(
                        new TypeDescription.ForLoadedType(Haz.class)
                )
        ).isTrue();
    }
    
    private static class Foo {
        private final Object method1;
        private final Object method2;
        private final Object method3;

        public Foo(Object method1, Object method2, Object method3) {
            this.method1 = method1;
            this.method2 = method2;
            this.method3 = method3;
        }
    }
    
    private static class Bar {
        private final Object method1;
        private Object method2;
        private final Object method3;

        public Bar(Object method1, Object method2, Object method3) {
            this.method1 = method1;
            this.method2 = method2;
            this.method3 = method3;
        }
    }
    
    private static class Baz {
        private final Object method1;
        final Object method2;
        private final Object method3;

        public Baz(Object method1, Object method2, Object method3) {
            this.method1 = method1;
            this.method2 = method2;
            this.method3 = method3;
        }
    }
    
    private static class Faz {
    }
    
    private static enum Haz {
        ONE(1), TWO(2), THREE(3);
        
        private final int origin;

        private Haz(int origin) {
            this.origin = origin;
        }

        public final int getOrigin() {
            return origin;
        }
    }
}
