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
package com.pragmaticobjects.oo.atom.codegen.bytebuddy.cfls;

import com.pragmaticobjects.oo.atom.tests.AssertAssertionFails;
import com.pragmaticobjects.oo.atom.tests.AssertAssertionPasses;
import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;

import static com.pragmaticobjects.oo.atom.codegen.bytebuddy.cfls.TestData.*;

/**
 * Tests suite for {@link AssertClassFileLocatorSourceResolvesAClassName}
 *
 * @author Kapralov Sergey
 */
public class AssertClassFileLocatorSourceResolvesAClassNameTest extends TestsSuite {
    public AssertClassFileLocatorSourceResolvesAClassNameTest() {
        super(
            new TestCase(
                "positive case",
                new AssertAssertionPasses(
                    new AssertClassFileLocatorSourceResolvesAClassName(
                        new CflsExplicit(
                            CFL_FOO
                        ),
                        FOO_NAME
                    )
                )
            ),
            new TestCase(
                "negative case",
                new AssertAssertionFails(
                    new AssertClassFileLocatorSourceResolvesAClassName(
                        new CflsExplicit(
                            CFL_FOO
                        ),
                        BAR_NAME
                    )
                )
            )
        );
    }
}