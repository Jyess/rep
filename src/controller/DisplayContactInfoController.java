package controller;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.ContactsModel;

public class DisplayContactInfoController implements ListSelectionListener {

    /**
     * Le champ contenant les informations d'un contact.
     */
    private JTextPane textField;

    /**
     * La liste de contacts.
     */
    private JList<String> contactList;

    /**
     * La classe qui gère les contacts.
     */
    private ContactsModel model;

    /**
     * Initialise les attributs.
     * @param textField     un champ
     * @param contactList   une liste
     * @param model         la classe modèle
     */
    public DisplayContactInfoController(JTextPane textField, JList<String> contactList, ContactsModel model) {
        this.textField = textField;
        this.contactList = contactList;
        this.model = model;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            String contactName = this.contactList.getSelectedValue();
            this.textField.setText(this.model.getContact(contactName));
        }
    }
}