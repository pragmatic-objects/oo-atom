package com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt;

import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * Tests suite for {@link SmtInvokeMethod}
 *
 * @author Kapralov Sergey
 */
class SmtInvokeMethodTest extends TestsSuite {
    /**
     * Ctor.
     */
    public SmtInvokeMethodTest() {
        super(
            new TestCase(
                "generates hashCode for class without fields",
                new AssertTokenToGenerateAByteCode(
                    new SmtInvokeMethod(
                        new TypeDescription.ForLoadedType(Foo.class),
                        ElementMatchers.named("bar")
                    ),
                    mv -> {
                        mv.visitMethodInsn(
                            Opcodes.INVOKEVIRTUAL,
                            "com/pragmaticobjects/oo/atom/codegen/bytebuddy/smt/SmtInvokeMethodTest$Foo",
                            "bar",
                            "()V",
                            false
                        );
                    }
                )
            )
        );
    }

    //CHECKSTYLE:OFF
    private static class Foo {
        public void bar() {}
    }
}