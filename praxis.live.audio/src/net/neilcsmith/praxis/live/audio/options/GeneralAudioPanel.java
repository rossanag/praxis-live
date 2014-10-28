/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2014 Neil C Smith.
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
package net.neilcsmith.praxis.live.audio.options;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import net.neilcsmith.praxis.audio.AudioSettings;

import org.jaudiolibs.audioservers.AudioServerProvider;
import org.jaudiolibs.audioservers.ext.Device;
import org.openide.util.Lookup;

final class GeneralAudioPanel extends javax.swing.JPanel {

    private final static String JAVASOUND = "JavaSound";
    private final static String JACK = "JACK";
    private final static DeviceInfo DEFAULT_DEVICE_INFO = new DeviceInfo(null);

//    private final static String[] libraries = new String[]{
//        "JavaSound", "JACK"
//    };
//    private final static String[] libraryDisplay = new String[]{
//        "JavaSound", "Jack Audio Connection Kit"
//    };
    private final static int[] buffersizes = new int[]{
        32, 64, 128, 256, 512, 1024, 2048, 4096
    };
//    private final static String[] buffersizeDisplay = new String[]{
//        "32", "64", "128", "256", "512", "1024", "2048", "4096"
//    };
    private final GeneralAudioOptionsPanelController controller;

    private final List<Library> libraries;

    GeneralAudioPanel(GeneralAudioOptionsPanelController controller) {
        this.controller = controller;
        this.libraries = initLibraries();
        initComponents();
        initChoosers();
    }

