package controller;

import javax.swing.SwingUtilities;

import model.ContactsModel;
import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveOrQuitController implements ActionListener {

    private ContactsModel model;

    public SaveOrQuitController(ContactsModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String source = e.getActionCommand();

        if (source.equals("Enregistrer")) {
            this.model.saveContactsInFile();
            System.exit(0);
        } else if (source.equals("Ne pas enregistrer")) {
            System.exit(0);
        } else if (source.equals("Annuler")) {
            SwingUtilities.windowForComponent((Component)e.getSource()).dispose();
        }
    }
}
