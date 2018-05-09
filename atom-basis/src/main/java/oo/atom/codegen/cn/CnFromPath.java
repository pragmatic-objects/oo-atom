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

package oo.atom.codegen.cn;

import io.vavr.collection.List;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class names, extracted from all .class files from certain directory.
 *
 * @author Kapralov Sergey
 */
public class CnFromPath implements ClassNames {
    private final Path path;

    /**
     * Ctor.
     *
     * @param path Path to scan for class names.
     */
    public CnFromPath(final Path path) {
        this.path = path;
    }

    @Override
    public final List<String> classNames() {
        try {
            if(Files.notExists(path)) {
                return List.empty();
            }
            final List<String> classes = Files.find(path, Integer.MAX_VALUE, (p, bf) -> p.toString().endsWith(".class"))
                .map(path::relativize)
                .map(Object::toString)
                .map(s -> s.replace(".class", "").replace("/", "."))
                .filter(s -> !"module-info".equals(s))
                .collect(List.collector());
            return classes;
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
