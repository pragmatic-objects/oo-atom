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
public class TFake<V> implements Task<V> {
    private final TaskResult<V> result;

    public TFake(TaskResult<V> result) {
        this.result = result;
    }

    @Override
    public final TaskResult<V> result() {
        return result;
    }
}
