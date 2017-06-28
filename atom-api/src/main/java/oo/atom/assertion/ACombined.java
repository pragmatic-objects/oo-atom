/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo.atom.assertion;

import java.util.function.Function;
import java.util.function.Supplier;
import javaslang.collection.List;

/**
 *
 * @author skapral
 */
public class ACombined implements Assertion {
    private final Function<AssertionError, AssertionError> nestingFunction;
    private final List<Assertion> assertions;

    public ACombined(Function<AssertionError, AssertionError> nestingFunction, List<Assertion> assertions) {
        this.nestingFunction = nestingFunction;
        this.assertions = assertions;
    }

    public ACombined(Function<AssertionError, AssertionError> nestingFunction, Assertion... assertions) {
        this(nestingFunction, List.of(assertions));
    }

    @Override
    public final void run() throws Exception {
        try {
            for (Assertion a : assertions) {
                a.run();
            }
        } catch (AssertionError ex) {
            throw nestingFunction.apply(ex);
        }
    }
}
