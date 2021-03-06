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
package org.praxislive.ide.core.ui;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.praxislive.core.ValueFormatException;
import org.praxislive.core.CallArguments;
import org.praxislive.core.ComponentAddress;
import org.praxislive.core.ControlAddress;
import org.praxislive.core.ComponentInfo;
import org.praxislive.core.protocols.ComponentProtocol;
import org.praxislive.core.services.RootManagerService;
import org.praxislive.core.protocols.StartableProtocol;
import org.praxislive.core.types.PBoolean;
import org.praxislive.core.types.PString;
import org.praxislive.impl.swing.ControlBinding;
import org.praxislive.ide.core.api.HubUnavailableException;
import org.praxislive.ide.util.ArgumentPropertyAdaptor;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Neil C Smith (http://neilcsmith.net)
 */
public class RootProxy {

    private final static Logger LOG = Logger.getLogger(RootProxy.class.getName());
    private final static String RESOURCE_DIR = "org/praxislive/ide/core/ui/resources/";

    private String id;
    private ComponentAddress address;
    private ControlAddress infoAddress;
    private ArgumentPropertyAdaptor.ReadOnly infoAdaptor;
    private ControlAddress runningAddress;
    private ArgumentPropertyAdaptor.ReadOnly runningAdaptor;
    private boolean startable;
    private boolean running;
    private Delegate node;

    RootProxy(String id) {
        this.id = id;
        address = ComponentAddress.create("/" + id);
        infoAddress = ControlAddress.create(address, ComponentProtocol.INFO);
        infoAdaptor = new ArgumentPropertyAdaptor.ReadOnly(null, ComponentProtocol.INFO, true, ControlBinding.SyncRate.Low);
        infoAdaptor.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateInfo();
            }
        });
        CoreHelper.getDefault().bind(infoAddress, infoAdaptor);
        runningAddress = ControlAddress.create(address, StartableProtocol.IS_RUNNING);
        updateInfo();
    }

    private void updateInfo() {
        LOG.info("RootProxy received Root Info");
        try {
            ComponentInfo info = ComponentInfo.coerce(infoAdaptor.getValue());
            if (info.hasProtocol(StartableProtocol.class)) {
                setStartable(true);
                LOG.info("Found Startable");
                return;
            }
        } catch (ValueFormatException ex) {
            // fall through
        }
        setStartable(false);
        refreshNode();
    }

    private void updateRunning() {
        try {
            PBoolean running = PBoolean.coerce(runningAdaptor.getValue());
            this.running = running.value();
        } catch (ValueFormatException ex) {
            running = false;
        }
        refreshNode();
    }

    private void setStartable(boolean startable) {
        if (startable) {
            this.startable = true;
            if (runningAdaptor == null) {
                runningAdaptor = new ArgumentPropertyAdaptor.ReadOnly(null, "is-running", true, ControlBinding.SyncRate.Low);
                runningAdaptor.addPropertyChangeListener(new PropertyChangeListener() {

                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        updateRunning();
                    }
                });
            }
            CoreHelper.getDefault().bind(runningAddress, runningAdaptor);
        } else {
            this.startable = false;
            if (runningAdaptor != null) {
                CoreHelper.getDefault().unbind(runningAdaptor);
            }
        }
    }

    void dispose() {
        CoreHelper.getDefault().unbind(infoAdaptor);
        if (runningAdaptor != null) {
            CoreHelper.getDefault().unbind(runningAdaptor);
        }
    }

    public String getID() {
        return id;
    }

    public Node getNodeDelegate() {
        if (node == null) {
            node = new Delegate(this);
        }
        return node;
    }

    private void refreshNode() {
        if (node != null) {
            node.refresh();
        }
    }

    private static class Delegate extends AbstractNode {

        private RootProxy root;
        private StartableAction startAction;
        private StartableAction stopAction;
        private DeleteAction deleteAction;

        private Delegate(RootProxy root) {
            super(Children.LEAF, Lookups.singleton(root));
            this.root = root;
            startAction = new StartableAction("Start", true);
            stopAction = new StartableAction("Stop", false);
            deleteAction = new DeleteAction();
            refresh();
        }

        private void refresh() {
            if (root.startable) {
                if (root.running) {
                    setDisplayName(root.id + " [active]");
                    setIconBaseWithExtension(RESOURCE_DIR + "root_active.png");
                    startAction.setEnabled(false);
                    stopAction.setEnabled(true);
                } else {
                    setDisplayName(root.id + " [idle]");
                    setIconBaseWithExtension(RESOURCE_DIR + "root_idle.png");
                    stopAction.setEnabled(false);
                    startAction.setEnabled(true);
                }
            } else {
                setDisplayName(root.id);
                setIconBaseWithExtension(RESOURCE_DIR + "root.png");
                startAction.setEnabled(false);
                stopAction.setEnabled(false);
            }
        }

        @Override
        public Action[] getActions(boolean context) {
            if (root.id.startsWith(HubProxy.SYSTEM_PREFIX)) {
                return new Action[0];
            }
            if (root.startable) {
                return new Action[]{
                    startAction,
                    stopAction,
                    null,
                    deleteAction
                };
            } else {
                return new Action[]{deleteAction};
            }
        }

        @Override
        public Action getPreferredAction() {
            if (startAction.isEnabled()) {
                return startAction;
            } else if (stopAction.isEnabled()) {
                return stopAction;
            } else {
                return null;
            }
        }

        private class StartableAction extends AbstractAction {

            private ControlAddress to;

            private StartableAction(String name, boolean start) {
                super(name);
                to = ControlAddress.create(root.address,
                        start ? StartableProtocol.START : StartableProtocol.STOP);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CoreHelper.getDefault().send(to, CallArguments.EMPTY, null);
                } catch (HubUnavailableException ex) {
                    Exceptions.printStackTrace(ex);
                }
                setEnabled(false);
            }
        }

        private class DeleteAction extends AbstractAction {

            private DeleteAction() {
                super("Delete");
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CoreHelper.getDefault().send(RootManagerService.class,
                            RootManagerService.REMOVE_ROOT,
                            CallArguments.create(PString.valueOf(root.id)), null);
                } catch (Exception ex) {
                    Exceptions.printStackTrace(ex);
                }
            }

        }

    }

}
