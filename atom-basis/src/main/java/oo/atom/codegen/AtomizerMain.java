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

import oo.atom.anno.Atom;
import oo.atom.anno.NotAtom;
import oo.atom.banner.BnnrFromResource;
import oo.atom.codegen.bytebuddy.plugin.*;
import oo.atom.codegen.cn.CnExplicit;
import oo.atom.codegen.javassist.plugin.InlineTemplates;
import oo.atom.codegen.javassist.plugin.MakeLambdaBreakers;
import oo.atom.codegen.stage.*;

/**
 * Instrumentation entry point
 *
 * @author Kapralov Sergey
 */
@NotAtom
public class AtomizerMain {
    private static final Instrumentation INSTRUMENTATION = new Instrumentation.Implementation(
        new ShowBannerStage(
            new BnnrFromResource(
                "banner"
            )
        ),
        new ShowStatsStage(),
        new OverrideClassesStage(
            new ByteBuddyStage(
                new NopPlugin()
            ),
            new CnExplicit(
                Atom.class.getName(),
                NotAtom.class.getName()
            )
        ),
        new ByteBuddyStage(
            new oo.atom.codegen.bytebuddy.plugin.VerbosePlugin(
                new AnnotateInterfacesPlugin()
            )
        ),
        new ByteBuddyStage(
            new oo.atom.codegen.bytebuddy.plugin.VerbosePlugin(
                new AnnotateClassesPlugin()
            )
        ),
        new JavassistStage(
            new oo.atom.codegen.javassist.plugin.VerbosePlugin(
                new MakeLambdaBreakers()
            )
        ),
        new JavassistStage(
            new oo.atom.codegen.javassist.plugin.VerbosePlugin(
                new InlineTemplates()
            )
        ),
        new ByteBuddyStage(
            new oo.atom.codegen.bytebuddy.plugin.VerbosePlugin(
                new GenerateEqualsAndHashCodePlugin()
            )
        ),
        new ByteBuddyStage(
            new oo.atom.codegen.bytebuddy.plugin.VerbosePlugin(
                new ValidateAtomAliases()
            )
        )
    );

    private static final Instrumentation INSTRUMENTATION_AGGRO = new Instrumentation.Implementation(
        new ShowBannerStage(
            new BnnrFromResource(
                "banner_aggro"
            )
        ),
        new ShowStatsStage(),
        new BcelStage(
            // @todo #99 In aggro mode, instrumentation must search for redundant object allocations and eliminate them
            new oo.atom.codegen.bcel.plugin.VerbosePlugin(
                new oo.atom.codegen.bcel.plugin.NopPlugin()
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
        if(args.length > 0 && args[0].equals("-aggro")) {
            INSTRUMENTATION_AGGRO.apply();
        } else {
            INSTRUMENTATION.apply();
        }
    }
}
