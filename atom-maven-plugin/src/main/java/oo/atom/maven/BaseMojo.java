package oo.atom.maven;

import oo.atom.codegen.Instrumentation;
import oo.atom.codegen.cp.ClassPath;
import oo.atom.codegen.cp.CpFromString;
import oo.atom.codegen.stage.Stage;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Collectors;

public abstract class BaseMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    protected final void doInstrumentation(Stage stage, Path workingDirectory) throws MojoExecutionException, MojoFailureException {
        String classPath = project.getArtifacts().stream()
                .map(Artifact::getFile)
                .map(File::toString)
                .collect(Collectors.joining(":"));
        ClassPath cp = new CpFromString(classPath);
        new Instrumentation.Implementation(
            cp,
            workingDirectory,
            stage
        ).apply();
    }
}
