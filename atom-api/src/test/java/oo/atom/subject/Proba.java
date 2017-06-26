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

/**
 *
 * @author Kapralov Sergey
 */
public class Proba {
    int a;
    long b;
    float c;
    double d;
    char e;
    boolean f;
    short g;
    byte h;
    Object o;

    public Proba(int a, long b, float c, double d, char e, boolean f, short g, byte h, Object o) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.o = o;
    }
    
    public int hashCode2() {
        return Objects.hash(a, b, c, d, e, f, g, h, System.identityHashCode(o));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.a;
        hash = 59 * hash + (int) (this.b ^ (this.b >>> 32));
        hash = 59 * hash + Float.floatToIntBits(this.c);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.d) ^ (Double.doubleToLongBits(this.d) >>> 32));
        hash = 59 * hash + this.e;
        hash = 59 * hash + (this.f ? 1 : 0);
        hash = 59 * hash + this.g;
        hash = 59 * hash + this.h;
        hash = 59 * hash + Objects.hashCode(this.o);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proba other = (Proba) obj;
        if (this.a != other.a) {
            return false;
        }
        if (this.b != other.b) {
            return false;
        }
        if (Float.floatToIntBits(this.c) != Float.floatToIntBits(other.c)) {
            return false;
        }
        if (Double.doubleToLongBits(this.d) != Double.doubleToLongBits(other.d)) {
            return false;
        }
        if (this.e != other.e) {
            return false;
        }
        if (this.f != other.f) {
            return false;
        }
        if (this.g != other.g) {
            return false;
        }
        if (this.h != other.h) {
            return false;
        }
        if (!Objects.equals(this.o, other.o)) {
            return false;
        }
        return true;
    }
}
