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

// public class AddContactInfoWindow extends JFrame {

//     private static final long serialVersionUID = 1L;
//     protected JPanel container;

//     public AddContactInfoWindow(String nameContact) {
// 		this.container = new JPanel();
// 		this.container.setLayout(new BoxLayout(this.container,BoxLayout.Y_AXIS));
		
// 		//blocs
// 		JPanel inputBlock = new JPanel();
// 		JPanel btnsBlock = new JPanel();
		
// 		//label input
// 		JLabel label = new JLabel();
// 		label.setText("Entrez les informations du contact");
// 		inputBlock.add(label);
		
// 		//input
// 		JTextField textField = new JTextField();
// 		textField.setPreferredSize(new Dimension(300,20));
// 		inputBlock.add(textField);
		
// 		//boutons
// 		JButton btnOK = new JButton("OK");
// 		JButton btnBack = new JButton("Retour");
// 		btnsBlock.add(btnOK);
// 		btnsBlock.add(btnBack);
		
// 		//gère bouton retour
// 		btnBack.addMouseListener(new MouseAdapter() {
			
// 			@Override
// 			public void mouseClicked(MouseEvent e) {
//                 Component c = (Component) e.getSource();
// 				JFrame frame = (JFrame) SwingUtilities.getRoot(c);
//                 frame.dispose();
// 			}
// 		});
		
// 		btnOK.addMouseListener(new MouseAdapter() {

// 			@Override
// 			public void mouseClicked(MouseEvent e) {
//                 String infoContact = textField.getText();
                
//                 contactsProp.setProperty(nameContact, infoContact);

//                 // SwingUtilities.updateComponentTreeUI(frame);

//                 Component c = (Component) e.getSource();
// 				JFrame frame = (JFrame) SwingUtilities.getRoot(c);
//                 frame.dispose();
// 			}
//         });
		
// 		this.container.add(inputBlock);
// 		this.container.add(btnsBlock);
// 	}
// }
