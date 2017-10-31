package oo.atom.codegen.bytebuddy.smt;

import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import oo.atom.tests.TestCase;
import oo.atom.tests.TestsSuite;

/**
 * Tests suite for {@link oo.atom.codegen.bytebuddy.smt.SmtCombined}
 * 
 * @author Kapralov Sergey
 */
public class SmtCombinedTest extends TestsSuite {
    public SmtCombinedTest() {
        super(
            new TestCase(
                "combines two StackManipulation tokens into one",
                new AssertTokenToRepresentExpectedStackManipulation(
                    new SmtCombined(
                        new SmtLoadReference(5),
                        new SmtLoadReference(6)
                    ),
                    () -> new StackManipulation.Compound(
                        MethodVariableAccess.REFERENCE.loadFrom(5),
                        MethodVariableAccess.REFERENCE.loadFrom(6)
                    )
                )
            )
        );
    }
}
