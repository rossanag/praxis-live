/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2016 Neil C Smith.
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
package org.praxislive.ide.video.options;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.praxislive.video.gstreamer.configuration.GStreamerSettings;



final class CapturePanel extends javax.swing.JPanel {
    
    private final CaptureOptionsPanelController controller;

    CapturePanel(CaptureOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        initDocumentListener();
    }
    
    private void initDocumentListener() {
        DocumentListener l = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                controller.changed();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                controller.changed();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                controller.changed();
            }
        };
        devInput1.getDocument().addDocumentListener(l);
        devInput2.getDocument().addDocumentListener(l);
        devInput3.getDocument().addDocumentListener(l);
        devInput4.getDocument().addDocumentListener(l);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        defaultConfigPanel = new javax.swing.JPanel();
        devLbl1 = new javax.swing.JLabel();
        devInput1 = new javax.swing.JTextField();
        devInput2 = new javax.swing.JTextField();
        devInput4 = new javax.swing.JTextField();
        devInput3 = new javax.swing.JTextField();
        devLbl2 = new javax.swing.JLabel();
        devLbl3 = new javax.swing.JLabel();
        devLbl4 = new javax.swing.JLabel();
        resetBtn1 = new javax.swing.JButton();
        resetBtn2 = new javax.swing.JButton();
        resetBtn3 = new javax.swing.JButton();
        resetBtn4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        libPathTextField = new javax.swing.JTextField();
        libPathLabel = new javax.swing.JLabel();
        libPathReset = new javax.swing.JButton();

        defaultConfigPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.defaultConfigPanel.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(devLbl1, org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.devLbl1.text")); // NOI18N

        devInput1.setText(org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.devInput1.text")); // NOI18N

        devInput2.setText(org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.devInput2.text")); // NOI18N

        devInput4.setText(org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.devInput4.text")); // NOI18N

        devInput3.setText(org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.devInput3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(devLbl2, org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.devLbl2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(devLbl3, org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.devLbl3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(devLbl4, org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.devLbl4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(resetBtn1, org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.resetBtn1.text")); // NOI18N
        resetBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtn1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(resetBtn2, org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.resetBtn2.text")); // NOI18N
        resetBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtn2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(resetBtn3, org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.resetBtn3.text")); // NOI18N
        resetBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtn3ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(resetBtn4, org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.resetBtn4.text")); // NOI18N
        resetBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtn4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout defaultConfigPanelLayout = new javax.swing.GroupLayout(defaultConfigPanel);
        defaultConfigPanel.setLayout(defaultConfigPanelLayout);
        defaultConfigPanelLayout.setHorizontalGroup(
            defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(defaultConfigPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(defaultConfigPanelLayout.createSequentialGroup()
                        .addComponent(devLbl4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(devInput4))
                    .addGroup(defaultConfigPanelLayout.createSequentialGroup()
                        .addComponent(devLbl3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(devInput3))
                    .addGroup(defaultConfigPanelLayout.createSequentialGroup()
                        .addComponent(devLbl2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(devInput2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, defaultConfigPanelLayout.createSequentialGroup()
                        .addComponent(devLbl1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(devInput1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resetBtn1)
                    .addComponent(resetBtn2)
                    .addComponent(resetBtn3)
                    .addComponent(resetBtn4)))
        );
        defaultConfigPanelLayout.setVerticalGroup(
            defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(defaultConfigPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(devLbl1)
                    .addComponent(devInput1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBtn1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(devLbl2)
                    .addComponent(devInput2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBtn2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(devLbl3)
                    .addComponent(devInput3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBtn3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(devLbl4)
                    .addComponent(devInput4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBtn4)))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.jPanel1.border.title"))); // NOI18N
        jPanel1.setEnabled(false);

        libPathTextField.setText(org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.libPathTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(libPathLabel, org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.libPathLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(libPathReset, org.openide.util.NbBundle.getMessage(CapturePanel.class, "CapturePanel.libPathReset.text")); // NOI18N
        libPathReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libPathResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(libPathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(libPathTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(libPathReset))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(libPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(libPathLabel)
                    .addComponent(libPathReset))
                .addGap(67, 67, 67))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(defaultConfigPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(defaultConfigPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void resetBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtn1ActionPerformed
        devInput1.setText(GStreamerSettings.getDefaultCaptureDevice(1));
        controller.changed();
    }//GEN-LAST:event_resetBtn1ActionPerformed

    private void resetBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtn2ActionPerformed
        devInput2.setText(GStreamerSettings.getDefaultCaptureDevice(2));
        controller.changed();
    }//GEN-LAST:event_resetBtn2ActionPerformed

    private void resetBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtn3ActionPerformed
        devInput3.setText(GStreamerSettings.getDefaultCaptureDevice(3));
        controller.changed();
    }//GEN-LAST:event_resetBtn3ActionPerformed

    private void resetBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtn4ActionPerformed
        devInput4.setText(GStreamerSettings.getDefaultCaptureDevice(4));
        controller.changed();
    }//GEN-LAST:event_resetBtn4ActionPerformed

    private void libPathResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libPathResetActionPerformed
        libPathTextField.setText(GStreamerSettings.getDefaultLibraryPath());
        controller.changed();
    }//GEN-LAST:event_libPathResetActionPerformed

    void load() {
        devInput1.setText(GStreamerSettings.getCaptureDevice(1));
        devInput2.setText(GStreamerSettings.getCaptureDevice(2));
        devInput3.setText(GStreamerSettings.getCaptureDevice(3));
        devInput4.setText(GStreamerSettings.getCaptureDevice(4));
        
        libPathTextField.setText(GStreamerSettings.getLibraryPath());
        
    }

    void store() {
        String text;
        text = devInput1.getText().trim();
        if (text.isEmpty() || text.equals(GStreamerSettings.getDefaultCaptureDevice(1))) {
            GStreamerSettings.resetCaptureDevice(1);
        } else {
            GStreamerSettings.setCaptureDevice(1, text);
        }
        
        text = devInput2.getText().trim();
        if (text.isEmpty() || text.equals(GStreamerSettings.getDefaultCaptureDevice(2))) {
            GStreamerSettings.resetCaptureDevice(2);
        } else {
            GStreamerSettings.setCaptureDevice(2, text);
        }
        
        text = devInput3.getText().trim();
        if (text.isEmpty() || text.equals(GStreamerSettings.getDefaultCaptureDevice(3))) {
            GStreamerSettings.resetCaptureDevice(3);
        } else {
            GStreamerSettings.setCaptureDevice(3, text);
        }
        
        text = devInput4.getText().trim();
        if (text.isEmpty() || text.equals(GStreamerSettings.getDefaultCaptureDevice(4))) {
            GStreamerSettings.resetCaptureDevice(4);
        } else {
            GStreamerSettings.setCaptureDevice(4, text);
        }
        
        text = libPathTextField.getText().trim();
        if (text.equals(GStreamerSettings.getDefaultLibraryPath())) {
            GStreamerSettings.resetLibraryPath();
        } else {
            GStreamerSettings.setLibraryPath(text);
        }
        

    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel defaultConfigPanel;
    private javax.swing.JTextField devInput1;
    private javax.swing.JTextField devInput2;
    private javax.swing.JTextField devInput3;
    private javax.swing.JTextField devInput4;
    private javax.swing.JLabel devLbl1;
    private javax.swing.JLabel devLbl2;
    private javax.swing.JLabel devLbl3;
    private javax.swing.JLabel devLbl4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel libPathLabel;
    private javax.swing.JButton libPathReset;
    private javax.swing.JTextField libPathTextField;
    private javax.swing.JButton resetBtn1;
    private javax.swing.JButton resetBtn2;
    private javax.swing.JButton resetBtn3;
    private javax.swing.JButton resetBtn4;
    // End of variables declaration//GEN-END:variables
}
