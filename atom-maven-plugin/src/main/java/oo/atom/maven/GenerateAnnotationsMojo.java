package oo.atom.maven;

import oo.atom.codegen.javapoet.JpAtomAnnotation;
import oo.atom.codegen.javapoet.JpNotAtomAnnotation;
import oo.atom.instrumentation.GenerateSources;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.nio.file.Paths;

@Mojo(name = "generate-annotations", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class GenerateAnnotationsMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project.basedir}/target/generated-sources/atom", required = true, readonly = true)
    private String workingDirectory;
    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;
    @Parameter(defaultValue = "false", required = true, readonly = true)
    private boolean instrumentPomProjects;

    @Override
    public final void execute() throws MojoExecutionException, MojoFailureException {
        if(!project.getPackaging().equals("pom") || instrumentPomProjects) {
            new GenerateSources(
                    Paths.get(workingDirectory),
                    new JpAtomAnnotation(),
                    new JpNotAtomAnnotation()
            ).apply();
            project.addCompileSourceRoot(workingDirectory);
        }
    }
}
