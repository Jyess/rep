package controller;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextPane;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.ContactsModel;

public class UpdateContactInfoController extends JFrame implements KeyListener {

    private static final long serialVersionUID = 1L;

    private JTextPane textField;
    private JList<String> contactList;
    private ContactsModel model;
    private JFrame frame;

    public UpdateContactInfoController(JTextPane textField, JList<String> contactList, ContactsModel model, JFrame frame) {
        this.textField = textField;
        this.contactList = contactList;
        this.model = model;
        this.frame = frame;
        whenClosing();
    }

    private void updateContact() {
        String contactName = this.contactList.getSelectedValue();
        String contactInfo = this.textField.getText();
        
        this.model.setContact(contactName, contactInfo);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        updateContact();
    }

    private void whenClosing() {
        this.frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("closing");
                // TO DO
            }
        });
    }
    
}
