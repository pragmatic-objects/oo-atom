/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo.atom.anno.api.task.result;

import javaslang.control.Try;

/**
 *
 * @author Kapralov Sergey
 */
public class TrConst<T> implements TaskResult<T> {
    private final Try<T> item;

    public TrConst(Try<T> item) {
        this.item = item;
    }
    
    @Override
    public final Try<T> item() {
        return item;
    }
}
