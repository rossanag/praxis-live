/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2012 Neil C Smith.
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
package org.praxislive.ide.pxr;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JToggleButton;
import org.praxislive.core.Value;
import org.praxislive.core.CallArguments;
import org.praxislive.core.ControlAddress;
import org.praxislive.core.protocols.StartableProtocol;
import org.praxislive.core.types.PBoolean;
import org.praxislive.impl.swing.ControlBinding.SyncRate;
import org.praxislive.ide.core.api.HubUnavailableException;
import org.praxislive.ide.model.RootProxy;
import org.praxislive.ide.util.ArgumentPropertyAdaptor;
import org.openide.util.ContextAwareAction;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;
import org.openide.util.actions.Presenter;

/**
 *
 * @author Neil C Smith <http://neilcsmith.net>
 */
class StartableRootAction extends AbstractAction
        implements ContextAwareAction, Presenter.Toolbar, LookupListener {
    
    private final static String RESOURCE_DIR = "org/praxislive/ide/pxr/resources/";
    
    private Lookup.Result<PXRRootContext> result;
    private RootProxy root;
    private JToggleButton button;
    private boolean running;
    private ArgumentPropertyAdaptor.ReadOnly runningAdaptor;
    
    
    StartableRootAction() {
        this(Utilities.actionsGlobalContext());
    }
    
    StartableRootAction(Lookup context) {
        super("", ImageUtilities.loadImageIcon(RESOURCE_DIR + "play.png", true));
        this.result = context.lookupResult(PXRRootContext.class);
        this.result.addLookupListener(this);
        putValue(SELECTED_KEY, Boolean.FALSE);
        setEnabled(false);
        resultChanged(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            ControlAddress to;
            if (getValue(SELECTED_KEY) == Boolean.TRUE) {
                to = ControlAddress.create(root.getAddress(), StartableProtocol.START);
            } else {
                to = ControlAddress.create(root.getAddress(), StartableProtocol.STOP);
            }
            PXRHelper.getDefault().send(to, CallArguments.EMPTY, null);
        } catch (HubUnavailableException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public Action createContextAwareInstance(Lookup actionContext) {
        return new StartableRootAction(actionContext);
    }

    @Override
    public Component getToolbarPresenter() {
        if (button == null) {
            button = new JToggleButton(this);
//            button.setIcon(ImageUtilities.loadImageIcon(
//                    RESOURCE_DIR + "pxr-toggle-idle.png", true));
//            button.setSelectedIcon(ImageUtilities.loadImageIcon(
//                    RESOURCE_DIR + "pxr-toggle-active.png", true));
        }
        return button;
    }

    @Override
    public final void resultChanged(LookupEvent ev) {
        if (root != null) {
            reset();
        }
        Collection<? extends PXRRootContext> roots = result.allInstances();
        if (roots.isEmpty()) {
            return;
        }
        setup(roots.iterator().next().getRoot());
    }
    
    private void reset() {
        root = null;
        putValue(SELECTED_KEY, Boolean.FALSE);
        setEnabled(false);
        PXRHelper.getDefault().unbind(runningAdaptor);
        runningAdaptor = null;
    }
    
    private void setup(PXRRootProxy root) {
        this.root = root;
        if (!isStartable(root)) {
            return;
        }
        setEnabled(true);
        runningAdaptor = new ArgumentPropertyAdaptor.ReadOnly(this, 
                StartableProtocol.IS_RUNNING, false, SyncRate.Low);
        runningAdaptor.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                try {
                    PBoolean selected = PBoolean.coerce((Value)pce.getNewValue());
                    putValue(SELECTED_KEY, selected.value());
                } catch (Exception ex) {
                    // no op?
                }             
            }
        });
        PXRHelper.getDefault().bind(ControlAddress.create(root.getAddress(), StartableProtocol.IS_RUNNING),
                runningAdaptor);
    }
    

    
    private boolean isStartable(PXRRootProxy root) {
        return root.getInfo().hasProtocol(StartableProtocol.class);
    }
    
}
