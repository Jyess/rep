package controller;

import model.ContactsModel;

public class SaveOrQuitController {

    private int response;
    private ContactsModel model;

    public SaveOrQuitController(int response, ContactsModel model) {
        this.response = response;
        this.model = model;
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
        }
    }
}
