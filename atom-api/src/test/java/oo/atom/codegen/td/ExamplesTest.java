/*
 * The MIT License
 *
 * Copyright 2017 Kapralov Sergey.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package oo.atom.codegen.td;

import oo.atom.subject.LongHolder;
import oo.atom.subject.Node;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
@Ignore
public class ExamplesTest {
    @Test
    public final void nodeEquality() {
        Node<String> node1 = new Node.Value<>("Hi!");
        Node<String> node2 = new Node.Value<>("Hi!");
        Node<String> node3 = new Node.Value<>("Preved!");
        Assertions.assertThat(node1).isEqualTo(node2);
        Assertions.assertThat(node1).isNotEqualTo(node3);
    }
    
    /*@Test
    public final void holderReferentialEquality() {
        Object o = new Object();
        Object o2 = new Object();
        Holder<Object> h = new Holder<>(o);
        Holder<Object> h2 = new Holder<>(o);
        Holder<Object> h3 = new Holder<>(o2);
        Assertions.assertThat(h).isEqualTo(h2);
        Assertions.assertThat(h).isNotEqualTo(h3);
    }*/
    
    @Test
    @Ignore
    public final void holderReferentialEquality2() {
        LongHolder h = new LongHolder(123l);
        LongHolder h2 = new LongHolder(123l);
        LongHolder h3 = new LongHolder(124l);
        Assertions.assertThat(h).isEqualTo(h2);
        Assertions.assertThat(h).isNotEqualTo(h3);
    }
}
