package view;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.ContactsModel;

public class ContactNameWindow {

	public ContactNameWindow(ContactsModel model, JList<String> contactList) {

		JLabel nameLabel = new JLabel("Entrez le nom du contact : ");
		JTextField nameInput = new JTextField();
		JLabel infoLabel = new JLabel("Entrez le numéro du contact : ");
		JTextField infoInput = new JFormattedTextField();

		String title = "Ajout d'un contact";
		Object[] components = { nameLabel, nameInput, infoLabel, infoInput };
		int btnType = JOptionPane.OK_CANCEL_OPTION;
		int msgType = JOptionPane.QUESTION_MESSAGE;
		Object[] options = { "Ajouter", "Annuler" };

		JOptionPane.showOptionDialog(null, components, title, btnType, msgType, null, options, options[0]);
		
		String contactName = nameInput.getText();
		String contactInfo = infoInput.getText();
 
		if (contactName.length() > 0 && contactInfo.length() > 0) {
			int index = model.insertAndGenerateList(contactName, contactInfo);

			contactList.setSelectedIndex(index);
			contactList.ensureIndexIsVisible(index); //scroll si hors champ
		}
	}
}
