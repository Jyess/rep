package view;

import javax.swing.JOptionPane;

import model.ContactsModel;

public class WarningView extends JOptionPane {

    /**
     * Constante pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Affiche une fenêtre pour avertir l'utilisateur que tous les champs ne sont pas remplis.
     */
    public WarningView(ContactsModel model) {

        String title = model.getVariable("warning-title");
        String message = model.getVariable("fields-required");
        int msgType = WARNING_MESSAGE;

        showMessageDialog(null, message, title, msgType);
	}
}
