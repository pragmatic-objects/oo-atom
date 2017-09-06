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
package oo.atom.codegen.bytebuddy.smt.internals;

import io.vavr.collection.List;
import java.util.IdentityHashMap;
import java.util.Objects;
import java.util.Random;
import java.util.Stack;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.Attribute;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.TypePath;
import oo.atom.anno.NotAtom;

/**
 * A customized mock, which is dedicated to assertions, which check the bytecode generation.
 * Two instances of {@link oo.atom.codegen.bytebuddy.smt.internals.MethodVisitorRecorder}
 * are equal if they produce semantically the same Java bytecode
 * 
 * @author Kapralov Sergey
 */
@NotAtom
public final class MethodVisitorRecorder extends MethodVisitor {
    private final Random random = new Random(42);
    private final Stack<List<Object>> recordedCalls = new Stack<>();
    private final IdentityHashMap<Attribute, Long> attributesTable = new IdentityHashMap<>();
    private final IdentityHashMap<Label, Long> labelsTable = new IdentityHashMap<>();

    public MethodVisitorRecorder() {
        super(Opcodes.ASM5);
    }

    private final Long resolveAttribute(Attribute attr) {
        return attributesTable.computeIfAbsent(attr, k -> random.nextLong());
    }

    private final Long resolveLabel(Label lbl) {
        return labelsTable.computeIfAbsent(lbl, k -> random.nextLong());
    }

    @Override
    public AnnotationVisitor visitAnnotation(String string, boolean bln) {
        recordedCalls.add(List.of("visitAnnotation", string, bln));
        return super.visitAnnotation(string, bln);
    }

    @Override
    public AnnotationVisitor visitAnnotationDefault() {
        recordedCalls.add(List.of("visitAnnotationDefault"));
        return super.visitAnnotationDefault();
    }

    @Override
    public void visitAttribute(Attribute atrbt) {
        recordedCalls.add(List.of(
                "visitAttributeDefault",
                resolveAttribute(atrbt)
        ));
        super.visitAttribute(atrbt);
    }

    @Override
    public void visitCode() {
        recordedCalls.add(List.of("visitCode"));
        super.visitCode();
    }

    @Override
    public void visitEnd() {
        recordedCalls.add(List.of("visitEnd"));
        super.visitEnd();
    }

    @Override
    public void visitFieldInsn(int i, String string, String string1, String string2) {
        recordedCalls.add(List.of(
                "visitFieldInsn",
                string,
                string1,
                string2
        ));
        super.visitFieldInsn(i, string, string1, string2);
    }

    @Override
    public void visitFrame(int i, int i1, Object[] os, int i2, Object[] os1) {
        recordedCalls.add(List.of(
                "visitFrame",
                i, i1,
                List.of(os),
                i2,
                List.of(os1)
        ));
        super.visitFrame(i, i1, os, i2, os1);
    }

    @Override
    public void visitIincInsn(int i, int i1) {
        recordedCalls.add(List.of(
                "visitIincInsn",
                i, i1
        ));
        super.visitIincInsn(i, i1);
    }

    @Override
    public void visitInsn(int i) {
        recordedCalls.add(List.of(
                "visitInsn",
                i
        ));
        super.visitInsn(i);
    }

    @Override
    public AnnotationVisitor visitInsnAnnotation(int i, TypePath tp, String string, boolean bln) {
        recordedCalls.add(List.of(
                "visitInsnAnnotation",
                i, tp.toString(),
                string, bln
        ));
        return super.visitInsnAnnotation(i, tp, string, bln);
    }

    @Override
    public void visitIntInsn(int i, int i1) {
        recordedCalls.add(List.of(
                "visitIntInsn",
                i, i1
        ));
        super.visitIntInsn(i, i1);
    }

    @Override
    public void visitInvokeDynamicInsn(String string, String string1, Handle handle, Object... os) {
        recordedCalls.add(List.of(
                "visitInvokeDynamicInsn",
                string, string1, handle, List.of(os)
        ));
        super.visitInvokeDynamicInsn(string, string1, handle, os);
    }

    @Override
    public void visitJumpInsn(int i, Label label) {
        recordedCalls.add(List.of(
                "visitJumpInsn",
                i, resolveLabel(label)
        ));
        super.visitJumpInsn(i, label);
    }

    @Override
    public void visitLabel(Label label) {
        recordedCalls.add(List.of(
                "visitLabel",
                resolveLabel(label)
        ));
        super.visitLabel(label);
    }

    @Override
    public void visitLdcInsn(Object o) {
        recordedCalls.add(List.of(
                "visitLdcInsn",
                o
        ));
        super.visitLdcInsn(o);
    }

