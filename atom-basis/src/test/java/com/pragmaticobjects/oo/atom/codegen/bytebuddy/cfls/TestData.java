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

import com.pragmaticobjects.oo.atom.anno.NotAtom;
import net.bytebuddy.dynamic.ClassFileLocator;
import org.apache.commons.io.IOUtils;

import java.util.HashMap;

//CHECKSTYLE:OFF
@NotAtom
final class TestData {
    public static final ClassFileLocator CFL_FOO;

    static {
        try {
            final HashMap<String, byte[]> bcMap = new HashMap<>();
            final String fooName = Foo.class.getName();
            final String fooPath = fooName.replace(".", "/") + ".class";
            bcMap.put(fooName, IOUtils.resourceToByteArray(
                    fooPath,
                    AssertClassFileLocatorSourceDoesntResolveAClassNameTest.class.getClassLoader()
            ));
            CFL_FOO = new ClassFileLocator.Simple(bcMap);
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static final class Foo {}
    public static final String FOO_NAME = Foo.class.getName();
    public static final class Bar {}
    public static final String BAR_NAME = Bar.class.getName();
}
