package com.pragmaticobjects.oo.atom.maven;

import com.pragmaticobjects.oo.atom.codegen.cp.ClassPath;
import com.pragmaticobjects.oo.atom.codegen.cp.CpFromString;
import com.pragmaticobjects.oo.atom.codegen.stage.Stage;
import com.pragmaticobjects.oo.atom.instrumentation.ApplyStages;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Collectors;

/**
 * Base Mojo for different instrumentations
 *
 * @author Kapralov Sergey
 */
public abstract class BaseMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;
    @Parameter(defaultValue = "false", required = true, readonly = true)
    private boolean instrumentPomProjects;

    protected final void doInstrumentation(Stage stage, Path workingDirectory) throws MojoExecutionException, MojoFailureException {
        if(!project.getPackaging().equals("pom") || instrumentPomProjects) {
            String classPath = project.getArtifacts().stream()
                    .map(Artifact::getFile)
                    .map(File::toString)
                    .collect(Collectors.joining(":"));
            ClassPath cp = new CpFromString(classPath);
            new ApplyStages(
                    cp,
                    workingDirectory,
                    stage
            ).apply();
        }
    }
}
