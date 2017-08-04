/*
 * The MIT License
 *
 * Copyright 2017 skapral.
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

import com.github.kimble.FactoryRunner;
import oo.atom.anno.Atom;
import org.easymock.EasyMock;
import org.easymock.MockType;
import org.junit.Test;

/**
 *
 * @author skapral
 */
public class AssertionsSuiteTest {
    @Test
    public final void generatesTests() throws Throwable {
        FactoryRunner.TestConsumer tc = EasyMock.mock(MockType.STRICT, FactoryRunner.TestConsumer.class);
        {
            tc.accept("foo", new FooTest());
            tc.accept("foo", new FooTest());
            tc.accept("foo", new FooTest());
            EasyMock.replay(tc);
        }
        {
            final AssertionsSuite suite = new AssertionsSuite(
                new AssertFoo(),
                new AssertFoo(),
                new AssertFoo()
            );
            suite.produceTests(tc);
            EasyMock.verify(tc);
        }
    }

    @Atom
    private static class AssertFoo implements Assertion {
        @Override
        public final String description() {
            return "foo";
        }

        @Override
        public final void check() throws Exception {
            //DO NOTHING
        }

        @Override
        public final boolean equals(Object o) {
            return o instanceof AssertFoo;
        }

        @Override
        public final int hashCode() {
            return 42;
        }
    }
    
    @Atom
    private static class FooTest extends AssertionTest {
        public FooTest() {
            super(new AssertFoo());
        }
    }
}
