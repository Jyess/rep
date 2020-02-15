package view;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import controller.DisplayContactInfoController;
import controller.LanguageController;
import controller.ContactUpdateController;
import model.ContactsModel;

public class MainView extends JFrame {

	/**
     * Constante pour la sérialisation.
     */
	private static final long serialVersionUID = 1L;

	/**
	 * La classe qui gère les contacts.
	 */
	private ContactsModel model;

	/**
	 * La liste de contacts.
	 */
	private JList<String> contactList;

	/**
	 * Affiche la fenêtre principale.
	 */
	public MainView() {

		model = new ContactsModel();
		contactList = new JList<String>(model); 

		JFrame frame = new JFrame(model.getVariable("main-title"));
		frame.setSize(400, 400); // dimension de la fenetre
		frame.setLocationRelativeTo(null); // RelativeTo pour centrer la fenetre
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setIconImage(model.getIcon("contact.png").getImage());
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		// blocs
		JPanel menuBarBlock = new JPanel(new BorderLayout());
		JPanel contactsBlock = new JPanel(new BorderLayout());
		JPanel infoBlock = new JPanel(new BorderLayout());
		JPanel btnsBlock = new JPanel();
		
		//menu
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu(model.getVariable("file"));
		fileMenu.setMnemonic(model.getMnemonic("file"));

		JMenuItem saveItem = new JMenuItem(model.getVariable("save"), model.getIcon("save.png"));
		KeyStroke saveShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
		saveItem.setAccelerator(saveShortcut);
		saveItem.setEnabled(false);

		JMenuItem changeLanguage = new JMenuItem(model.getVariable("change-language"), model.getIcon("lang.png"));

		JMenuItem exitItem = new JMenuItem(model.getVariable("exit"), model.getIcon("exit.png"));
		KeyStroke exitShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK);
		exitItem.setAccelerator(exitShortcut);

		fileMenu.add(saveItem);
		fileMenu.add(changeLanguage);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		JMenu contactsMenu = new JMenu(model.getVariable("contacts"));
		contactsMenu.setMnemonic(model.getMnemonic("contacts"));

		JMenuItem addItem = new JMenuItem(model.getVariable("add-contact"), model.getIcon("add.png"));

		JMenuItem deleteItem = new JMenuItem(model.getVariable("delete-contact"), model.getIcon("del.png"));
		KeyStroke deleteShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
		deleteItem.setAccelerator(deleteShortcut);

		contactsMenu.add(addItem);
		contactsMenu.add(deleteItem);

		menuBar.add(fileMenu);
		menuBar.add(contactsMenu);
		menuBarBlock.add(menuBar, BorderLayout.CENTER);
		menuBarBlock.setMaximumSize(new Dimension(menuBarBlock.getMaximumSize().width,20));
		menuBarBlock.setPreferredSize(new Dimension(menuBarBlock.getMaximumSize().width,20));

		// boutons
		JButton btnSave = new JButton(model.getVariable("save"));
		JButton btnAdd = new JButton(model.getVariable("add"));
		JButton btnDelete = new JButton(model.getVariable("delete"));
		btnSave.setEnabled(false);
		btnsBlock.add(btnSave);
		btnsBlock.add(btnAdd);
		btnsBlock.add(btnDelete);

		// JList<String> contactList = new JList<String>(contactsArray);
		contactList.setSelectedIndex(0); // default item selected
		contactList.setVisibleRowCount(4); // nb of row to display without scrolling
		contactsBlock.add(contactList);

		// gère le scroll si height de la liste trop petite
		JScrollPane scrollContact = new JScrollPane(contactList);
		contactsBlock.add(scrollContact);

		JLabel label = new JLabel();
		label.setText(model.getVariable("info"));
		infoBlock.add(label, BorderLayout.NORTH);

		Border border = label.getBorder();
		Border margin = new EmptyBorder(5,5,3,0); //counter clockwise
		label.setBorder(new CompoundBorder(border, margin));

		// affiche les infos d'un contact
		JTextPane textField = new JTextPane();
		infoBlock.add(textField, BorderLayout.SOUTH);

		// gère le scroll si width de la liste trop petite
		JScrollPane scrollInfo = new JScrollPane(textField);
		infoBlock.add(scrollInfo);

		// display default info
		if (contactList.getModel().getSize() > 0) {
			textField.setText(model.getContact(contactList.getSelectedValue()));
		}

		if (contactList.getModel().getSize() == 0) {
			deleteItem.setEnabled(false);
			btnDelete.setEnabled(false);
		}

		// gère les contacts
		ContactUpdateController contactListener = new ContactUpdateController(textField, btnSave, saveItem, btnDelete, deleteItem, contactList, model, frame);
		textField.getDocument().addDocumentListener(contactListener);
		btnSave.addActionListener(contactListener);
		saveItem.addActionListener(contactListener);
		exitItem.addActionListener(contactListener);
		btnAdd.addActionListener(contactListener);
		addItem.addActionListener(contactListener); //obligé action donc tous les boutons action et pas mouse
		deleteItem.addActionListener(contactListener);
		btnDelete.addActionListener(contactListener);

		// gère affichage des données
		DisplayContactInfoController listListener = new DisplayContactInfoController(textField, contactList, model);
		contactList.addListSelectionListener(listListener);	

		// gère les langues
		LanguageController langListener = new LanguageController(model);
		changeLanguage.addActionListener(langListener);

		// ajout de tous les blocs au container
		container.add(menuBarBlock);
		container.add(contactsBlock);
		container.add(infoBlock);
		container.add(btnsBlock);

		frame.add(container);

		frame.setVisible(true);
	}

	/**
	 * Instancie la classe {@link #MainView}.
	 */
	private static void createAndShowGUI() {
		new MainView();
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


