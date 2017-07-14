/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo.atom.codegen.bytebuddy.task.builder;

import javaslang.control.Try;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatcher;
import oo.atom.anno.api.task.Task;
import oo.atom.codegen.bytebuddy.matchers.IsAtom;

/**
 *
 * @author Kapralov Sergey
 */
public class BtValidateAtom implements Task<DynamicType.Builder<?>> {
    private static final ElementMatcher<TypeDescription> IS_ATOM = new IsAtom();
    
    private final TypeDescription type;
    private final Task<DynamicType.Builder<?>> task;

    public BtValidateAtom(TypeDescription type, Task<DynamicType.Builder<?>> task) {
        this.type = type;
        this.task = task;
    }
    
    @Override
    public final Try<DynamicType.Builder<?>> result() {
        if(IS_ATOM.matches(type)) {
            return task.result();
        } else {
            return Try.failure(
                new RuntimeException(
                    String.format("%s is not atom", type.getName())
                )
            );
            /*return task.result();*/
        }
    }
}
