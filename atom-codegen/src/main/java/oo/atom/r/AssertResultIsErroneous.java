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
package oo.atom.r;

import io.vavr.collection.List;
import oo.atom.tests.Assertion;
import static org.assertj.core.api.Assertions.*;

/**
 * Asserts that the {@link oo.atom.r.Result} represents an issue with expected set of messages.
 * 
 * @author Kapralov Sergey
 */
public class AssertResultIsErroneous implements Assertion {
    private final Result<?> result;
    private final List<String> issues;
    
    /**
     * Ctor.
     * 
     * @param result Result to assert on
     * @param issues A set of issue messages to expect
     */
    public AssertResultIsErroneous(Result<?> result, List<String> issues) {
        this.result = result;
        this.issues = issues;
    }

    /**
     * Ctor.
     * 
     * @param result Result to assert on
     * @param issues A set of issue messages to expect
     */
    public AssertResultIsErroneous(Result<?> result, String... issues) {
        this(
            result,
            List.of(issues)
        );
    }

    @Override
    public final void check() throws Exception {
        assertThatThrownBy(() -> {
            result.value().get();
        }).isInstanceOf(RuntimeException.class);
        assertThat(result.issues())
                .containsOnlyElementsOf(issues);
    }
}
