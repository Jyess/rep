package view;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import controller.ContactNameWindowController;
import controller.DisplayContactInfoController;
import controller.UpdateContactInfoController;
import model.ContactsModel;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private ContactsModel model;
	public JList<String> contactList;

	public MainWindow() {

		model = new ContactsModel();

		JFrame frame = new JFrame("Liste de contacts");
		frame.setSize(400, 400); // dimension de la fenetre
		frame.setLocationRelativeTo(null); // RelativeTo pour centrer la fenetre
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		// blocs
		JPanel menuBarBlock = new JPanel(new BorderLayout());
		JPanel contactsBlock = new JPanel(new BorderLayout());
		JPanel infoBlock = new JPanel(new BorderLayout());
		JPanel btnsBlock = new JPanel();
		
		//menu
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("Fichier");
		JMenu contactsMenu = new JMenu("Contacts");

		JMenuItem saveItem = new JMenuItem("Enregister");
		KeyStroke saveShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
		saveItem.setAccelerator(saveShortcut);
		saveItem.setMnemonic(KeyEvent.VK_E);

		JMenuItem exitItem = new JMenuItem("Fermer");
		KeyStroke exitShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK);
		exitItem.setAccelerator(exitShortcut);
		exitItem.setMnemonic(KeyEvent.VK_F);

		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		menuBar.add(fileMenu);
		menuBar.add(contactsMenu);
		menuBarBlock.add(menuBar, BorderLayout.NORTH);

		// boutons
		JButton btnSave = new JButton("Enregistrer");
		JButton btnAdd = new JButton("Ajouter");
		JButton btnDelete = new JButton("Supprimer");
		btnSave.setEnabled(false);
		btnsBlock.add(btnSave);
		btnsBlock.add(btnAdd);
		btnsBlock.add(btnDelete);

		contactList = new JList<String>(model); 

		// JList<String> contactList = new JList<String>(contactsArray);
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
		textField.setText(model.getContact(contactList.getSelectedValue()));

		// met à jour les properties 
		UpdateContactInfoController infoListener = new UpdateContactInfoController(textField, btnSave, contactList, model, frame);
		textField.addKeyListener(infoListener);
		btnSave.addMouseListener(infoListener);

		// affiche les infos de l'item selected
		DisplayContactInfoController listListener = new DisplayContactInfoController(textField, contactList, model);
		contactList.addListSelectionListener(listListener);

		ContactNameWindowController addListener = new ContactNameWindowController(model, contactList);
		btnAdd.addMouseListener(addListener);

		// gère le scroll si width de la liste trop petite
		JScrollPane scrollInfo = new JScrollPane(textField);
		infoBlock.add(scrollInfo);

		// ajout de tous les blocs au container
		container.add(menuBarBlock);
		container.add(contactsBlock);
		container.add(infoBlock);
		container.add(btnsBlock);

		frame.add(container);

		frame.setVisible(true);
	}

	private static void createAndShowGUI() {
		new MainWindow();
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


