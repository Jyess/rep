package view;

import javax.swing.JOptionPane;

import controller.SaveOrQuitController;
import model.ContactsModel;

public class SaveOrQuitDialog extends JOptionPane {

    private static final long serialVersionUID = 1L;

    public SaveOrQuitDialog(ContactsModel model) {

        Object[] options = {"Enregistrer", "Ne pas enregistrer", "Annuler"};

		int response = showOptionDialog(
            null, 
            "Souhaitez-vous enregistrer vos modifications ?", 
            "Modifications non enregistrées", 
            YES_NO_CANCEL_OPTION, 
            WARNING_MESSAGE, 
            null, 
            options, 
            options[0]
        );

        createDialog("a");

        SaveOrQuitController dialog = new SaveOrQuitController(response, model);
        dialog.action();
    }
}