    @Override
    public void visitLineNumber(int i, Label label) {
        recordedCalls.add(List.of(
                "visitLineNumber",
                i, resolveLabel(label)
        ));
        super.visitLineNumber(i, label);
    }

    @Override
    public void visitLocalVariable(String string, String string1, String string2, Label label, Label label1, int i) {
        recordedCalls.add(List.of(
                "visitLocalVariable",
                string, string1, string2,
                resolveLabel(label),
                resolveLabel(label1),
                i
        ));
        super.visitLocalVariable(string, string1, string2, label, label1, i);
    }

    @Override
    public AnnotationVisitor visitLocalVariableAnnotation(int i, TypePath tp, Label[] labels, Label[] labels1, int[] ints, String string, boolean bln) {
        recordedCalls.add(List.of(
                "visitLocalVariableAnnotation",
                tp.toString(),
                List.of(labels).map(this::resolveLabel),
                List.of(labels1).map(this::resolveLabel),
                List.of(ints),
                string, bln
        ));
        return super.visitLocalVariableAnnotation(i, tp, labels, labels1, ints, string, bln);
    }

    @Override
    public void visitLookupSwitchInsn(Label label, int[] ints, Label[] labels) {
        recordedCalls.add(List.of(
                "visitLookupSwitchInsn",
                resolveLabel(label),
                List.of(ints),
                List.of(labels).map(this::resolveLabel)
        ));
        super.visitLookupSwitchInsn(label, ints, labels);
    }

    @Override
    public void visitMaxs(int i, int i1) {
        recordedCalls.add(List.of(
                "visitMaxs",
                i, i1
        ));
        super.visitMaxs(i, i1);
    }

    @Override
    public void visitMethodInsn(int i, String string, String string1, String string2) {
        recordedCalls.add(List.of(
                "visitMethodInsn",
                i, string, string1, string2
        ));

        super.visitMethodInsn(i, string, string1, string2);
    }

    @Override
    public void visitMethodInsn(int i, String string, String string1, String string2, boolean bln) {
        recordedCalls.add(List.of(
                "visitMethodInsn",
                i, string, string1, string2, bln
        ));
        super.visitMethodInsn(i, string, string1, string2, bln);
    }

    @Override
    public void visitMultiANewArrayInsn(String string, int i) {
        recordedCalls.add(List.of(
                "visitMultiANewArrayInsn",
                string, i
        ));
        super.visitMultiANewArrayInsn(string, i);
    }

    @Override
    public void visitParameter(String string, int i) {
        recordedCalls.add(List.of(
                "visitParameter",
                string, i
        ));
        super.visitParameter(string, i);
    }

    @Override
    public AnnotationVisitor visitParameterAnnotation(int i, String string, boolean bln) {
        recordedCalls.add(List.of(
                "visitParameterAnnotation",
                i, string, bln
        ));
        return super.visitParameterAnnotation(i, string, bln);
    }

    @Override
    public void visitTableSwitchInsn(int i, int i1, Label label, Label... labels) {
        recordedCalls.add(List.of(
                "visitTableSwitchInsn",
                i, i1, resolveLabel(label),
                List.of(labels).map(this::resolveLabel)
        ));
        super.visitTableSwitchInsn(i, i1, label, labels);
    }

    @Override
    public AnnotationVisitor visitTryCatchAnnotation(int i, TypePath tp, String string, boolean bln) {
        recordedCalls.add(List.of(
                "visitTryCatchAnnotation",
                i, tp.toString(),
                string, bln
        ));
        return super.visitTryCatchAnnotation(i, tp, string, bln);
    }

    @Override
    public void visitTryCatchBlock(Label label, Label label1, Label label2, String string) {
        recordedCalls.add(List.of(
                "visitTryCatchBlock",
                resolveLabel(label),
                resolveLabel(label1),
                resolveLabel(label2),
                string
        ));
        super.visitTryCatchBlock(label, label1, label2, string);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int i, TypePath tp, String string, boolean bln) {
        recordedCalls.add(List.of(
                "visitTypeAnnotation",
                i, tp.toString(),
                string, bln
        ));
        return super.visitTypeAnnotation(i, tp, string, bln);
    }

    @Override
    public void visitTypeInsn(int i, String string) {
        recordedCalls.add(List.of(
                "visitTypeInsn",
                i, string
        ));
        super.visitTypeInsn(i, string);
    }

    @Override
    public void visitVarInsn(int i, int i1) {
        recordedCalls.add(List.of(
                "visitVarInsn",
                i, i1
        ));
        super.visitVarInsn(i, i1);
    }

    public void trace() {
        recordedCalls.forEach(System.out::println);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MethodVisitorRecorder) {
            MethodVisitorRecorder mvr = (MethodVisitorRecorder) obj;
            return mvr.recordedCalls.equals(this.recordedCalls);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.recordedCalls);
    }
}
