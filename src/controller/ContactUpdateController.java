package controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.ContactsModel;
import view.AddContactView;
import view.SaveOrQuitView;

public class ContactUpdateController extends JFrame implements ActionListener, DocumentListener {

    private static final long serialVersionUID = 1L;

    private JTextPane textField;
    private JButton btnSave;
    private JMenuItem saveItem;
    private JList<String> contactList;
    private ContactsModel model;
    private JFrame frame;
    private WindowAdapter windowListener;

    public ContactUpdateController(JTextPane textField, JButton btnSave, JMenuItem saveItem, JList<String> contactList, ContactsModel model, JFrame frame) {
        this.textField = textField;
        this.btnSave = btnSave;
        this.saveItem = saveItem;
        this.contactList = contactList;
        this.model = model;
        this.frame = frame;
        this.windowListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // affiche la fenetre seulement quand on quitte la fenetre principale (sinon joptionpane est affecté)
                if (frame.isActive()) {
                    displaySaveOrQuitWindow();
                }
            }
        };
    }

    private void displaySaveOrQuitWindow() {
        new SaveOrQuitView(this.model);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateContact();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateContact();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String source = e.getActionCommand();
        if (source.equals("Enregistrer")) {
            save();
            changeCloseBehavior(false);
        } else if(source.equals("Quitter")) {
            displaySaveOrQuitWindow();
        } else if (source.equals("Ajouter") || source.equals("Ajouter un contact")) {
            new AddContactView(this.model, this.contactList);
            afterInsert();
        } else if (source.equals("Supprimer") || source.equals("Supprimer le contact")) {
            this.model.deleteAndGenerateList(this.contactList.getSelectedValue());
            afterDelete();
        }
    }

    private void updateContact() {
        String contactName = this.contactList.getSelectedValue();
        String contactInfo = this.textField.getText();

        if (contactName.length() > 0 && contactInfo.length() > 0) {
            this.model.setContact(contactName, contactInfo);
        }
        
        if (this.model.updateOccurred()) {
            enableSaveButtons(true);
            changeCloseBehavior(true);
        } else {
            enableSaveButtons(false);
            changeCloseBehavior(false);
        }
    }

    private void changeCloseBehavior(boolean isChanged) {
        if (isChanged) {
            //save ?
            this.frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            this.frame.addWindowListener(this.windowListener);
        } else {
            //close
            this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.frame.removeWindowListener(this.windowListener);
        }
    }

    private void enableSaveButtons(boolean isEnabled) {
        this.btnSave.setEnabled(isEnabled);
        this.saveItem.setEnabled(isEnabled);

        if (isEnabled) {
            KeyStroke saveShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
            saveItem.setAccelerator(saveShortcut);
        } else {
            this.saveItem.setAccelerator(null);
        }
    }

    private void afterInsert() {
        this.textField.setText(model.getContact(this.contactList.getSelectedValue()));
    }

    private void afterDelete() {
        //test si il y a des contacts
        if (true) {
            this.contactList.setSelectedIndex(0);
            this.contactList.ensureIndexIsVisible(0); //scroll si hors champ
            this.textField.setText(model.getContact(this.contactList.getSelectedValue()));
        }
    }

    private void save() {
        this.model.saveContactsInFile();
        enableSaveButtons(false);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {}
}
