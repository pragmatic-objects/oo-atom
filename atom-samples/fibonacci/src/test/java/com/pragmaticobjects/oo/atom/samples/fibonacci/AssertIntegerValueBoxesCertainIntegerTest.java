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
package com.pragmaticobjects.oo.atom.samples.fibonacci;

import com.pragmaticobjects.oo.atom.tests.AssertAssertionFails;
import com.pragmaticobjects.oo.atom.tests.AssertAssertionPasses;
import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;

/**
 *
 * @author Kapralov Sergey
 */
public class AssertIntegerValueBoxesCertainIntegerTest extends TestsSuite {
    public AssertIntegerValueBoxesCertainIntegerTest() {
        super(
            new TestCase(
                "assertion must pass when returned primitive matches expected value",
                new AssertAssertionPasses(
                    new AssertIntegerValueBoxesCertainInteger(
                        new IvConstant(42),
                        42
                    )
                )
            ),
            new TestCase(
                "assertion must fail when returned primitive mismatches expected value",
                new AssertAssertionFails(
                    new AssertIntegerValueBoxesCertainInteger(
                        new IvConstant(42),
                        24
                    )
                )
            )
        );
    }
}
