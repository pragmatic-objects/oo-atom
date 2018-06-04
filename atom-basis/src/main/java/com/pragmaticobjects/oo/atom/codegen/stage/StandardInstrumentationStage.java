package com.pragmaticobjects.oo.atom.codegen.stage;

import com.pragmaticobjects.oo.atom.banner.BnnrFromResource;
import com.pragmaticobjects.oo.atom.codegen.bytebuddy.plugin.AnnotateClassesPlugin;
import com.pragmaticobjects.oo.atom.codegen.bytebuddy.plugin.AnnotateInterfacesPlugin;
import com.pragmaticobjects.oo.atom.codegen.bytebuddy.plugin.GenerateEqualsAndHashCodePlugin;
import com.pragmaticobjects.oo.atom.codegen.bytebuddy.plugin.ValidateAtomAliases;
import com.pragmaticobjects.oo.atom.codegen.javassist.plugin.InlineTemplates;
import com.pragmaticobjects.oo.atom.codegen.javassist.plugin.MakeLambdaBreakers;

/**
 * Standard instrumentation scenario
 *
 * @author Kapralov Sergey
 */
public class StandardInstrumentationStage extends SequenceStage {
    /**
     * Ctor.
     */
    public StandardInstrumentationStage() {
        super(
            new ShowBannerStage(
                new BnnrFromResource(
                    "banner"
                )
            ),
            new ShowStatsStage(),
            new ByteBuddyStage(
                new com.pragmaticobjects.oo.atom.codegen.bytebuddy.plugin.VerbosePlugin(
                    new AnnotateInterfacesPlugin()
                )
            ),
            new ByteBuddyStage(
                new com.pragmaticobjects.oo.atom.codegen.bytebuddy.plugin.VerbosePlugin(
                    new AnnotateClassesPlugin()
                )
            ),
            new JavassistStage(
                new com.pragmaticobjects.oo.atom.codegen.javassist.plugin.VerbosePlugin(
                    new MakeLambdaBreakers()
                )
            ),
            new JavassistStage(
                new com.pragmaticobjects.oo.atom.codegen.javassist.plugin.VerbosePlugin(
                    new InlineTemplates()
                )
            ),
            new ByteBuddyStage(
                new com.pragmaticobjects.oo.atom.codegen.bytebuddy.plugin.VerbosePlugin(
                    new GenerateEqualsAndHashCodePlugin()
                )
            ),
            new ByteBuddyStage(
                new com.pragmaticobjects.oo.atom.codegen.bytebuddy.plugin.VerbosePlugin(
                    new ValidateAtomAliases()
                )
            )
        );
    }
}
