package view;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.ContactsModel;

public class AddContactView extends JOptionPane {

	/**
     * Constante pour la sérialisation.
     */
	private static final long serialVersionUID = 1L;

	/**
	 * Affiche la fenêtre pour l'ajout d'un contact.
	 * @param model			la classe modèle pour la gestion des contacts
	 * @param contactList	la liste de contacts
	 */
	public AddContactView(ContactsModel model, JList<String> contactList) {

		JLabel nameLabel = new JLabel(model.getVariable("contact-name"));
		JTextField nameInput = new JTextField();
		JLabel infoLabel = new JLabel(model.getVariable("contact-info"));
		JTextField infoInput = new JTextField();

		String title = model.getVariable("add-title");
		Object[] components = { nameLabel, nameInput, infoLabel, infoInput };
		int btnType = OK_CANCEL_OPTION;
		int msgType = QUESTION_MESSAGE;
		Object[] options = { model.getVariable("add"), model.getVariable("cancel") };

		int response = showOptionDialog(null, components, title, btnType, msgType, null, options, options[0]);
		
		String contactName = nameInput.getText();
		String contactInfo = infoInput.getText();
 
		if (response == OK_OPTION && contactName.length() > 0 && contactInfo.length() > 0) {
			int index = model.insertAndGenerateList(contactName, contactInfo);

			contactList.setSelectedIndex(index);
			contactList.ensureIndexIsVisible(index); //scroll si hors champ
		} else if (response == OK_OPTION) {
			new WarningView(model);
			new AddContactView(model, contactList);
		}
	}
}
