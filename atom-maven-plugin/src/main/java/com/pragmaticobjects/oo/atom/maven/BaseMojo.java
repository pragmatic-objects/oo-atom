package com.pragmaticobjects.oo.atom.maven;

import com.pragmaticobjects.oo.atom.codegen.cn.CnExcludingPackages;
import com.pragmaticobjects.oo.atom.codegen.cn.CnFromPath;
import com.pragmaticobjects.oo.atom.codegen.cp.ClassPath;
import com.pragmaticobjects.oo.atom.codegen.cp.CpFromString;
import com.pragmaticobjects.oo.atom.codegen.stage.Stage;
import com.pragmaticobjects.oo.atom.instrumentation.ApplyStages;
import io.vavr.collection.HashSet;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.maven.plugin.descriptor.PluginDescriptor;

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
    @Parameter
    private String[] excludePackages;

    protected final ClassPath buildClassPath() {
        PluginDescriptor pluginDescriptor = (PluginDescriptor) this.getPluginContext().get("pluginDescriptor");
        String classPath = HashSet.ofAll(project.getArtifacts())
                    .addAll(pluginDescriptor.getArtifacts())
                    .map(Artifact::getFile)
                    .map(File::toString)
                    .collect(Collectors.joining(":"));
        ClassPath cp = new CpFromString(classPath);
        return cp;
    }
    
    protected final void doInstrumentation(Stage stage, ClassPath cp, Path workingDirectory) throws MojoExecutionException, MojoFailureException {
        if(!project.getPackaging().equals("pom") || instrumentPomProjects) {
            new ApplyStages(
                cp,
                workingDirectory,
                new CnExcludingPackages(
                    new CnFromPath(
                        workingDirectory
                    ),
                    Optional.ofNullable(excludePackages).orElse(new String[] {})
                ),
                stage
            ).apply();
        }
    }
}
