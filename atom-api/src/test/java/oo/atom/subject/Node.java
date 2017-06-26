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
package oo.atom.subject;

import java.util.Objects;
import oo.atom.anno.Atom;


/**
 *
 * @author Kapralov Sergey
 */
@Atom
public interface Node<T extends Comparable<T>> {
    /**
     * Adds a new node to the tree and returns new tree's root node.
     * 
     * @param newValue
     * @return 
     */
    Node<T> put(T newValue);
    
    class Empty<T extends Comparable<T>> implements Node<T> {
        @Override
        public final Node<T> put(T newValue) {
            return new Value<>(newValue);
        }
    }
    
    class Value<T extends Comparable<T>> implements Node<T> {
        private final T value;
        private final Node<T> left;
        private final Node<T> right;

        public Value(T value) {
            this(value, new Empty<>(), new Empty<>());
        }
        
        public Value(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public final Node<T> put(T newValue) {
            int comparison = value.compareTo(value);
            if(comparison == 0) {
                return this;
            } else if(comparison > 0) {
                return new Value<>(value, left, right.put(newValue));
            } else {
                return new Value<>(value, left.put(newValue), right);
            }
        }
    }
}
