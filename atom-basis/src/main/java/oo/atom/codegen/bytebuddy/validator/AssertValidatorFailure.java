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

package oo.atom.codegen.bytebuddy.validator;

import io.vavr.collection.List;
import net.bytebuddy.description.type.TypeDescription;
import oo.atom.r.AssertResultIsErroneous;
import oo.atom.tests.AssertInferred;
import oo.atom.tests.Assertion;


/**
 * Asserts that validator fails on certain {@link TypeDescription}, with expected issues
 *
 * @author Kapralov Sergey
 */
public class AssertValidatorFailure extends AssertInferred {
    /**
     * Ctor.
     *
     * @param validator The validator under the test.
     * @param type Type to validate.
     * @param issues List of expected issues.
     */
    public AssertValidatorFailure(final Validator validator, final TypeDescription type, List<String> issues) {
        super(
            new AssertValidatorFailureInference(
                validator,
                type,
                issues
            )
        );
    }

    /**
     * Ctor.
     *
     * @param validator The validator under the test.
     * @param type Type to validate.
     * @param issues List of expected issues.
     */
    public AssertValidatorFailure(final Validator validator, final TypeDescription type, String... issues) {
        this(
            validator,
            type,
            List.of(issues)
        );
    }
}

/**
 * {@link AssertValidatorFailure} inference
 *
 * @author Kapralov Sergey
 */
class AssertValidatorFailureInference implements Assertion.Inference {
    private final Validator validator;
    private final TypeDescription type;
    private final List<String> issues;

    /**
     * Ctor.
     *
     * @param validator The validator under the test.
     * @param type Type to validate.
     * @param issues List of expected issues.
     */
    public AssertValidatorFailureInference(final Validator validator, final TypeDescription type, List<String> issues) {
        this.validator = validator;
        this.type = type;
        this.issues = issues;
    }

    @Override
    public final Assertion assertion() {
        return new AssertResultIsErroneous(
            validator.transitionResult(type),
            issues
        );
    }
}
