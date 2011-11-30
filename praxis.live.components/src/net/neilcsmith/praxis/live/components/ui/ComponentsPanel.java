/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.neilcsmith.praxis.live.components.ui;

import net.neilcsmith.praxis.live.components.ComponentSettings;

final class ComponentsPanel extends javax.swing.JPanel {

    private final ComponentsOptionsPanelController controller;

    ComponentsPanel(ComponentsOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        defaultConfigPanel = new javax.swing.JPanel();
        defaultConfigDescription = new javax.swing.JLabel();
        showTestCheckBox = new javax.swing.JCheckBox();

        defaultConfigPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ComponentsPanel.class, "ComponentsPanel.defaultConfigPanel.border.title"))); // NOI18N

        defaultConfigDescription.setForeground(javax.swing.UIManager.getDefaults().getColor("textInactiveText"));
        defaultConfigDescription.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        org.openide.awt.Mnemonics.setLocalizedText(defaultConfigDescription, org.openide.util.NbBundle.getMessage(ComponentsPanel.class, "ComponentsPanel.defaultConfigDescription.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(showTestCheckBox, org.openide.util.NbBundle.getMessage(ComponentsPanel.class, "ComponentsPanel.showTestCheckBox.text")); // NOI18N

        javax.swing.GroupLayout defaultConfigPanelLayout = new javax.swing.GroupLayout(defaultConfigPanel);
        defaultConfigPanel.setLayout(defaultConfigPanelLayout);
        defaultConfigPanelLayout.setHorizontalGroup(
            defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(defaultConfigPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(defaultConfigPanelLayout.createSequentialGroup()
                        .addComponent(showTestCheckBox)
                        .addContainerGap())
                    .addGroup(defaultConfigPanelLayout.createSequentialGroup()
                        .addComponent(defaultConfigDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                        .addGap(36, 36, 36))))
        );
        defaultConfigPanelLayout.setVerticalGroup(
            defaultConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(defaultConfigPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(showTestCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(defaultConfigDescription)
                .addContainerGap(321, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 639, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(defaultConfigPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(defaultConfigPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    void load() {
        showTestCheckBox.setSelected(ComponentSettings.getShowTestComponents());
    }

    void store() {
        ComponentSettings.setShowTestComponents(showTestCheckBox.isSelected());
    }

    boolean valid() {
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel defaultConfigDescription;
    private javax.swing.JPanel defaultConfigPanel;
    private javax.swing.JCheckBox showTestCheckBox;
    // End of variables declaration//GEN-END:variables
}