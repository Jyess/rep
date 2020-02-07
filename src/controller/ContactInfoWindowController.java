package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

import model.ContactsModel;
import view.ContactInfoWindow;

public class ContactInfoWindowController implements MouseListener {

    private JFrame previousFrame;
    private JTextField textFieldName;
    private JList<String> contactList;
    private ContactsModel model;

    public ContactInfoWindowController(JFrame previousFrame, JTextField textField, JList<String> contactList, ContactsModel model) {
        this.previousFrame = previousFrame;
        this.textFieldName = textField;
        this.contactList = contactList;
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        new ContactInfoWindow(this.previousFrame, this.textFieldName, this.contactList, this.model);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
