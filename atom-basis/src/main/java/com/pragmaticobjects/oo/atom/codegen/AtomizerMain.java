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

package com.pragmaticobjects.oo.atom.codegen;

import com.pragmaticobjects.oo.atom.anno.NotAtom;
import com.pragmaticobjects.oo.atom.codegen.cn.CnFromPath;
import com.pragmaticobjects.oo.atom.codegen.cp.CpFromString;
import com.pragmaticobjects.oo.atom.codegen.stage.AggroInstrumentationStage;
import com.pragmaticobjects.oo.atom.codegen.stage.CopyAtomAnnotations;
import com.pragmaticobjects.oo.atom.codegen.stage.Stage;
import com.pragmaticobjects.oo.atom.codegen.stage.StandardInstrumentationStage;
import com.pragmaticobjects.oo.atom.instrumentation.ApplyStages;
import io.vavr.control.Option;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Instrumentation entry point
 *
 * @author Kapralov Sergey
 */
@NotAtom
public class AtomizerMain {
    /**
     * Main.
     *
     * @param args CLI arguments
     * @throws Exception If something goes wrong.
     */
    public static final void main(String... args) throws Exception {
        final Stage stage;
        if(args.length > 0 && args[0].equals("-aggro")) {
            stage = new AggroInstrumentationStage();
        } else {
            stage = new StandardInstrumentationStage();
        }
        final Path workingDirectory = Option.of(System.getProperty("user.dir"))
                .map(Paths::get)
                .getOrElseThrow(RuntimeException::new);
        new ApplyStages(
            new CpFromString(
                System.getProperty("java.class.path")
            ),
            workingDirectory,
            new CnFromPath(
                workingDirectory
            ),
            stage,
            new CopyAtomAnnotations()
        ).apply();
    }
}
