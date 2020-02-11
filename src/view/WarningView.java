package view;

import javax.swing.JOptionPane;

public class WarningView extends JOptionPane {

    /**
     * Constante pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Affiche une fenêtre pour avertir l'utilisateur que tous les champs ne sont pas remplis.
     */
    public WarningView() {

        String title = "Action impossible";
        String message = "Tous les champs sont requis.";
        int msgType = WARNING_MESSAGE;

        showMessageDialog(null, message, title, msgType);
	}
}
