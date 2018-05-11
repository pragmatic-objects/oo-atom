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
package oo.atom.codegen.javapoet;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

import javax.lang.model.element.Modifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.file.Path;
import java.util.stream.Collectors;

/**
 * Simple parameter-less Java annotation
 *
 * @author Kapralov Sergey
 */
public class JpAnnotation implements JavapoetSource {
    private final Set<ElementType> targets;
    private final RetentionPolicy retentionPolicy;
    private final String packageName;
    private final String className;

    /**
     * Ctor.
     *
     * @param packageName Package.
     * @param className Annotation's name.
     * @param retentionPolicy Annotation's retention policy.
     * @param targets Annotation targets.
     */
    public JpAnnotation(String packageName, String className, RetentionPolicy retentionPolicy, Set<ElementType> targets) {
        this.targets = targets;
        this.retentionPolicy = retentionPolicy;
        this.packageName = packageName;
        this.className = className;
    }

    /**
     * Ctor.
     *
     * @param packageName Package.
     * @param className Annotation's name.
     * @param retentionPolicy Annotation's retention policy.
     * @param targets Annotation targets.
     */
    public JpAnnotation(String packageName, String className, RetentionPolicy retentionPolicy, ElementType... targets) {
        this(packageName, className, retentionPolicy, HashSet.of(targets));
    }

    @Override
    public final void saveIn(Path directory) {
        try {
            final String targetsLiteral = this.targets
                    .map(ElementType::name)
                    .collect(Collectors.joining(", ", "{", "}"));
            final AnnotationSpec retention = AnnotationSpec.builder(Retention.class)
                    .addMember("value", "$L", this.retentionPolicy)
                    .build();
            final AnnotationSpec target = AnnotationSpec.builder(Target.class)
                    .addMember("value", "$L", targetsLiteral)
                    .build();
            final TypeSpec typeSpec = TypeSpec.annotationBuilder(className)
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(retention)
                    .addAnnotation(target)
                    .build();
            JavaFile.builder(packageName, typeSpec)
                    .addStaticImport(RetentionPolicy.class, retentionPolicy.name())
                    .addStaticImport(ElementType.class, "*")
                    .build().writeTo(directory);
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
