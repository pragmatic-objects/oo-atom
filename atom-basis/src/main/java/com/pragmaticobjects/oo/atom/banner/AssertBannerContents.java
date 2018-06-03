/*
 * The MIT License
 *
 * Copyright 2018 Kapralov Sergey.
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

package com.pragmaticobjects.oo.atom.banner;

import com.pragmaticobjects.oo.atom.tests.Assertion;
import org.assertj.core.api.Assertions;

/**
 * Asserts that the banner prints out specific contents.
 *
 * @author Kapralov Sergey
 */
public class AssertBannerContents implements Assertion {
    private final Banner banner;
    private final String contents;

    /**
     * Ctor.
     *
     * @param banner a banner under the tests.
     * @param contents contents expected to be printed.
     */
    public AssertBannerContents(final Banner banner, final String contents) {
        this.banner = banner;
        this.contents = contents;
    }

    @Override
    public final void check() throws Exception {
        banner.print(c -> Assertions.assertThat(c).isEqualTo(contents));
    }
}
