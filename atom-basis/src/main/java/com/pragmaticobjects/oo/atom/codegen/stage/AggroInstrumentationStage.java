package com.pragmaticobjects.oo.atom.codegen.stage;

import com.pragmaticobjects.oo.atom.banner.BnnrFromResource;
import com.pragmaticobjects.oo.atom.codegen.bcel.plugin.NopPlugin;
import com.pragmaticobjects.oo.atom.codegen.bcel.plugin.VerbosePlugin;

/**
 * Aggressive instrumentation stages.
 *
 * @author Kapralov Sergey
 */
public class AggroInstrumentationStage extends SequenceStage {
    /**
     * Ctor.
     */
    public AggroInstrumentationStage() {
        super(
            new ShowBannerStage(
                new BnnrFromResource(
                    "banner_aggro"
                )
            ),
            new ShowStatsStage(),
            new BcelStage(
                // @todo #99 In aggro mode, instrumentation must search for redundant object allocations and eliminate them
                new VerbosePlugin(
                    new NopPlugin()
                )
            )
        );
    }
}
