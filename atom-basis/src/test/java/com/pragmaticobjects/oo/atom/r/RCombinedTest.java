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
package com.pragmaticobjects.oo.atom.r;

import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;

/**
 * Tests suite for {@link RCombined}
 * 
 * @author Kapralov Sergey
 */
public class RCombinedTest extends TestsSuite {
    /**
     * Ctor.
     */
    public RCombinedTest() {
        super(
            new TestCase(
                "empty combination is prohibited",
                new AssertResultIsMalformed( 
                    new RCombined<Integer>(
                        (a, b) -> a + b
                    )
                )
            ),
            new TestCase(
                "combines two results into one",
                new AssertResultHoldsExpectedValue(
                    new RCombined<>(
                        (a, b) -> a + b,
                        new RSuccess<>(2), 
                        new RSuccess<>(2)
                    ),
                    4
                )
            ),
            new TestCase(
                "combines a series of results into one",
                new AssertResultHoldsExpectedValue(
                    new RCombined<>(
                        (a, b) -> a + b,
                        new RSuccess<>(1), 
                        new RSuccess<>(2),
                        new RSuccess<>(3),
                        new RSuccess<>(4)
                    ),
                    10
                )
            ),
            new TestCase(
                "supports sequence of one result",
                new AssertResultHoldsExpectedValue(
                    new RCombined<>(
                        (a, b) -> a + b,
                        new RSuccess<>(2)
                    ),
                    2
                )
            ),
            new TestCase(
                "supports sequence of one failure", 
                new AssertResultIsErroneous(
                    new RCombined<>(
                        (a, b) -> a + b,
                        new RFailure<Integer>("error")
                    ),
                    "error"
                )
            ),
            new TestCase(
                "combines a series of results with error",
                new AssertResultIsErroneous(
                    new RCombined<>(
                        (a, b) -> a + b,
                        new RSuccess<>(1), 
                        new RFailure<Integer>("error"),
                        new RSuccess<>(3),
                        new RSuccess<>(4)
                    ),
                    "error"
                )
            ),
            new TestCase(
                "combines a series of errors", 
                new AssertResultIsErroneous(
                    new RCombined<>(
                        (a, b) -> a + b,
                        new RFailure<Integer>("error"),
                        new RFailure<Integer>("error2"),
                        new RFailure<Integer>("error3"),
                        new RFailure<Integer>("error4")
                    ),
                    "error",
                    "error2",
                    "error3",
                    "error4"
                )
            )
        );
    }
}
