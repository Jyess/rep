package controller;

import model.ContactsModel;

public class SaveOrQuitController {

    /**
     * Réponse de la fenêtre de confirmation.
     */
    private int response;

    /**
     * La classe qui gère les contacts.
     */
    private ContactsModel model;

    /**
     * Initialise les attributs à réutilisés.
     * @param response  la réponse de ka fenêtre de confirmation
     * @param model     la classe modèle
     */
    public SaveOrQuitController(int response, ContactsModel model) {
        this.response = response;
        this.model = model;
    }

    /**
     * Exécute l'action dédiée à la réponse reçue.
     */
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
