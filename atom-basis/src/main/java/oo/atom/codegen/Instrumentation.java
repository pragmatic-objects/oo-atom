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

import io.vavr.collection.List;
import io.vavr.control.Option;
import oo.atom.codegen.cn.ClassNames;
import oo.atom.codegen.cn.CnFromPath;
import oo.atom.codegen.cp.ClassPath;
import oo.atom.codegen.cp.CpCombined;
import oo.atom.codegen.cp.CpExplicit;
import oo.atom.codegen.cp.CpFromString;
import oo.atom.codegen.stage.Stage;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classes instrumentation
 *
 * @author Kapralov Sergey
 */
public interface Instrumentation {
    /**
     * Applies instumentation.
     */
    void apply();

    /**
     * Instrumentation that consists of a sequence of stages, which are applied at classes of the process working directory.
     *
     * @author Kapralov Sergey
     */
    class Implementation implements Instrumentation {
        private final List<Stage> stages;

        /**
         * Ctor.
         *
         * @param stages Stages to apply.
         */
        public Implementation(final List<Stage> stages) {
            this.stages = stages;
        }

        /**
         * Ctor.
         *
         * @param stages Stages to apply.
         */
        public Implementation(final Stage... stages) {
            this(
                List.of(stages)
            );
        }

        @Override
        public final void apply() {
            final Path workingDirectory = Option.of(System.getProperty("user.dir"))
                .map(Paths::get)
                .getOrElseThrow(RuntimeException::new);
            final ClassPath classPath = new CpCombined(
                new CpFromString(
                    System.getProperty("java.class.path")
                ),
                new CpExplicit(
                    workingDirectory
                )
            );
            final ClassNames classNames = new CnFromPath(
                workingDirectory
            );
            for(Stage stage : stages) {
                stage.apply(classPath, classNames, workingDirectory);
            }
        }
    }
}
