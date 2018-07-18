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

package com.pragmaticobjects.oo.atom.codegen.bytebuddy.validator;

import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;
import net.bytebuddy.description.type.TypeDescription;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * Tests suite for {@link ValSingle}
 *
 * @author Kapralov Sergey
 */
public class ValSingleTest extends TestsSuite {
    public ValSingleTest() {
        super(
            new TestCase(
                "validation passes successfully",
                new AssertValidatorSuccess(
                    new ValSingle(
                        named(Foo.class.getName()),
                        "Error message"
                    ),
                    new TypeDescription.ForLoadedType(Foo.class)
                )
            ),
            new TestCase(
                "validation fails with certain error message",
                new AssertValidatorFailure(
                    new ValSingle(
                        named(Foo.class.getName()),
                        "Error message"
                    ),
                    new TypeDescription.ForLoadedType(Bar.class),
                    "Error message"
                )
            )
        );
    }

    private static final class Foo {}
    private static final class Bar {}
}
