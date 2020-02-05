package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ContactsModel {
    
    String propertiesLocation = "contacts.properties";
    private Properties contactsProp;
    
    public ContactsModel() {
        this.contactsProp = new Properties();
    }

    public void saveContacts() {
        // try-with-resources : permet de fermer les streams
        try (OutputStream out = new FileOutputStream(propertiesLocation)) {
            this.contactsProp.store(out, "Liste des contacts.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadContacts() {
        try (InputStream in = new FileInputStream(propertiesLocation)) {
			this.contactsProp.load(in);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
    }
}