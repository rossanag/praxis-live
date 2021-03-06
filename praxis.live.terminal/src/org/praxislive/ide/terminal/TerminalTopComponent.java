/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.praxislive.ide.terminal;

import java.util.logging.Logger;
import org.praxislive.core.CallArguments;
import org.praxislive.core.services.ScriptService;
import org.praxislive.core.types.PString;
import org.praxislive.ide.core.api.Callback;
import org.praxislive.terminal.Terminal;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
//import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.praxislive.ide.terminal//Terminal//EN",
autostore = false)
public final class TerminalTopComponent extends TopComponent {

    private static TerminalTopComponent instance;
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "TerminalTopComponent";

    private Terminal terminal;

    public TerminalTopComponent() {
        initComponents();
        terminal = new Terminal();
        terminal.setContext(new Context(terminal));
        add(terminal);
        setName(NbBundle.getMessage(TerminalTopComponent.class, "CTL_TerminalTopComponent"));
        setToolTipText(NbBundle.getMessage(TerminalTopComponent.class, "HINT_TerminalTopComponent"));
//        setIcon(ImageUtilities.loadImage(ICON_PATH, true));

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized TerminalTopComponent getDefault() {
        if (instance == null) {
            instance = new TerminalTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the TerminalTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized TerminalTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(TerminalTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof TerminalTopComponent) {
            return (TerminalTopComponent) win;
        }
        Logger.getLogger(TerminalTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        if (instance == null) {
            instance = this;
        }
        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    private static class Context implements Terminal.Context {

        private final Terminal terminal;

        private Context(Terminal terminal) {
            this.terminal = terminal;
        }

        @Override
        public void eval(String script) throws Exception {
            PString scr = PString.valueOf(script);
            TerminalHelper.getDefault().send(
                    ScriptService.class,
                    ScriptService.EVAL,
                    CallArguments.create(scr),
                    new Callback() {

                @Override
                public void onReturn(CallArguments args) {
                    terminal.processResponse(args);
                }

                @Override
                public void onError(CallArguments args) {
                    terminal.processError(args);
                }
            });
        }

        @Override
        public void clear() throws Exception {
            TerminalHelper.getDefault().send(
                    ScriptService.class,
                    ScriptService.CLEAR,
                    CallArguments.EMPTY,
                    null);
        }

    }
}
