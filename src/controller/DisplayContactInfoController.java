package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.ContactsModel;
import view.ContactListWindow;

public class DisplayContactInfoController implements ListSelectionListener {

    private JTextPane textField;
    private JList<String> contactList;
    private ContactsModel model;

    public DisplayContactInfoController(JTextPane textField, JList<String> contactList, ContactsModel model) {

        this.textField = textField;
        this.contactList = contactList;
        this.model = model;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            String contactName = this.contactList.getSelectedValue();
            this.textField.setText(this.model.findContact(contactName));
        }
    }
}