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

package oo.atom.codegen;

import oo.atom.anno.NotAtom;
import oo.atom.codegen.bytebuddy.plugin.EnforcingAtomPlugin;
import oo.atom.codegen.javassist.plugin.InlineTemplates;
import oo.atom.codegen.stage.ByteBuddyStage;
import oo.atom.codegen.stage.JavassistStage;

/**
 * Instrumentation entry point
 *
 * @author Kapralov Sergey
 */
@NotAtom
public class Main {
    private static final Instrumentation INSTRUMENTATION = new Instrumentation.Implementation(
        new JavassistStage(
            new oo.atom.codegen.javassist.plugin.VerbosePlugin(
                new InlineTemplates()
            )
        ),
        new ByteBuddyStage(
            new oo.atom.codegen.bytebuddy.plugin.VerbosePlugin(
                new EnforcingAtomPlugin()
            )
        )
    );

    /**
     * Main.
     *
     * @param args CLI arguments
     * @throws Exception If something goes wrong.
     */
    public static final void main(String... args) throws Exception {
        INSTRUMENTATION.apply();
    }
}
