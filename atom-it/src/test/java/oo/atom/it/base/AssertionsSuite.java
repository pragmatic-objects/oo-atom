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
package oo.atom.it.base;

import com.github.kimble.FactoryRunner;
import io.vavr.collection.List;
import org.junit.runner.RunWith;


class AssertionTest implements FactoryRunner.Test {
    private final Assertion assertion;

    public AssertionTest(Assertion assertion) {
        this.assertion = assertion;
    }

    @Override
    public final void execute() throws Throwable {
        assertion.check();
    }
}


/**
 *
 * @author Kapralov Sergey
 */
@RunWith(FactoryRunner.class)
public class AssertionsSuite implements FactoryRunner.Producer {
    private final List<Assertion> assertions;

    public AssertionsSuite(Assertion... assertions) {
        this(
            List.of(assertions)
        );
    }
    
    public AssertionsSuite(List<Assertion> assertions) {
        this.assertions = assertions;
    }

    @Override
    public final void produceTests(FactoryRunner.TestConsumer tc) throws Throwable {
        for(Assertion assertion : assertions) {
            tc.accept(
                    assertion.description(), 
                    new AssertionTest(assertion)
            );
        }
    }
    
}
