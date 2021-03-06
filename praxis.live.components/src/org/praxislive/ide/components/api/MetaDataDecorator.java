/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2013 Neil C Smith.
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
package org.praxislive.ide.components.api;

import java.awt.Image;
import java.util.Optional;
import org.praxislive.core.services.ComponentFactory;
import org.praxislive.core.ComponentType;
import org.praxislive.core.Lookup;
import org.praxislive.impl.InstanceLookup;
import org.praxislive.meta.DisplayNameProvider;
import org.praxislive.meta.IconProvider;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Neil C Smith
 */
public class MetaDataDecorator<T> extends ComponentFactory.MetaData<T> {
    
    private final ComponentFactory.MetaData<T> delegate;
    private final Lookup lookup;
    
    public MetaDataDecorator(ComponentFactory.MetaData<T> delegate, Object ... exts) {
        if (exts.length == 0) {
            lookup = delegate.getLookup();
        } else {
            lookup = InstanceLookup.create(delegate.getLookup(), exts);
        }
        this.delegate = delegate;    
    }

    @Override
    public boolean isDeprecated() {
        return delegate.isDeprecated();
    }

    @Override
    public Optional<ComponentType> findReplacement() {
        return delegate.findReplacement();
    }

    @Override
    public Lookup getLookup() {
        return lookup;
    }

    public static class DisplayName implements DisplayNameProvider {

        private final String name;
        
        public DisplayName(String name) {
            if (name == null) {
                throw new NullPointerException();
            }
            this.name = name;
        }
        
        @Override
        public String getDisplayName() {
            return name;
        }
        
    }
    
    public static class Icon implements IconProvider {
        
        private final String location;
        
        public Icon(String location) {
            if (location == null) {
                throw new NullPointerException();
            }
            this.location = location;
        }

        @Override
        public Image getIcon(int width, int height) {
            return ImageUtilities.loadImage(location, true);
        }
        
    }
    
}
