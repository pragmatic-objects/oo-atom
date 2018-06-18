package com.pragmaticobjects.oo.atom.codegen.bytebuddy.smt;

import com.pragmaticobjects.oo.atom.tests.TestCase;
import com.pragmaticobjects.oo.atom.tests.TestsSuite;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.jar.asm.Opcodes;

class SmtArrayTest extends TestsSuite {
    public SmtArrayTest() {
        super(
            new TestCase(
                "generates new array",
                new AssertTokenToGenerateAByteCode(
                    new SmtArray(
                        new SmtStatic(
                            IntegerConstant.forValue(1)
                        ),
                        new SmtStatic(
                            IntegerConstant.forValue(2)
                        ),
                        new SmtStatic(
                            IntegerConstant.forValue(3)
                        )
                    ),
                    mv -> {
                        mv.visitInsn(Opcodes.ICONST_3);
                        mv.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object");
                        mv.visitInsn(Opcodes.DUP);
                        mv.visitInsn(Opcodes.ICONST_0);
                        mv.visitInsn(Opcodes.ICONST_1);
                        mv.visitInsn(Opcodes.AASTORE);
                        mv.visitInsn(Opcodes.DUP);
                        mv.visitInsn(Opcodes.ICONST_1);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitInsn(Opcodes.AASTORE);
                        mv.visitInsn(Opcodes.DUP);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitInsn(Opcodes.ICONST_3);
                        mv.visitInsn(Opcodes.AASTORE);
                    }
                )
            )

        );
    }

}