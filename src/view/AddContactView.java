package view;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.ContactsModel;

public class AddContactView extends JOptionPane {

	private static final long serialVersionUID = 1L;

	public AddContactView(ContactsModel model, JList<String> contactList) {

		JLabel nameLabel = new JLabel("Entrez le nom du contact : ");
		JTextField nameInput = new JTextField();
		JLabel infoLabel = new JLabel("Entrez les informations du contact : ");
		JTextField infoInput = new JTextField();

		String title = "Ajout d'un contact";
		Object[] components = { nameLabel, nameInput, infoLabel, infoInput };
		int btnType = OK_CANCEL_OPTION;
		int msgType = QUESTION_MESSAGE;
		Object[] options = { "Ajouter", "Annuler" };

		int response = showOptionDialog(null, components, title, btnType, msgType, null, options, options[0]);
		
		String contactName = nameInput.getText();
		String contactInfo = infoInput.getText();
 
		if (response == OK_OPTION && contactName.length() > 0 && contactInfo.length() > 0) {
			int index = model.insertAndGenerateList(contactName, contactInfo);

			contactList.setSelectedIndex(index);
			contactList.ensureIndexIsVisible(index); //scroll si hors champ
		}
	}
}
