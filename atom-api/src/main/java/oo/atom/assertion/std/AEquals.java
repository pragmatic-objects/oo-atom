/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo.atom.assertion.std;

import oo.atom.assertion.Assertion;
import org.assertj.core.api.Assertions;

/**
 *
 * @author skapral
 */
public class AEquals implements Assertion {
    private final Object o1;
    private final Object o2;

    public AEquals(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public final void run() throws Exception {
        Assertions.assertThat(
                o1
        ).isEqualTo(
                o2
        );
    }
    
}
