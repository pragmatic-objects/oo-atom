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

package com.pragmaticobjects.oo.atom.banner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

/**
 * A banner, stored in a resource. It is obtained by provided name using {@link ClassLoader#getResourceAsStream(String)}
 * call.
 *
 * @author Kapralov Sergey
 */
public class BnnrFromResource implements Banner {
    private final String resourceName;

    /**
     * Ctor.
     *
     * @param resourceName A valid resource name
     */
    public BnnrFromResource(final String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public final void print(final Consumer<String> printoutConsumer) {
        try {
            final InputStream stream = BnnrFromResource.class.getClassLoader().getResourceAsStream(resourceName);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            while (true) {
                final String s = reader.readLine();
                if(s == null) break;
                printoutConsumer.accept(s);
            }
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
