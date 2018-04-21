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
package oo.atom.tests;


import io.vavr.collection.List;
import oo.atom.anno.Atom;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

/**
 * A tests suite.
 * 
 * @author Kapralov Sergey
 */
@Atom
public class TestsSuite {
    private final List<Test> tests;

    /**
     * Ctor.
     * 
     * @param tests Tests, which this test suite consists of.
     */
    public TestsSuite(Test... tests) {
        this(
            List.of(tests)
        );
    }

    /**
     * Ctor.
     * 
     * @param tests Tests, which this test suite consists of.
     */
    public TestsSuite(List<Test> tests) {
        this.tests = tests;
    }

    @TestFactory
    public final Iterable<DynamicTest> produceTests() {
        return tests.map(t -> DynamicTest.dynamicTest(t.description(), () -> t.execute()));
    }
}
