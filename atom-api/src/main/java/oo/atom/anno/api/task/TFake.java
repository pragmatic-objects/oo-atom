/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo.atom.anno.api.task;

import oo.atom.anno.api.task.result.TaskResult;

/**
 *
 * @author skapral
 */
public class TFake<V, R extends TaskResult<? extends V>> implements Task<V, R> {
    private final R result;

    public TFake(R result) {
        this.result = result;
    }

    @Override
    public final R result() {
        return result;
    }
}
