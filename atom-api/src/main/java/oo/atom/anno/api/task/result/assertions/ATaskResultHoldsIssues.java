/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo.atom.anno.api.task.result.assertions;

import javaslang.collection.List;
import oo.atom.assertion.ADerived;
import oo.atom.assertion.Assertion;
import oo.atom.assertion.AssertionDerivative;
import oo.atom.anno.api.task.issue.Issue;
import oo.atom.anno.api.task.result.TaskResult;
import oo.atom.assertion.std.AEquals;

/**
 *
 * @author skapral
 */
public class ATaskResultHoldsIssues<V, R extends TaskResult<? extends V>> extends ADerived implements Assertion {
    public ATaskResultHoldsIssues(R taskResult, List<Issue> issues) {
        super(
            new AssertionDerivative() {
                @Override
                public Assertion assertion() {
                    return new AEquals(taskResult.issues(), issues);
                }
            }
        );
    }
}
