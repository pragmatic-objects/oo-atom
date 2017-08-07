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
package oo.atom.task.result;

import oo.atom.tests.AssertionsSuite;

/**
 *
 * @author Kapralov Sergey
 */
public class TrCombinedTest extends AssertionsSuite {
    public TrCombinedTest() {
        super(
            new AssertTaskResultIsMalformed(
                "empty combination is prohibited", 
                new TrCombined<Integer>(
                    (a, b) -> a + b
                )
            ),
            new AssertTaskResultHoldsExpectedValue(
                "combines two results into one", 
                new TrCombined<>(
                    (a, b) -> a + b,
                    new TrSuccess<>(2), 
                    new TrSuccess<>(2)
                ),
                4
            ),
            new AssertTaskResultHoldsExpectedValue(
                "combines a series of results into one", 
                new TrCombined<>(
                    (a, b) -> a + b,
                    new TrSuccess<>(1), 
                    new TrSuccess<>(2),
                    new TrSuccess<>(3),
                    new TrSuccess<>(4)
                ),
                10
            ),
            new AssertTaskResultHoldsExpectedValue(
                "supports sequence of one result", 
                new TrCombined<>(
                    (a, b) -> a + b,
                    new TrSuccess<>(2)
                ),
                2
            ),
            new AssertTaskResultIsErroneous(
                "supports sequence of one failure", 
                new TrCombined<>(
                    (a, b) -> a + b,
                    new TrFailure<Integer>("error")
                ),
                "error"
            ),
            new AssertTaskResultIsErroneous(
                "combines a series of results with error", 
                new TrCombined<>(
                    (a, b) -> a + b,
                    new TrSuccess<>(1), 
                    new TrFailure<Integer>("error"),
                    new TrSuccess<>(3),
                    new TrSuccess<>(4)
                ),
                "error"
            ),
            new AssertTaskResultIsErroneous(
                "combines a series of errors", 
                new TrCombined<>(
                    (a, b) -> a + b,
                    new TrFailure<Integer>("error"),
                    new TrFailure<Integer>("error2"),
                    new TrFailure<Integer>("error3"),
                    new TrFailure<Integer>("error4")
                ),
                "error",
                "error2",
                "error3",
                "error4"
            )
        );
    }
}
