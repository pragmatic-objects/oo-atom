/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo.atom.anno.api.task;

import javaslang.control.Try;

/**
 *
 * @author skapral
 */
public class TConst<V> implements Task<V> {
    private final Try<V> result;

    public TConst(Try<V> result) {
        this.result = result;
    }

    @Override
    public final Try<V> result() {
        return result;
    }
}
