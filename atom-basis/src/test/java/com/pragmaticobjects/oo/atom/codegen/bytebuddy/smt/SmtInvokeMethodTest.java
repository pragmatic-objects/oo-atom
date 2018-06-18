package com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt;

import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.matcher.ElementMatchers;

class SmtInvokeMethodTest extends TestsSuite {
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


    private static class Foo {
        public void bar() {}
    }
}