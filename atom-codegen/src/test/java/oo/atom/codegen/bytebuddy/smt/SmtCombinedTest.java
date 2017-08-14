package oo.atom.codegen.bytebuddy.smt;

import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import oo.atom.tests.AssertionsSuite;

public class SmtCombinedTest extends AssertionsSuite {
    public SmtCombinedTest() {
        super(
                new AssertTaskToGenerateBytecode(
                        "combines two StackManipulation tokens into one",
                        new SmtCombined(
                                new SmtLoadReference(5),
                                new SmtLoadReference(6)
                        ),
                        () -> new StackManipulation.Compound(
                                MethodVariableAccess.REFERENCE.loadFrom(5),
                                MethodVariableAccess.REFERENCE.loadFrom(6)
                        )
                )
        );
    }
}
