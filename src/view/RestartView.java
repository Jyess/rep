package view;

import javax.swing.JOptionPane;

import model.ContactsModel;

public class RestartView extends JOptionPane {

    /**
     * Constante pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Affiche une fenêtre d'avertissement pour un redémarrage.
     */
    public RestartView(ContactsModel model) {

        String title = model.getVariable("restart-title");
        String message = model.getVariable("restart-needed");
        int msgType = WARNING_MESSAGE;

        showMessageDialog(null, message, title, msgType);
	}
}
