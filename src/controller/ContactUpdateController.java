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

    /**
     * Constante pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Le champ contenant les informations d'un contact.
     */
    private JTextPane textField;

    /**
     * Le bouton de sauvegarde.
     */
    private JButton btnSave;

    /**
     * L'item de sauvegarde dans le menu.
     */
    private JMenuItem saveItem;

    /**
     * Le bouton de suppression.
     */
    private JButton btnDelete;

    /**
     * L'item de suppression dans le menu.
     */
    private JMenuItem deleteItem;

    /**
     * La liste de contact.
     */
    private JList<String> contactList;

    /**
     * La classe qui gère les contacts.
     */
    private ContactsModel model;

    /**
     * La fenêtre principale.
     */
    private JFrame frame;

    /**
     * L'écouteur de la fenêtre.
     */
    private WindowAdapter windowListener;

    /**
     * Initialise les attributs à réutilisés et crée le type d'écouteur.
     * @param textField     un champ
     * @param btnSave       un bouton
     * @param saveItem      un item de menu
     * @param btnDelete     un bouton
     * @param deleteItem    un item de menu
     * @param contactList   une liste
     * @param model         la classe modèle
     * @param frame         une fenêtre
     */
    public ContactUpdateController(JTextPane textField, JButton btnSave, JMenuItem saveItem, JButton btnDelete, JMenuItem deleteItem, JList<String> contactList, ContactsModel model, JFrame frame) {
        this.textField = textField;
        this.btnSave = btnSave;
        this.saveItem = saveItem;
        this.btnDelete = btnDelete;
        this.deleteItem = deleteItem;
        this.contactList = contactList;
        this.model = model;
        this.frame = frame;
        this.windowListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // System.out.println(EventQueue.getCurrentEvent()); ?????
                // affiche la fenetre seulement quand on quitte la fenetre principale (sinon joptionpane est affecté)
                if (frame.isActive()) {
                    displaySaveOrQuitWindow();
                }
            }
        };
    }

    /**
     * Affiche la fenêtre demandant d'enregistrer ou non les modifications.
     */
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

    /**
     * Met à jour l'affichage d'un contact et redéfinit ses informations si besoin.
     */
    private void updateContact() {
        if (this.contactList.getModel().getSize() > 0) {
            String contactName = this.contactList.getSelectedValue();
            String contactInfo = this.textField.getText();

            this.model.setContact(contactName, contactInfo);
        }    
        
        if (this.model.updateOccurred()) {
            toggleSaveButtons(true);
            changeCloseBehavior(true);
        } else {
            toggleSaveButtons(false);
            changeCloseBehavior(false);
        }
    }

    /**
     * Modifie le comportement de la fenêtre principale. 
     * @param isChanged     {@code true} pour ajouter la fenêtre de confirmation à la fermeture;
     *                      {@code false} pour remettre le comportement de base à la fermeture et enlève l'écouteur
     */
    private void changeCloseBehavior(boolean isChanged) {
        if (isChanged) {
            //save ?
            this.frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            this.frame.addWindowListener(this.windowListener);
        } else {
            //close
            this.frame.removeWindowListener(this.windowListener);
            this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }

    /**
     * Active ou désactive le bouton et l'item de sauvegarde. Si {@code true} alors on active les éléments et
     * on ajoute un raccourci clavier sur l'item de sauvegarde (CTRL+S);
     * si {@code false} on désactive les éléments et on enlève le raccourci existant.
     * @param isEnabled     {@code true} pour que les éléments soient activés ainsi que le raccourci;
     *                      {@code false} pour que les éléments soient désactivés ainsi que le raccourci
     */
    private void toggleSaveButtons(boolean isEnabled) {
        this.btnSave.setEnabled(isEnabled);
        this.saveItem.setEnabled(isEnabled);

        if (isEnabled) {
            KeyStroke saveShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
            saveItem.setAccelerator(saveShortcut);
        } else {
            this.saveItem.setAccelerator(null);
        }
    }

    /**
     * Active ou désactive le bouton et l'item de suppression. Si {@code true} alors on active les éléments et
     * on ajoute un raccourci clavier sur l'item de suppression (SUPPR);
     * si {@code false} on désactive les éléments et on enlève le raccourci existant.
     * @param isEnabled     {@code true} pour que les éléments soient activés ainsi que le raccourci;
     *                      {@code false} pour que les éléments soient désactivés ainsi que le raccourci
     */
    private void toggleDeleteButtons(boolean isEnabled) {
        this.btnDelete.setEnabled(isEnabled);
        this.deleteItem.setEnabled(isEnabled);

        if (isEnabled) {
            KeyStroke deleteShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
            deleteItem.setAccelerator(deleteShortcut);
        } else {
            this.deleteItem.setAccelerator(null);
            this.textField.setText("");
        }
    }

    /**
     * Affiche les informations du contact après son ajout et exécute {@link #toggleDeleteButtons()}
     */
    private void afterInsert() {
        if (this.contactList.getModel().getSize() > 0) {
            this.textField.setText(model.getContact(this.contactList.getSelectedValue()));
            toggleDeleteButtons(true);
        }
    }

    /**
     * Affiche les informations du premier contact (ou du nouveau premier contact dans le cas où il serait supprimé)
     * après la suppresion. S'il n'existe plus de contact, la méthode {@link #toggleDeleteButtons()} est exécutée.
     */
    private void afterDelete() {
        //test si il y a des contacts
        if (contactList.getModel().getSize() > 0) {
            this.contactList.setSelectedIndex(0);
            this.contactList.ensureIndexIsVisible(0); //scroll si hors champ
            this.textField.setText(model.getContact(this.contactList.getSelectedValue()));
        } else {
            toggleDeleteButtons(false);
        }
    }

    /**
     * Sauvegarde la liste de contacts et exécute la méthode {@link #toggleSaveButtons()}.
     */
    private void save() {
        this.model.saveContactsInFile();
        toggleSaveButtons(false);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {}
}
