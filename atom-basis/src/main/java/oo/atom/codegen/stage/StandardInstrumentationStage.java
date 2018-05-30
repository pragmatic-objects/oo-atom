package oo.atom.codegen.stage;

import oo.atom.banner.BnnrFromResource;
import oo.atom.codegen.bytebuddy.plugin.AnnotateClassesPlugin;
import oo.atom.codegen.bytebuddy.plugin.AnnotateInterfacesPlugin;
import oo.atom.codegen.bytebuddy.plugin.GenerateEqualsAndHashCodePlugin;
import oo.atom.codegen.bytebuddy.plugin.ValidateAtomAliases;
import oo.atom.codegen.javassist.plugin.InlineTemplates;
import oo.atom.codegen.javassist.plugin.MakeLambdaBreakers;

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
    }
}
