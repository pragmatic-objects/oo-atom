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
package oo.atom.codegen.bytebuddy.matchers.atomspec;

import io.vavr.collection.List;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 *
 * @author Kapralov Sergey
 */
public class HasNoStaticMethods implements ElementMatcher<TypeDescription> {
    private static final Junction<MethodDescription> ENUM_METHODS_MATCHER;

    static {
        ENUM_METHODS_MATCHER = List.of(Enum.class.getMethods())
            .map(MethodDescription.ForLoadedMethod::new)
            .filter(isStatic()::matches)
            .map(MethodDescription::asDefined)
            .map(ElementMatchers::is)
            .append(new Junction.Conjunction<>(
                named("values"),
                takesArguments(0)
            ))
            .append(new Junction.Conjunction<>(
                named("valueOf"),
                takesArguments(String.class)
            ))
            .reduce(Junction.Disjunction::new);
    }

    @Override
    public final boolean matches(TypeDescription target) {
        MethodList<MethodDescription.InDefinedShape> declaredMethods = target.getDeclaredMethods()
            .filter(isStatic())
            .filter(not(isSynthetic()));

        if(target.isEnum()) {
            declaredMethods = declaredMethods
                .filter(not(ENUM_METHODS_MATCHER));
        }

        return declaredMethods.isEmpty();
    }
}
