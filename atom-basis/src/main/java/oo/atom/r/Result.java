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
import io.vavr.control.Try;

/**
 * An object, which represents a result of some execution. A result may be successful
 * (in that case, it hold an outcome of execution) or erroneous (in that case it holds 
 * supplementary info about the failures occured during execution)
 * 
 * @author Kapralov Sergey
 */
public interface Result<T> {
    /**
     * Inference for {@link Result}
     * 
     * @param <T> Value type
     */
    interface Inference<T> {
        /**
         * @return Inferred result
         */
        Result<T> result();
    }
    
    /**
     * @return Result's outcome. Erroneous in case of issues.
     */
    Try<T> value();
    
    /**
     * @return A list of issues. Is empty if result is successful.
     */
    List<String> issues();
}
