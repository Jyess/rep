package view;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.ContactsModel;

public class NotUniqueView extends JOptionPane {

    /**
     * Constante pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Affiche une fenêtre pour avertir l'utilisateur le contact existe déjà.
     */
    public NotUniqueView(ContactsModel model) {

        String title = model.getVariable("duplicate-title");
        String message = model.getVariable("not-unique");
        int msgType = WARNING_MESSAGE;
        ImageIcon icon = model.getIcon("warning.png");

        showMessageDialog(null, message, title, msgType, icon);
	}
}
