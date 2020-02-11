package view;

import javax.swing.JOptionPane;

import controller.SaveOrQuitController;
import model.ContactsModel;

public class SaveOrQuitView extends JOptionPane {

    /**
     * Constante pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Affiche la fenêtre de confirmation de sauvegarde ou non des données.
     * @param model     la classe de gestion des contacts
     */
    public SaveOrQuitView(ContactsModel model) {

        String title = "Modifications non enregistrées";
		String message = "Souhaitez-vous enregistrer vos modifications ?";
		int btnType = YES_NO_CANCEL_OPTION;
		int msgType = WARNING_MESSAGE;
        Object[] options = {"Enregistrer", "Ne pas enregistrer", "Annuler"};

        int response = showOptionDialog(null, message, title, btnType, msgType, null, options, options[0]);

        SaveOrQuitController dialog = new SaveOrQuitController(response, model);
        dialog.action();
    }
}
