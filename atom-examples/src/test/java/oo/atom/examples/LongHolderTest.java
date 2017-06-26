/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo.atom.examples;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class LongHolderTest {
    @Test
    public void holderValuesAreComparedByEqual() {
        LongHolder l1 = new LongHolder(5);
        LongHolder l2 = new LongHolder(5);
        LongHolder l3 = new LongHolder(6);
        
        Assertions.assertThat(l1).isEqualTo(l2);
        Assertions.assertThat(l1).isNotEqualTo(l3);
    }
}
