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
package oo.atom.codegen.bytebuddy.smt;

import java.lang.reflect.Modifier;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import oo.atom.tests.TestCase;
import oo.atom.tests.TestsSuite;

/**
 *
 * @author Kapralov Sergey
 */
public class SmtCheckFieldsEqualityTest extends TestsSuite {
    public SmtCheckFieldsEqualityTest() {
        super(
            new TestCase(
                "array fields must be compared deeply",
                new AssertTokensToRepresentIdenticalBytecode(
                    new SmtCheckFieldsEquality(
                        new TypeDescription.ForLoadedType(
                            Foo.class
                        )
                    ),
                    new SmtCombined(
                        new SmtLoadPairOfFields(
                            new TypeDescription.ForLoadedType(
                                Foo.class
                            ),
                            new FieldDescription.Latent(
                                new TypeDescription.ForLoadedType(
                                    Foo.class
                                ),
                                new FieldDescription.Token(
                                    "objects",
                                    0,
                                    new TypeDescription.ForLoadedType(Object[].class).asGenericType()
                                )
                            )
                        ),
                        new SmtIfNotDeeplyEqual(
                            new SmtReturnInteger(0)
                        )
                    )
                )
            )
        );
    }
    
    private static final class Foo {
        private final Object[] objects;

        public Foo(Object[] objects) {
            this.objects = objects;
        }
    }
}
