/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo.atom.anno.api.task.result.assertions;

import oo.atom.anno.api.task.result.TaskResult;
import oo.atom.assertion.ACombined;
import oo.atom.assertion.Assertion;

/**
 *
 * @author skapral
 */
public class ATaskResultsAreSame<V, R extends TaskResult<V>> extends ACombined implements Assertion {
    public ATaskResultsAreSame(R taskResult, R taskSample) {
        super(
                ex -> new AssertionError(
                        String.format("%s expected to be the same as %s", taskResult, taskSample),
                        ex
                ),
                new ATaskResultHoldsIssues<>(taskResult, taskSample.issues()),
                new ATaskResultHoldsItem<>(taskResult, taskSample.item())
        );
    }
}
