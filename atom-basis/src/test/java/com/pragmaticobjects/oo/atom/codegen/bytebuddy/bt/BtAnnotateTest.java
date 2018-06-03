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

import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;

import java.lang.annotation.*;

/**
 * Tests suite for {@link BtAnnotate}
 * 
 * @author Kapralov Sergey
 */
public class BtAnnotateTest extends TestsSuite {
    
    public BtAnnotateTest() {
        super(
            new TestCase(
                "prevents duplicate annotations",
                new AssertBuilderTransitionIsNotCorruptingClass(
                    new BtAnnotate(
                        new DummyImpl()
                    ), 
                    Bar.class
                )
            )
        );
    }

    
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    private static @interface Dummy {}
    
    private static final class DummyImpl implements Dummy {
        @Override
        public final Class<? extends Annotation> annotationType() {
            return Dummy.class;
        }
    }
    
    @Dummy
    private static class Bar {
    }
}
