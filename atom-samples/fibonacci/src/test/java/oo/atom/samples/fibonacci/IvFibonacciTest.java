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
package oo.atom.samples.fibonacci;

import oo.atom.tests.TestCase;
import oo.atom.tests.TestsSuite;
import org.junit.Ignore;

/**
 * Tests suite for {@link IvFibonacci}
 * 
 * @author Kapralov Sergey
 */
public class IvFibonacciTest extends TestsSuite {
    public IvFibonacciTest() {
        super(
            new TestCase(
                "undefined on negative position",
                new AssertIntegerValueIsMalformed(
                    new IvFibonacci(-1)
                )
            ),
            new TestCase(
                "0 at 0th position",
                new AssertIntegerValueBoxesCertainInteger(
                    new IvFibonacci(0),
                    0
                )
            ),
            new TestCase(
                "1 at 1th position",
                new AssertIntegerValueBoxesCertainInteger(
                    new IvFibonacci(1),
                    1
                )
            ),
            new TestCase(
                "3 at 4th position",
                new AssertIntegerValueBoxesCertainInteger(
                    new IvFibonacci(4),
                    3
                )
            )
        );
    }
}
