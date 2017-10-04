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

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import oo.atom.codegen.validator.ValFail;
import oo.atom.tests.TestCase;
import oo.atom.tests.TestsSuite;

/**
 * Tests suite for {@link BtFallback}
 * 
 * @author Kapralov Sergey
 */
public class BtFallbackTest extends TestsSuite {
    public BtFallbackTest() {
        super(
            new TestCase(
                "successful branch",
                new AssertBuilderTransitionToAnnotateAClass(
                    new BtFallback(
                        new BtAnnotate(
                            new SuccessImpl()
                        ),
                        new BtAnnotate(
                            new FallbackImpl()
                        )
                    ),
                    TestClass.class,
                    Success.class
                )
            ),
            new TestCase(
                "fallback branch",
                new AssertBuilderTransitionToAnnotateAClass(
                    new BtFallback(
                        new BtValidated(
                            new ValFail("test issue"),
                            new BtAnnotate(
                                new SuccessImpl()
                            )
                        ),
                        new BtAnnotate(
                            new FallbackImpl()
                        )
                    ),
                    TestClass.class,
                    Fallback.class
                )
            )
        );
    }
    
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    private static @interface Success {}
    
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    private static @interface Fallback {}
    
    private static class SuccessImpl implements Success {
        @Override
        public final Class<? extends Annotation> annotationType() {
            return Success.class;
        }
    }
    
    private static class FallbackImpl implements Fallback {
        @Override
        public final Class<? extends Annotation> annotationType() {
            return Fallback.class;
        }
    }
    
    private static class TestClass {}
}
