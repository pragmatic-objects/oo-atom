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

package com.pragmaticobjects.oo.atom.codegen.bytebuddy.cfls;

import io.vavr.collection.List;
import net.bytebuddy.dynamic.ClassFileLocator;

/**
 * {@link CflsCompound} inference;
 *
 * @author Kapralov Sergey
 */
class CflsCompoundInference implements ClassFileLocatorSource.Inference {
    private final List<ClassFileLocatorSource> parts;

    /**
     * Ctor.
     *
     * @param parts Parts to combine
     */
    public CflsCompoundInference(final List<ClassFileLocatorSource> parts) {
        this.parts = parts;
    }

    @Override
    public final ClassFileLocatorSource classFileLocatorSource() {
        return new CflsExplicit(
            parts
                .map(ClassFileLocatorSource::classFileLocator)
                .transform(cflsl -> new ClassFileLocator.Compound(cflsl.toJavaList()))
        );
    }
}

/**
 * Source for a combined {@link ClassFileLocator}, consisting of provided parts
 *
 * @author Kapralov Sergey
 */
public class CflsCompound extends CflsInferred implements ClassFileLocatorSource {
    /**
     * Ctor.
     *
     * @param parts Parts to combine
     */
    public CflsCompound(List<ClassFileLocatorSource> parts) {
        super(
            new CflsCompoundInference(
                parts
            )
        );
    }

    /**
     * Ctor.
     *
     * @param parts Parts to combine
     */
    public CflsCompound(final ClassFileLocatorSource... parts) {
        this(
            List.of(parts)
        );
    }
}
