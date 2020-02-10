package controller;

import model.ContactsModel;

public class SaveOrQuitController {

    private ContactsModel model;
    private int response;

    public SaveOrQuitController(ContactsModel model, int response) {
        this.model = model;
        this.response = response;
    }

    public void action() {
        switch (this.response) {
            case 0:
                this.model.saveContactsInFile();
                System.exit(0);
                break;

            case 1:
                System.exit(0);
                break;
        
            default:
                break;
        }
    }
}
