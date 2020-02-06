package view;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
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

import controller.FrameController;

public class ContactListWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public ContactListWindow() {

		FrameController fc = new FrameController();

		setTitle("Liste de contacts");
		setSize(400, 400); // dimension de la fenetre
		setLocationRelativeTo(null); // RelativeTo pour centrer la fenetre
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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

		String[] contactsArray = fc.getContactList();

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
		textField.setText(fc.getContact(contactList.getSelectedValue()));

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
		contactList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					String contactName = contactList.getSelectedValue();
					textField.setText(fc.getContact(contactName));
				}
			}
		});

		textField.getDocument().addDocumentListener(new DocumentListener() {

			private void updateContact() {
				String contactName = contactList.getSelectedValue();
				String contactInfo = textField.getText();

				fc.setContact(contactName, contactInfo);
				
				setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				addWindowListener(new WindowAdapter() {
					
					@Override
					public void windowClosing(WindowEvent e) {
						new SaveOrQuitWindow();
					}
				});
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateContact();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateContact();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateContact();
			}
		});

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
				fc.saveContactList();
			}
		});

		// gère le scroll si width de la liste trop petite
		JScrollPane scrollInfo = new JScrollPane(textField);
		infoBlock.add(scrollInfo);

		// ajout de tous les blocs au container
		container.add(contactsBlock);
		container.add(infoBlock);
		container.add(btnsBlock);

		add(container);

		setVisible(true);
	}

	public static void main(String[] args) {	
		new ContactListWindow();
	}
}


