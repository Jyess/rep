package view;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.DisplayContactInfoController;
import controller.UpdateContactInfoController;
import model.ContactsModel;

public class ContactListWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private ContactsModel model;

	public ContactListWindow() {

		model = new ContactsModel();

		JFrame frame = new JFrame("Liste de contacts");
		frame.setSize(400, 400); // dimension de la fenetre
		frame.setLocationRelativeTo(null); // RelativeTo pour centrer la fenetre
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		// blocs
		JPanel contactsBlock = new JPanel(new BorderLayout());
		JPanel infoBlock = new JPanel(new BorderLayout());
		JPanel btnsBlock = new JPanel();

		// boutons
		JButton btnSave = new JButton("Enregistrer");
		JButton btnAdd = new JButton("Ajouter");
		JButton btnDelete = new JButton("Supprimer");
		btnSave.setEnabled(false);
		btnsBlock.add(btnSave);
		btnsBlock.add(btnAdd);
		btnsBlock.add(btnDelete);

		String[] contactsArray = this.model.contactList(); /****************************************************** */

		JList<String> contactList = new JList<String>(contactsArray);
		contactList.setSelectedIndex(0); // default item selected
		contactList.setVisibleRowCount(4); // nb of row to display without scrolling
		contactsBlock.add(contactList, BorderLayout.CENTER);

		// gère le scroll si height de la liste trop petite
		JScrollPane scrollContact = new JScrollPane(contactList);
		contactsBlock.add(scrollContact);

		// affiche les infos d'un contact
		JTextPane textField = new JTextPane();
		infoBlock.add(textField);

		// display default info
		textField.setText(this.model.findContact(contactList.getSelectedValue())); /****************************************************** */

		// grise ou non le bouton enregistrer si textfield est focus
		textField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				btnSave.setEnabled(true);
			}

			@Override
			public void focusLost(FocusEvent e) {
				btnSave.setEnabled(false);
			}
		});

		// affiche les infos de l'item selected
		DisplayContactInfoController listListener = new DisplayContactInfoController(textField, contactList, model);
		contactList.addListSelectionListener(listListener);

		UpdateContactInfoController infoListener = new UpdateContactInfoController(textField, contactList, model, frame);
		textField.addKeyListener(infoListener);

		btnAdd.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// JFrame addContactNameFrame = new JFrame("Ajout d'un contact");
				// addContactNameFrame.setSize(350, 150);
				// addContactNameFrame.setLocationRelativeTo(null);

				// AddContactNamePanel addContactNamePanel = new AddContactNamePanel();
				// addContactNameFrame.add(addContactNamePanel.container);

				// addContactNameFrame.setResizable(false);
				// addContactNameFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				// addContactNameFrame.setVisible(true);
			}
		});

		btnSave.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// fc.saveContactList();
			}
		});

		// gère le scroll si width de la liste trop petite
		JScrollPane scrollInfo = new JScrollPane(textField);
		infoBlock.add(scrollInfo);

		// ajout de tous les blocs au container
		container.add(contactsBlock);
		container.add(infoBlock);
		container.add(btnsBlock);

		frame.add(container);

		frame.setVisible(true);
	}

	private static void createAndShowGUI() {
		new ContactListWindow();
	}

	public static void main(String[] args) {	
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
		
			@Override
			public void run() {
				createAndShowGUI();
			}

		});
	}
}


