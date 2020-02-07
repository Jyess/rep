package controller;

import java.util.Arrays;
import java.util.List;

import javax.swing.JList;
import javax.swing.ListModel;

import model.ContactsModel;

public class AddContactController {

    public AddContactController(String contactName, String contactInfo, JList<String> contactList, ContactsModel model) {  
        model.insertAndGenerateList(contactName, contactInfo);

        // contactList.setSelectedIndex(index);
        // contactList.ensureIndexIsVisible(index);
    }
}
