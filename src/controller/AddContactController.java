package controller;

import javax.swing.JList;

import model.ContactsModel;

public class AddContactController {

    public AddContactController(String contactName, String contactInfo, JList<String> contactList, ContactsModel model) {  
        int index = model.insertAndGenerateList(contactName, contactInfo);

        contactList.setSelectedIndex(index);
        contactList.ensureIndexIsVisible(index); //scroll si hors champ
    }
}
