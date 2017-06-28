/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo.atom.assertion;

/**
 *
 * @author skapral
 */
public class ADerived implements Assertion {
    private final AssertionDerivative derivative;

    public ADerived(AssertionDerivative derivative) {
        this.derivative = derivative;
    }

    @Override
    public void run() throws Exception {
        derivative.assertion().run();
    }
}
