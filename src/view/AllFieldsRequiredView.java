package view;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.ContactsModel;

public class AllFieldsRequiredView extends JOptionPane {

    /**
     * Constante pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Affiche une fenêtre pour avertir l'utilisateur que tous les champs ne sont pas remplis.
     */
    public AllFieldsRequiredView(ContactsModel model) {

        String title = model.getVariable("fields-title");
        String message = model.getVariable("fields-required");
        int msgType = WARNING_MESSAGE;
        ImageIcon icon = model.getIcon("warning.png");

        showMessageDialog(null, message, title, msgType, icon);
	}
}
