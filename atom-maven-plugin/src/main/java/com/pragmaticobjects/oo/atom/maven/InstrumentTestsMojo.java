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
package com.pragmaticobjects.oo.atom.maven;

import com.pragmaticobjects.oo.atom.codegen.cp.CpCombined;
import com.pragmaticobjects.oo.atom.codegen.cp.CpFromString;
import com.pragmaticobjects.oo.atom.codegen.stage.StandardInstrumentationStage;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.nio.file.Paths;

/**
 * Mojo that instruments test code.
 *
 * @author Kapralov Sergey
 */
@Mojo(name = "instrument-tests", defaultPhase = LifecyclePhase.PROCESS_TEST_CLASSES, requiresDependencyResolution = ResolutionScope.TEST)
public class InstrumentTestsMojo extends BaseMojo {
    @Parameter(defaultValue = "${project.build.outputDirectory}", required = true, readonly = true)
    protected String outputDirectory;
    @Parameter(defaultValue = "${project.build.testOutputDirectory}", required = true, readonly = true)
    protected String testOutputDirectory;

    @Parameter(defaultValue = "false", required = true, readonly = true)
    protected boolean stubbedInstrumentation;
    
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        doInstrumentation(
            new StandardInstrumentationStage(stubbedInstrumentation),
            new CpCombined(
                buildClassPath(),
                new CpFromString(
                    outputDirectory
                )
            ),
            Paths.get(testOutputDirectory)
        );
    }
}
