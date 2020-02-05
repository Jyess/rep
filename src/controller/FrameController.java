package controller;

import model.ContactsModel;

public class FrameController {

    private ContactsModel cm;

    public FrameController() {
        this.cm = new ContactsModel();
    }

    public String[] getContactList() {
        return cm.contactList();
    }

    public String getContact(String contactName) {
        return cm.findContact(contactName);
    }

    public void setContact(String contactName, String contactInfo) {
        cm.createContact(contactName, contactInfo);
    }

    public void saveContactList() {
        cm.saveContacts();
    }

    public void test() {
        cm.test();
    }
}