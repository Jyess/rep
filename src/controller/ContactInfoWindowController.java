package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

import model.ContactsModel;
import view.ContactInfoWindow;

public class ContactInfoWindowController implements MouseListener {

    private JDialog dialog;
    private JTextField textFieldName;
    private JList<String> contactList;
    private ContactsModel model;

    public ContactInfoWindowController(JDialog dialog, JTextField textField, JList<String> contactList, ContactsModel model) {
        this.dialog = dialog;
        this.textFieldName = textField;
        this.contactList = contactList;
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.dialog.setVisible(false);
        new ContactInfoWindow(this.dialog, this.textFieldName, this.contactList, this.model);
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
