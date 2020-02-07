package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;

public class ContactsModel extends DefaultListModel<String> {
    
    private static final long serialVersionUID = 1L;
    private static final String PROPERTIES_LOCATION = "contacts.properties";

    private Properties contactsProp;
    
    public ContactsModel() {
        this.contactsProp = new Properties();
        loadContactsFromFile();
        generateContactList();
    }

    public void saveContactsInFile() {
        // try-with-resources : permet de fermer les streams
        try (OutputStream out = new FileOutputStream(PROPERTIES_LOCATION)) {
            this.contactsProp.store(out, "Liste des contacts.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadContactsFromFile() {
        try (InputStream in = new FileInputStream(PROPERTIES_LOCATION)) {
            this.contactsProp.load(in);
		} catch(IOException ex) {
			ex.printStackTrace();
        }
    }

    public String getContact(String contactName) {
        return this.contactsProp.getProperty(contactName);
    }

    public void setContact(String contactName, String contactInfo) {
        this.contactsProp.setProperty(contactName, contactInfo);
    }

    private Set<String> getProperties() {
        return this.contactsProp.stringPropertyNames();
    }

    private void generateContactList() {
        clear(); //efface la liste 

        Set<String> contactSet = getProperties(); //prend les properties
        List<String> sortedContactList = sortContactList(contactSet); //crée une liste triée

        Iterator<String> iterator = sortedContactList.iterator();
        while (iterator.hasNext()) {
            addElement(iterator.next()); //ajoute les contacts dans l'ordre ASC
        }
    }

    private List<String> sortContactList(Set<String> contactSet) {
        List<String> contactList = contactSet.stream().collect(Collectors.toList());
        Collections.sort(contactList, new SortIgnoringCase());
        return contactList;
    }

    private class SortIgnoringCase implements Comparator<Object> {

        @Override
        public int compare(Object o1, Object o2) {
            String s1 = (String) o1;
            String s2 = (String) o2;
            return s1.toLowerCase().compareTo(s2.toLowerCase());
        }

    }

    public void insertAndGenerateList(String contactName, String contactInfo) {
        setContact(contactName, contactInfo); //ajoute au properties
        generateContactList(); //génère la nouvelle liste triée
    }
}