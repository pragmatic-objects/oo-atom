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

package oo.atom.codegen.javassist.templates;

import oo.atom.anno.Atom;
import oo.atom.anno.NotAtom;

/**
 * Utility methods for Atoms.
 * WARNING: this class is used as a template for instrumentation procedures. NEVER call its methods
 * directly in the code.
 *
 * @author Kapralov Sergey
 */
@NotAtom
public class Atoms {
    private Atoms() {
        throw new IllegalAccessError();
    }

    private static boolean atom$equal(Object object1, Object object2) {
        if (object1 == object2) {
            return true;
        }
        if (object1 == null) {
            return object2 == null;
        }
        Class class1 = object1.getClass();
        Class class2 = object2.getClass();
        if(class1 != class2) {
            return false;
        }
        boolean isAtom = object1.getClass().isAnnotationPresent(Atom.class);
        if(isAtom) {
            return object1.equals(object2);
        }
        return false;
    }

    private static int atom$hashCode(Object... elements) {
        if (elements == null) {
            return 0;
        }
        int result = 1;
        for (Object element : elements) {
            result = 31 * result;
            if(element != null) {
                final boolean annotationPresent = element.getClass().isAnnotationPresent(Atom.class);
                final int hashCode = annotationPresent ? element.hashCode() : System.identityHashCode(element);
                result = result + (element == null ? 0 : element.hashCode());
            }
        }
        return result;
    }
}
