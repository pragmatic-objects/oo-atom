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
package com.pragmaticobjects.oo.atom.codegen.bytebuddy.matchers.atomspec;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * Matcher which matches types, methods of which are final.
 *
 * @author Kapralov Sergey
 * @todo #165 Investigate why there is a check `.filter(isPublic())` in {@link AllMethodsAreFinal}.
 */
public class AllMethodsAreFinal implements ElementMatcher<TypeDescription> {
    @Override
    public final boolean matches(TypeDescription target) {
        MethodList<MethodDescription.InDefinedShape> methodsToCheck = target.getDeclaredMethods()
                .filter(not(isConstructor()))
                .filter(isPublic())
                .filter(not(isStatic()))
                .filter(not(isBridge()))
                .filter(not(named("equals")))
                .filter(not(named("hashCode")))
                .filter(not(named("toString")));
        
        for(MethodDescription md : methodsToCheck) {
            if(!md.isFinal()) {
                return false;
            }
        }
        return true;
    }
}
