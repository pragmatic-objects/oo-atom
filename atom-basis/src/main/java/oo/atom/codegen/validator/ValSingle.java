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
package oo.atom.codegen.validator;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import oo.atom.r.RFailure;
import oo.atom.r.RSuccess;
import oo.atom.r.ResultTransition;
import oo.atom.r.RtInferred;

/**
 * {@link ValSingle} inference
 *
 * @author Kapralov Sergey
 */
class ValSingleInference implements ResultTransition.Inference<TypeDescription, TypeDescription> {
    private final ElementMatcher<TypeDescription> matcher;
    private final String errorMessage;

    /**
     * Ctor.
     *
     * @param matcher a validating matcher
     * @param errorMessage error message if validation is failed
     */
    public ValSingleInference(ElementMatcher<TypeDescription> matcher, String errorMessage) {
        this.matcher = matcher;
        this.errorMessage = errorMessage;
    }

    @Override
    public final ResultTransition<TypeDescription, TypeDescription> taskResultTransition() {
        return (type) -> {
            if(matcher.matches(type)) {
                return new RSuccess<>(type);
            } else {
                return new RFailure<>(errorMessage);
            }
        };
    }
}

/**
 * A single-matcher validation
 *
 * @author Kapralov Sergey
 */
public class ValSingle extends RtInferred<TypeDescription, TypeDescription> implements Validator {
    /**
     * Ctor.
     *
     * @param matcher a validating matcher
     * @param errorMessage error message if validation is failed
     */
    public ValSingle(ElementMatcher<TypeDescription> matcher, String errorMessage) {
        super(
            new ValSingleInference(matcher, errorMessage)
        );
    }
}
