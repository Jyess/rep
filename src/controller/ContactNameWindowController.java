package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import model.ContactsModel;
import view.ContactNameWindow;

public class ContactNameWindowController implements MouseListener, ActionListener {

    private ContactsModel model;
    private JList<String> contactList;

    public ContactNameWindowController(ContactsModel model, JList<String> contactList) {
        this.model = model;
        this.contactList = contactList;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        new ContactNameWindow(this.model, this.contactList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ContactNameWindow(this.model, this.contactList);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }    
}
