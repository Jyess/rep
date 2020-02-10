package controller;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.ContactsModel;

public class DisplayContactInfoController implements ListSelectionListener {

    private JTextPane textField;
    private JMenuItem deleteItem;
    private JList<String> contactList;
    private ContactsModel model;

    public DisplayContactInfoController(JTextPane textField, JMenuItem deleteItem, JList<String> contactList, ContactsModel model) {
        this.textField = textField;
        this.deleteItem = deleteItem;
        this.contactList = contactList;
        this.model = model;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            String contactName = this.contactList.getSelectedValue();
            this.textField.setText(this.model.getContact(contactName));
        }

        // if (e.getLastIndex() == 0) {
        //     KeyStroke deleteShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
        //     deleteItem.setAccelerator(deleteShortcut);
        //     deleteItem.setEnabled(true);
        // } else {
        //     deleteItem.setAccelerator(null);
        //     deleteItem.setEnabled(false);
        // }
    }
}