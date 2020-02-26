package controller;

import model.ContactsModel;
import view.LanguageListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanguageController implements ActionListener {

    /**
     * La classe qui gère les contacts.
     */
    private ContactsModel model;

    /**
     * Initialise les attributs à réutilisés.
     * 
     * @param model    la classe modèle
     */
    public LanguageController(ContactsModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new LanguageListView(model);
    }
}
