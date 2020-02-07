package model;

public class Contact {

    private String contactName;
    private String contactInfo;

    public Contact(String contactName, String contactInfo) {
        this.contactName = contactName;
        this.contactInfo = contactInfo;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactInfo() {
        return this.contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    
}