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
import view.SaveOrQuitWindow;

public class UpdateContactInfoController extends JFrame implements ActionListener, DocumentListener {

    private static final long serialVersionUID = 1L;

    private JTextPane textField;
    private JButton btnSave;
    private JMenuItem saveItem;
    private JMenuItem exitItem;
    private JList<String> contactList;
    private ContactsModel model;
    private JFrame frame;
    private WindowAdapter windowListener;

    public UpdateContactInfoController(JTextPane textField, JButton btnSave, JMenuItem saveItem, JMenuItem exitItem,
            JList<String> contactList, ContactsModel model, JFrame frame) {
        this.textField = textField;
        this.btnSave = btnSave;
        this.saveItem = saveItem;
        this.exitItem = exitItem;
        this.contactList = contactList;
        this.model = model;
        this.frame = frame;
        this.windowListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                displaySaveOrQuitWindow();
            }
        };
    }

    private void displaySaveOrQuitWindow() {
        new SaveOrQuitWindow(this.model);
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
        if (e.getSource().equals(this.saveItem) || e.getSource().equals(this.btnSave)) {
            save();
            changeCloseBehavior(false);
        } else if(e.getSource().equals(this.exitItem)) {
            displaySaveOrQuitWindow();
        }
    }

    private void updateContact() {
        String contactName = this.contactList.getSelectedValue();
        String contactInfo = this.textField.getText();
        this.model.setContact(contactName, contactInfo);

        enableSaveButtons(true);
        
        changeCloseBehavior(true);
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

    private void save() {
        this.model.saveContactsInFile();
        enableSaveButtons(false);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {}
}
