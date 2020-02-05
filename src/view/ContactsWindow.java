package view;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.awt.event.MouseAdapter;
import java.util.Properties;
import java.util.Set;

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

public class ContactsWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel container;
	protected Properties contactsProp;
	String propertiesLocation = "contacts.properties";

	public ContactsWindow() {
		this.contactsProp = new Properties();

		//chargement des données
		try (InputStream in = new FileInputStream(propertiesLocation)) {
			this.contactsProp.load(in);
		} catch(IOException ex) {
			ex.printStackTrace();
		}

		// container
		this.container = new JPanel();
		this.container.setLayout(new BoxLayout(this.container, BoxLayout.Y_AXIS));

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

		// liste des contacts
		Set<String> keys = this.contactsProp.stringPropertyNames();
		String[] contactsArray = keys.toArray(new String[keys.size()]);

		JList<String> listContacts = new JList<String>(contactsArray);
		listContacts.setSelectedIndex(0); // default item selected
		listContacts.setVisibleRowCount(4); // nb of row to display without scrolling
		contactsBlock.add(listContacts, BorderLayout.CENTER);

		// gère le scroll si height de la liste trop petite
		JScrollPane scrollContact = new JScrollPane(listContacts);
		contactsBlock.add(scrollContact);

		// affiche les infos d'un contact
		JTextPane textField = new JTextPane();
		infoBlock.add(textField);

		// display default info
		textField.setText(this.contactsProp.getProperty(listContacts.getSelectedValue()));

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
		listContacts.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					String keyName = listContacts.getSelectedValue();
					textField.setText(contactsProp.getProperty(keyName));
				}
			}
		});

		textField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				String infoUpdated = textField.getText();
				String keyName = listContacts.getSelectedValue();

				contactsProp.setProperty(keyName, infoUpdated);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String infoUpdated = textField.getText();
				String keyName = listContacts.getSelectedValue();

				contactsProp.setProperty(keyName, infoUpdated);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		btnAdd.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame addContactNameFrame = new JFrame("Ajout d'un contact");
				addContactNameFrame.setSize(350, 150);
				addContactNameFrame.setLocationRelativeTo(null);

				AddContactNamePanel addContactNamePanel = new AddContactNamePanel();
				addContactNameFrame.add(addContactNamePanel.container);

				addContactNameFrame.setResizable(false);
				addContactNameFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				addContactNameFrame.setVisible(true);
			}
		});

		btnSave.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// try-with-resources : permet de fermer les streams
				try (OutputStream out = new FileOutputStream(propertiesLocation)) {
					contactsProp.store(out, "Liste des contacts.");
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});

		// gère le scroll si width de la liste trop petite
		JScrollPane scrollInfo = new JScrollPane(textField);
		infoBlock.add(scrollInfo);

		// ajout de tous les blocs au container
		this.container.add(contactsBlock);
		this.container.add(infoBlock);
		this.container.add(btnsBlock);
	}

	public static void createAndShowGUI() {
		// fenetre principale
		JFrame frame = new JFrame("Carnet d'adresses");
		frame.setSize(400, 400); // dimension de la fenetre
		frame.setLocationRelativeTo(null); // RelativeTo pour centrer la fenetre

		AddressBook ab = new AddressBook();
		frame.add(ab.container);

		frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // comportement quand on close la window
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// fenetre de demande de sauvegarde
				JFrame askSaveFrame = new JFrame("Sauvegardé ?");
				askSaveFrame.setSize(400, 150); // dimension de la fenetre
				askSaveFrame.setLocationRelativeTo(null); // RelativeTo pour centrer la fenetre

				AskSavePanel askSavePanel = new AskSavePanel();
				askSaveFrame.add(askSavePanel.container);

				askSaveFrame.setResizable(false);
				askSaveFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // comportement quand on close la window
				askSaveFrame.setVisible(true);
			}
		});
	}
	
	public static void main(String[] args) {	
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            createAndShowGUI();
	        }
	    });
	}
}


