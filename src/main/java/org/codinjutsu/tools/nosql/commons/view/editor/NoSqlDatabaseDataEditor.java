/*
 * Copyright (c) 2015 David Boissier
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codinjutsu.tools.nosql.commons.view.editor;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.fileEditor.FileEditorStateLevel;
import com.intellij.openapi.util.UserDataHolderBase;
import org.codinjutsu.tools.nosql.commons.view.NoSqlResultView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.beans.PropertyChangeListener;

public class NoSqlDatabaseDataEditor extends UserDataHolderBase implements FileEditor {


    private NoSqlResultView panel;
    private boolean disposed;

    public NoSqlDatabaseDataEditor(NoSqlResultView noSqlResultView) {
        panel = noSqlResultView;
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                panel.showResults();
            }
        });
    }

    @NotNull
    @Override
    public JComponent getComponent() {
        return isDisposed() ? new JPanel() : panel;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return panel == null ? null : panel.getResultPanel();
    }

    @NotNull
    @Override
    public String getName() {
        return "NoSql Data";
    }

    @Override
    public void dispose() {
        if (!disposed) {
            panel.dispose();
            panel = null;
            disposed = true;
        }
    }

    public boolean isDisposed() {
        return disposed;
    }

    @NotNull
    @Override
    public FileEditorState getState(@NotNull FileEditorStateLevel level) {
        return FileEditorState.INSTANCE;
    }

//    Unused methods

    @Override
    public void setState(@NotNull FileEditorState state) {

    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void selectNotify() {

    }

    @Override
    public void deselectNotify() {

    }

    @Override
    public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {

    }

    @Override
    public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {

    }

    @Nullable
    @Override
    public BackgroundEditorHighlighter getBackgroundHighlighter() {
        return null;
    }

    @Nullable
    @Override
    public FileEditorLocation getCurrentLocation() {
        return null;
    }

    @Nullable
    @Override
    public StructureViewBuilder getStructureViewBuilder() {
        return null;
    }
}