    private List<Library> initLibraries() {
        List<Library> libs = new ArrayList<>();
        for (AudioServerProvider prov
                : Lookup.getDefault().lookupAll(AudioServerProvider.class)) {
            libs.add(new Library(prov));
        }
        Collections.sort(libs, new Comparator<Library>() {

            @Override
            public int compare(Library lib1, Library lib2) {
                String n1 = lib1.provider.getLibraryName();
                String n2 = lib2.provider.getLibraryName();
                if (JAVASOUND.equals(n1)) {
                    return -1;
                } else if (JAVASOUND.equals(n2)) {
                    return 1;
                } else {
                    return n1.compareTo(n2);
                }
            }
        });
        return libs;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        defaultConfigPanel = new javax.swing.JPanel();
        defaultConfigDescription = new javax.swing.JLabel();
        libraryLbl = new javax.swing.JLabel();
        libraryChooser = new javax.swing.JComboBox();
        deviceLbl = new javax.swing.JLabel();
        deviceChooser = new javax.swing.JComboBox();
        buffersizeLbl = new javax.swing.JLabel();
        buffersizeChooser = new javax.swing.JComboBox();
        inputDeviceLbl = new javax.swing.JLabel();
        inputDeviceChooser = new javax.swing.JComboBox();
        slaveWarning = new javax.swing.JLabel();

        defaultConfigPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(GeneralAudioPanel.class, "GeneralAudioPanel.defaultConfigPanel.border.title"))); // NOI18N

        defaultConfigDescription.setForeground(javax.swing.UIManager.getDefaults().getColor("textInactiveText"));
        defaultConfigDescription.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(defaultConfigDescription, org.openide.util.NbBundle.getMessage(GeneralAudioPanel.class, "GeneralAudioPanel.defaultConfigDescription.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(libraryLbl, org.openide.util.NbBundle.getMessage(GeneralAudioPanel.class, "GeneralAudioPanel.libraryLbl.text")); // NOI18N

        libraryChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libraryChooserActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(deviceLbl, org.openide.util.NbBundle.getMessage(GeneralAudioPanel.class, "GeneralAudioPanel.deviceLbl.text")); // NOI18N

        deviceChooser.setEnabled(false);
        deviceChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deviceChooserActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(buffersizeLbl, org.openide.util.NbBundle.getMessage(GeneralAudioPanel.class, "GeneralAudioPanel.buffersizeLbl.text")); // NOI18N

        buffersizeChooser.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(inputDeviceLbl, org.openide.util.NbBundle.getMessage(GeneralAudioPanel.class, "GeneralAudioPanel.inputDeviceLbl.text")); // NOI18N

        inputDeviceChooser.setEnabled(false);

        slaveWarning.setFont(slaveWarning.getFont().deriveFont(slaveWarning.getFont().getStyle() | java.awt.Font.BOLD));
        slaveWarning.setForeground(javax.swing.UIManager.getDefaults().getColor("textInactiveText"));
        slaveWarning.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(slaveWarning, org.openide.util.NbBundle.getMessage(GeneralAudioPanel.class, "GeneralAudioPanel.slaveWarning.text")); // NOI18N

        javax.swing.GroupLayout defaultConfigPanelLayout = new javax.swing.GroupLayout(defaultConfigPanel);
        defaultConfigPanel.setLayout(defaultConfigPanelLayout);
        defaultConfigPanelLayout.setHorizontalGroup(
            defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(defaultConfigPanelLayout.createSequentialGroup()
                .addGroup(defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(defaultConfigPanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(defaultConfigDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(defaultConfigPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buffersizeLbl)
                            .addComponent(libraryLbl)
                            .addComponent(deviceLbl)
                            .addComponent(inputDeviceLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputDeviceChooser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buffersizeChooser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(libraryChooser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deviceChooser, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(slaveWarning, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        defaultConfigPanelLayout.setVerticalGroup(
            defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(defaultConfigPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(libraryChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(libraryLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deviceChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deviceLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputDeviceChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputDeviceLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buffersizeLbl)
                    .addComponent(buffersizeChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                .addComponent(defaultConfigDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slaveWarning)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(defaultConfigPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(defaultConfigPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void libraryChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libraryChooserActionPerformed
        Object o = libraryChooser.getSelectedItem();
        if (o instanceof Library) {
            initLibrary((Library) o);
        }
    }//GEN-LAST:event_libraryChooserActionPerformed

    private void deviceChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deviceChooserActionPerformed
        Object o = deviceChooser.getSelectedItem();
        if (o instanceof DeviceInfo) {
            DeviceInfo info = (DeviceInfo) o;
            if (info.device != null && info.device.getMaxInputChannels() > 0) {
                inputDeviceChooser.setSelectedItem(DEFAULT_DEVICE_INFO);
                inputDeviceChooser.setEnabled(false);
            } else {
                inputDeviceChooser.setEnabled(inputDeviceChooser.getItemCount() > 1);
            }
        }
    }//GEN-LAST:event_deviceChooserActionPerformed

    private void initChoosers() {
        for (Library lib : libraries) {
            libraryChooser.addItem(lib);
        }
        for (int bsize : buffersizes) {
            buffersizeChooser.addItem(bsize);
        }
    }

    void load() {
        String libName = AudioSettings.getLibrary();
        Library active = libraries.get(0);
        for (Library lib : libraries) {
            if (lib.provider.getLibraryName().equals(libName)) {
                active = lib;
                break;
            }
        }
        libraryChooser.setSelectedItem(active);
    }

    void store() {
        Object o = libraryChooser.getSelectedItem();
        if (o instanceof Library) {
            AudioSettings.setLibrary(((Library)o).provider.getLibraryName());
        }
        
        o = deviceChooser.getSelectedItem();
        if (o instanceof DeviceInfo) {
            DeviceInfo info = (DeviceInfo) o;
            if (info.device != null) {
                AudioSettings.setDeviceName(info.device.getName());
            } else {
                AudioSettings.setDeviceName(null);
            }
        }
        
        o = inputDeviceChooser.getSelectedItem();
        if (o instanceof DeviceInfo) {
            DeviceInfo info = (DeviceInfo) o;
            if (info.device != null) {
                AudioSettings.setInputDeviceName(info.device.getName());
            } else {
                AudioSettings.setInputDeviceName(null);
            }
        }
        
        if (buffersizeChooser.isEnabled()) {
            o = buffersizeChooser.getSelectedItem();
            if (o instanceof Integer) {
                AudioSettings.setBuffersize((int)o);
            }
        }
        
    }

    private void initLibrary(Library lib) {
        deviceChooser.removeAllItems();
        inputDeviceChooser.removeAllItems();
        String devName = AudioSettings.getDeviceName();
        String inDevName = AudioSettings.getInputDeviceName();
        DeviceInfo dev = DEFAULT_DEVICE_INFO;
        DeviceInfo inDev = DEFAULT_DEVICE_INFO;
        
        for (DeviceInfo devInfo : lib.devices) {
            deviceChooser.addItem(devInfo);
            if (devInfo.device != null && devInfo.device.getName().equals(devName)) {
                dev = devInfo;
            }
        }
        
        for (DeviceInfo devInfo : lib.inputDevices) {
            inputDeviceChooser.addItem(devInfo);
            if (devInfo.device != null && devInfo.device.getName().equals(inDevName)) {
                inDev = devInfo;
            }
        }
        
        inputDeviceChooser.setSelectedItem(inDev);
        deviceChooser.setSelectedItem(dev);
        
        int bsize = AudioSettings.getBuffersize();
        buffersizeChooser.setSelectedItem(bsize);
        
        inputDeviceChooser.setEnabled(inputDeviceChooser.getItemCount() > 1);
        deviceChooser.setEnabled(deviceChooser.getItemCount() > 1);
        buffersizeChooser.setEnabled(!JACK.equals(lib.provider.getLibraryName()));
    }




 

    boolean valid() {
        // check whether form is consistent and complete
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox buffersizeChooser;
    private javax.swing.JLabel buffersizeLbl;
    private javax.swing.JLabel defaultConfigDescription;
    private javax.swing.JPanel defaultConfigPanel;
    private javax.swing.JComboBox deviceChooser;
    private javax.swing.JLabel deviceLbl;
    private javax.swing.JComboBox inputDeviceChooser;
    private javax.swing.JLabel inputDeviceLbl;
    private javax.swing.JComboBox libraryChooser;
    private javax.swing.JLabel libraryLbl;
    private javax.swing.JLabel slaveWarning;
    // End of variables declaration//GEN-END:variables

    private static class Library {

        private final AudioServerProvider provider;
        private final String desc;
        private final DeviceInfo[] devices;
        private final DeviceInfo[] inputDevices;

        private Library(AudioServerProvider provider) {
            this.provider = provider;
            String str = provider.getLibraryDescription();
            if (!str.isEmpty()) {
                desc = provider.getLibraryName() + " : " + str;
            } else {
                desc = provider.getLibraryName();
            }
            List<DeviceInfo> outs = new ArrayList<>();
            List<DeviceInfo> ins = new ArrayList<>();
            outs.add(DEFAULT_DEVICE_INFO);
            ins.add(DEFAULT_DEVICE_INFO);
            for (Device device : provider.findAll(Device.class)) {
                if (device.getMaxOutputChannels() > 0) {
                    outs.add(new DeviceInfo(device));
                } else if (device.getMaxInputChannels() > 0) {
                    ins.add(new DeviceInfo(device));
                }
            }
            devices = outs.toArray(new DeviceInfo[outs.size()]);
            inputDevices = ins.toArray(new DeviceInfo[ins.size()]);
        }

        @Override
        public String toString() {
            return desc;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + Objects.hashCode(this.provider);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Library other = (Library) obj;
            if (!Objects.equals(this.provider, other.provider)) {
                return false;
            }
            return true;
        }

    }

    private static class DeviceInfo {

        private final Device device;

        private DeviceInfo(Device device) {
            this.device = device;
        }

        @Override
        public String toString() {
            return device == null ? "Default Device" : device.getName();
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 79 * hash + Objects.hashCode(this.device);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final DeviceInfo other = (DeviceInfo) obj;
            if (!Objects.equals(this.device, other.device)) {
                return false;
            }
            return true;
        }

    }

}
