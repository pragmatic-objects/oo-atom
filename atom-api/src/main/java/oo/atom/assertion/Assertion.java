/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo.atom.assertion;

import oo.atom.anno.Atom;

/**
 *
 * @author skapral
 */
@Atom
public interface Assertion {
    void run() throws Exception;
}
