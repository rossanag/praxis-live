package org.praxislive.ide.pxr.gui;

/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2011 Neil C Smith.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 3 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 3 for more details.
 *
 * You should have received a copy of the GNU General Public License version 3
 * along with this work; if not, see http://www.gnu.org/licenses/
 *
 *
 * Please visit http://neilcsmith.net if you need additional information or
 * have any questions.
 */



import org.praxislive.core.ComponentType;
import org.praxislive.ide.pxr.api.RootEditor;
import org.praxislive.ide.model.RootProxy;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Neil C Smith (http://neilcsmith.net)
 */
@ServiceProvider(service=RootEditor.Provider.class, position=10)
public class GuiEditorProvider implements RootEditor.Provider {

    @Override
    public RootEditor createEditor(RootProxy model) {
        ComponentType type = model.getType();
        if (type.toString().equals("root:gui")) {
            return new GuiEditor(model);
        }
        return null;
    }

}
