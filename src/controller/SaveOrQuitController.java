package controller;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.ContactsModel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class SaveOrQuitController extends MouseAdapter {

    private JButton btnSave;
    private JButton btnQuit;
    private JButton btnCancel;
    private JFrame frame;
    private ContactsModel model;

    public SaveOrQuitController(JButton btnSave, JButton btnQuit, JButton btnCancel, JFrame frame, ContactsModel model) {
        this.btnSave = btnSave;
        this.btnQuit = btnQuit;
        this.btnCancel = btnCancel;
        this.frame = frame;
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.btnSave.hasFocus()) {
            this.model.saveContactsInFile();
            System.exit(0);
        } else if (this.btnQuit.hasFocus()) {
            System.exit(0);
        } else if (this.btnCancel.hasFocus()) {
            this.frame.dispose();
        }
    }
}
