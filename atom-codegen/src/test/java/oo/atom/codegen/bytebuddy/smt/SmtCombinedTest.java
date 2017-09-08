package oo.atom.codegen.bytebuddy.smt;

import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import oo.atom.tests.AssertionsSuite;

/**
 * Tests suite for {@link oo.atom.codegen.bytebuddy.smt.SmtCombined}
 * 
 * @author Kapralov Sergey
 */
public class SmtCombinedTest extends AssertionsSuite {
    public SmtCombinedTest() {
        super(
            new AssertTokenToRepresentExpectedStackManipulation(
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
