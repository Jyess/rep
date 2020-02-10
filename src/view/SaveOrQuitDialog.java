package view;

import javax.swing.JOptionPane;

import controller.SaveOrQuitController;
import model.ContactsModel;

public class SaveOrQuitDialog {

    public SaveOrQuitDialog(ContactsModel model) {

        Object[] options = {"Enregistrer", "Ne pas enregistrer", "Annuler"};

		int response = JOptionPane.showOptionDialog(
            null, 
            "Souhaitez-vous enregistrer vos modifications ?", 
            "Modifications non enregistrées", 
            JOptionPane.YES_NO_CANCEL_OPTION, 
            JOptionPane.WARNING_MESSAGE, 
            null, 
            options, 
            options[0]
        );

        SaveOrQuitController dialog = new SaveOrQuitController(model, response);
        dialog.action();
    }
}
