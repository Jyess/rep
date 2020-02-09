package controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextPane;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.ContactsModel;
import view.SaveOrQuitWindow;

public class UpdateContactInfoController extends JFrame implements KeyListener, MouseListener {

    private static final long serialVersionUID = 1L;

    private JTextPane textField;
    private JButton btnSave;
    private JList<String> contactList;
    private ContactsModel model;
    private JFrame frame;
    private WindowAdapter windowListener;
    private boolean infoUpdated = false;

    public UpdateContactInfoController(JTextPane textField, JButton btnSave, JList<String> contactList,
            ContactsModel model, JFrame frame) {
        this.textField = textField;
        this.btnSave = btnSave;
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
    public void keyReleased(KeyEvent e) {
        updateContact();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        onSave();
    }

    private void updateContact() {
        String contactName = this.contactList.getSelectedValue();
        String contactInfo = this.textField.getText();

        this.model.setContact(contactName, contactInfo);

        if (!this.infoUpdated) {
            onClose();
        }

        enableSaveButton(this.infoUpdated);
    }

    private void onClose() {
        this.frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(this.windowListener);
        
        infoUpdated = true;
    }

    private void enableSaveButton(boolean isEnabled) {
        this.btnSave.setEnabled(isEnabled);
    }

    private void onSave() {
        this.model.saveContactsInFile();
        
        this.infoUpdated = false;
        
        enableSaveButton(this.infoUpdated);
        
        this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.frame.removeWindowListener(this.windowListener);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}
}
