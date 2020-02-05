// package view;

// import java.awt.Component;
// import java.awt.Dimension;
// import java.awt.event.MouseEvent;
// import java.awt.event.MouseAdapter;

// import javax.swing.BoxLayout;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.JTextField;
// import javax.swing.SwingUtilities;

// public class AddContactNameWindow extends JFrame {

//     private static final long serialVersionUID = 1L;
// 	protected JPanel container;

// 	public AddContactNameWindow() {
// 		this.container = new JPanel();
// 		this.container.setLayout(new BoxLayout(this.container,BoxLayout.Y_AXIS));
		
// 		//blocs
// 		JPanel inputBlock = new JPanel();
// 		JPanel btnsBlock = new JPanel();
		
// 		//label input
// 		JLabel label = new JLabel();
// 		label.setText("Entrez le nom souhaité");
// 		inputBlock.add(label);
		
// 		//input
// 		JTextField textField = new JTextField();
// 		textField.setPreferredSize(new Dimension(300,20));
// 		inputBlock.add(textField);
		
// 		//boutons
// 		JButton btnOK = new JButton("OK");
// 		JButton btnCancel = new JButton("Annuler");
// 		btnsBlock.add(btnOK);
// 		btnsBlock.add(btnCancel);
		
// 		//gère bouton annuler
// 		btnCancel.addMouseListener(new MouseAdapter() {
			
// 			@Override
// 			public void mouseClicked(MouseEvent e) {
// 				Component c = (Component) e.getSource();
// 				JFrame frame = (JFrame) SwingUtilities.getRoot(c);
// 				frame.dispose();
// 			}
// 		});
		
// 		btnOK.addMouseListener(new MouseAdapter() {

// 			@Override
// 			public void mouseClicked(MouseEvent e) {
// 				String nameContact = textField.getText();
				
// 				JFrame addContactInfoFrame = new JFrame("Ajout d'un contact");
// 				addContactInfoFrame.setSize(350, 150);
// 				addContactInfoFrame.setLocationRelativeTo(null);

// 				AddContactInfoPanel addContactInfoPanel = new AddContactInfoPanel(nameContact);
// 				addContactInfoFrame.add(addContactInfoPanel.container);

// 				addContactInfoFrame.setResizable(false);
// 				addContactInfoFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
// 				addContactInfoFrame.setVisible(true);

// 				Component c = (Component) e.getSource();
// 				JFrame frame = (JFrame) SwingUtilities.getRoot(c);
// 				frame.setVisible(false);
// 			}
// 		});
		
// 		this.container.add(inputBlock);
// 		this.container.add(btnsBlock);
// 	}
// }
