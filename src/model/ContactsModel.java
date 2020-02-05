package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Set;

public class ContactsModel {
    
    String propertiesLocation = "contacts.properties";
    private Properties contactsProp = new Properties();
    
    public ContactsModel() {
        findAllContacts();
    }

    public void saveContacts() {
        // try-with-resources : permet de fermer les streams
        try (OutputStream out = new FileOutputStream(propertiesLocation)) {
            this.contactsProp.store(out, "Liste des contacts.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void findAllContacts() {
        try (InputStream in = new FileInputStream(propertiesLocation)) {
            this.contactsProp.load(in);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
    }

    public String findContact(String contactName) {
        return this.contactsProp.getProperty(contactName);
    }

    public void createContact(String contactName, String contactInfo) {
        this.contactsProp.setProperty(contactName, contactInfo);
    }

    public String[] contactList() {
        findAllContacts();
        Set<String> everyContactName = this.contactsProp.stringPropertyNames();
		return everyContactName.toArray(new String[everyContactName.size()]);
    }

    public void test() {
        System.out.println(contactsProp);
    }
}