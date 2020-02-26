package model;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

/**
 * Classe modèle pour la gestion des contacts.
 */
public class ContactsModel extends DefaultListModel<String> {
    
    /**
     * Constante pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Chemin du fichier de sauvagarde des contacts.
     */
    private static final String LANGUAGE_PREFERENCE_LOCATION = "lang.properties";

    /**
     * Chemin du fichier de la préférence de la langue.
     */
    private static final String PROPERTIES_LOCATION = "contacts.properties";
    
    /**
     * Les différents choix de langues
     */
    private static final List<String> LANGUAGE_CHOICE = new ArrayList<String>(Arrays.asList("fr", "en", "es", "de"));

    /**
     * Liste des contacts.
     */
    private Properties contactsProp;

    /**
     * Préférence de la langue.
     */
    private Properties languagePreference;

    /**
     * Langue d'affichage.
     */
    private Properties language;
    
    /**
     * Initialise une liste de propriétés, charge les données existantes et génère une liste de contacts.
     */
    public ContactsModel() {
        this.contactsProp = new Properties();
        this.languagePreference = new Properties();
        this.language = new Properties();

        createFiles();
        loadContactsFromFile();
        generateContactList();
        loadLanguagePreferenceFile();
        loadLanguageFile();
    }

    /**
     * Crée et remplit les fichiers de base pour le fonctionnement de l'application (contacts et préférence de la langue)
     */
    private void createFiles() {
        File contacts = new File(PROPERTIES_LOCATION);
        File lang = new File(LANGUAGE_PREFERENCE_LOCATION);
        
        try {
            if (contacts.createNewFile()) {
                saveContactsInFile();
            }

            if (lang.createNewFile()) {
                this.languagePreference.setProperty("lang", "fr");
                saveLanguagePreferenceInFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enregistre une liste des contacts dans un fichier.
     */
    public void saveContactsInFile() {
        // try-with-resources : permet de fermer les streams
        try (OutputStream out = new FileOutputStream(PROPERTIES_LOCATION)) {
            this.contactsProp.store(out, "Liste des contacts.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Charge une liste des contacts à partir d'un fichier.
     */
    private void loadContactsFromFile() {
        try (InputStream in = new FileInputStream(PROPERTIES_LOCATION)) {
            Reader reader = new InputStreamReader(in, "UTF-8"); //encodage
            this.contactsProp.load(reader);
		} catch(IOException ex) {
			ex.printStackTrace();
        }
    }

    /**
     * Charge la préférence de la langue à partir d'un fichier.
     */
    private void loadLanguagePreferenceFile() {
        try (InputStream in = new FileInputStream(LANGUAGE_PREFERENCE_LOCATION)) {
            this.languagePreference.load(in);
		} catch(IOException ex) {
			ex.printStackTrace();
        }
    }

    /**
     * Enregistre la préférence de la langue dans un fichier.
     */
    private void saveLanguagePreferenceInFile() {
        try (OutputStream out = new FileOutputStream(LANGUAGE_PREFERENCE_LOCATION)) {
            this.languagePreference.store(out, "Langue choisie par défaut.");
		} catch(IOException ex) {
			ex.printStackTrace();
        }
    }

    /**
     * Charge le fichier langue correspondant.
     */
    private void loadLanguageFile() {
        String lang = this.languagePreference.getProperty("lang");

        //si le fichier de préférence est erroné et qu'une valeur n'est pas valide
        if (lang == null || !LANGUAGE_CHOICE.contains(lang) || this.languagePreference.size() > 1) {
        	this.languagePreference = new Properties();
        	this.languagePreference.setProperty("lang", "fr");
            saveLanguagePreferenceInFile();
        }

        lang = this.languagePreference.getProperty("lang");

        try (InputStream in = getClass().getClassLoader().getResourceAsStream("lang/" + lang + ".properties")) {
            Reader reader = new InputStreamReader(in, "UTF-8"); //encodage
            this.language.load(reader);
		} catch(IOException e1) {
            try (InputStream in = getClass().getClassLoader().getResourceAsStream("lang/fr.properties")) {
                Reader reader = new InputStreamReader(in, "UTF-8"); //encodage
                this.language.load(reader);
            } catch(IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Retourne les informations d'un contact.
     * @param contactName   le nom du contact
     * @return              les informations du contact
     */
    public String getContact(String contactName) {
        return this.contactsProp.getProperty(contactName);
    }

    /**
     * Définit un nouveau contact.
     * @param contactName   le nom du contact
     * @param contactInfo   les informations du contact
     */
    public void setContact(String contactName, String contactInfo) {
        this.contactsProp.setProperty(contactName, contactInfo);
    }

    /**
     * Supprime un contact.
     * @param contactName   le nom du contact
     */
    public void unsetContact(String contactName) {
        this.contactsProp.remove(contactName);
    }

    /**
     * Retourne le nom de chaque contact.
     * @return  un tableau contenant le nom de chaque contact
     */
    private Set<String> getProperties() {
        return this.contactsProp.stringPropertyNames();
    }

    /**
     * Génère une liste de contacts.
     * @return  une liste de contacts triée
     */
    private List<String> generateContactList() {
        clear(); //efface la liste 

        Set<String> contactSet = getProperties(); //prend les properties
        List<String> sortedContactList = sortContactList(contactSet); //crée une liste triée

        Iterator<String> iterator = sortedContactList.iterator();
        while (iterator.hasNext()) {
            addElement(iterator.next()); //ajoute les contacts dans l'ordre ASC
        }

        return sortedContactList;
    }

    /**
     * Trie une liste de contact dans l'order alphabétique
     * @param contactSet    une liste de contacts
     * @return              une liste de contacts triée
     */
    private List<String> sortContactList(Set<String> contactSet) {
        List<String> contactList = contactSet.stream().collect(Collectors.toList());
        Collections.sort(contactList, new SortIgnoringCase());
        return contactList;
    }

    /**
     * Permet de redéfinir la méthode {@link #compare()}
     */
    private class SortIgnoringCase implements Comparator<Object> {

        /**
         * Compare deux objets {@code String} sans prendre en compte les majuscules.
         * @param o1    un objet {@code String}
         * @param o2    un objet {@code String}
         * @return      une valeur {@code 0} si les deux objets sont égaux;
         *              une valeur strictement inférieure à {@code 0} si l'objet est plus petit que celui en paramètre;
         *              une valeur strictement supérieure à {@code 0} si l'objet est plus grand que celui en paramètre.
         */
        @Override
        public int compare(Object o1, Object o2) {
            String s1 = (String) o1;
            String s2 = (String) o2;
            return s1.toLowerCase().compareTo(s2.toLowerCase());
        }

    }

    /**
     * Ajoute un contact et regénère une liste.
     * @param contactName   le nom du contact
     * @param contactInfo   les informations du contact
     * @return              un index correspondant à la position du contact dans la liste de contact
     */
    public int insertAndGenerateList(String contactName, String contactInfo) {
        //vérifie que les deux champs soient remplis et que le contact soit unique
        if (contactName.length() > 0 && contactInfo.length() > 0 && getContact(contactName) == null) {
            setContact(contactName, contactInfo); //ajoute au properties
            List<String> list = generateContactList(); //génère la nouvelle liste triée
            return list.indexOf(contactName);
        } else if (contactName.length() == 0 || contactInfo.length() == 0) {
            return -1;
        } else if (getContact(contactName) != null) {
            return -2;
        } else {
            return -3;   
        }
    }

    /**
     * Supprime un contact et regénère une liste.
     * @param contactName   le nom du contact
     */
    public void deleteAndGenerateList(String contactName) {
        unsetContact(contactName);
        generateContactList(); //génère la nouvelle liste triée
    }

    /**
     * Retourne {@code true} si et seulement si il existe une différence entre la liste de contacts sauvegardée 
     * et non sauvegardée (c'est-à-dire entre le contenu du fichier existant et les propriétés non sauvegardée dans un fichier).
     * @return  {@code true} si une mise à jour a eu lieu dans la liste de contacts,
     *          {@code false} sinon
     */
    public boolean updateOccurred() {
        Properties contactSaved = new Properties();

        try (InputStream in = new FileInputStream(PROPERTIES_LOCATION)) {
            contactSaved.load(in);
		} catch(IOException ex) {
			ex.printStackTrace();
        }

        Properties contactNotSaved = this.contactsProp;

        HashMap<String,String> pairSaved = new HashMap<String,String>();
        HashMap<String,String> pairNotSaved = new HashMap<String,String>();

        for (String key : contactSaved.stringPropertyNames()) {
            pairSaved.put(key, contactSaved.getProperty(key));
        }

        for (String key : contactNotSaved.stringPropertyNames()) {
            pairNotSaved.put(key, contactNotSaved.getProperty(key));
        }

        // System.out.println(pairNotSaved);
        // System.out.println(pairSaved);

        return !pairSaved.equals(pairNotSaved);
    }

    /**
     * Retourne la valeur de la variable correspondant aux textes de l'application dans la langue correspondate.
     * @param variable  le nom de la variable à afficher
     * @return          la valeur de la variable
     */
    public String getVariable(String variable) {
        return this.language.getProperty(variable);        
    }

    /**
     * Retourne le code de la langue de préférence.
     * @return     code de la langue de préférence
     */
    public String getLanguagePreference() {
        return this.languagePreference.getProperty("lang");        
    }

    /**
     * Définit la langue de préférence.
     * @return     code de la langue de préférence
     */
    private void setLanguagePreference(String value) {
        this.languagePreference.setProperty("lang", value);        
    }

    /**
     * Retourne la première lettre correspondant à un mot de la barre de menu pour gérer les raccourcis clavier.
     * @param $key  le nom du menu
     * @return      la valeur du raccourci clavier
     */
    public int getMnemonic(String key) {
        String value = this.language.getProperty(key);
        char firstLetter = value.charAt(0);

        return KeyEvent.getExtendedKeyCodeForChar(firstLetter);
    }

    /**
     * Change la langue en modifiant la valeur de la langue de préférence et en l'enregistrant dans un fichier.
     * @param response  code la langue de préférence
     */
	public void changeLanguage(String response) {
        switch (response) {
            case "Français":
                setLanguagePreference("fr");
                break;
            
            case "English":
                setLanguagePreference("en");
                break;

            case "Español":
                setLanguagePreference("es");
                break;
            
            case "Deutsch":
                setLanguagePreference("de");
                break;
        }

        saveLanguagePreferenceInFile();
    }
    
    /**
     * Retourne un objet de type ImageIcon.
     * @param filename      nom du fichier icone
     * @return              objet ImageIcon
     */
    public ImageIcon getIcon(String filename) {
        return new ImageIcon(getClass().getResource("/media/" + filename));
    }
}