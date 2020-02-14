package view;

import javax.swing.JOptionPane;

import model.ContactsModel;

public class LanguageListView extends JOptionPane {

    /**
     * Constante pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Affiche une liste comprenant les langues d'affichage disponibles.
     */
    public LanguageListView(ContactsModel model) {

        String title = model.getVariable("change-language");
        int msgType = PLAIN_MESSAGE;
        Object[] languages = { "Français", "English", "Español", "Deutsch" };
        Object selected = null;

        switch (model.getLanguagePreference()) {
        case "fr":
            selected = languages[0];
            break;

        case "en":
            selected = languages[1];
            break;

        case "es":
            selected = languages[2];
            break;

        case "de":
            selected = languages[3];
            break;
        }

        String response = (String) showInputDialog(null, "", title, msgType, null, languages, selected);

        if (response != null) {
            model.changeLanguage(response);
            new RestartView(model);
        } 
    }
}
