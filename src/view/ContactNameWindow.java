package view;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ContactInfoWindowController;
import model.ContactsModel;

public class ContactNameWindow extends JFrame {

    private static final long serialVersionUID = 1L;

	public ContactNameWindow(ContactsModel model, JList<String> contactList) {

        JFrame frame = new JFrame("Nom du contact");
        frame.setSize(350, 150);
        frame.setLocationRelativeTo(null);
        
        JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		
		//blocs
		JPanel inputBlock = new JPanel();
		JPanel btnsBlock = new JPanel();
		
		//label input
		JLabel label = new JLabel();
		label.setText("Entrez le nom souhaité");
		inputBlock.add(label);
		
		//input
		JTextField textFieldName = new JTextField();
		textFieldName.setPreferredSize(new Dimension(300,20));
		inputBlock.add(textFieldName);
		
		//boutons
		JButton btnOK = new JButton("OK");
		JButton btnCancel = new JButton("Annuler");
		btnsBlock.add(btnOK);
		btnsBlock.add(btnCancel);
		
		//gère bouton annuler
		btnCancel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});

		ContactInfoWindowController infoListener = new ContactInfoWindowController(frame, textFieldName, contactList, model);
		btnOK.addMouseListener(infoListener);
		
		// btnOK.addMouseListener(new MouseAdapter() {

		// 	@Override
		// 	public void mouseClicked(MouseEvent e) {                
        //         String contactName = textField.getText();
        //         new AddContactInfoWindow(frame, contactName, model);
        //         frame.setVisible(false);
		// 	}
		// });
		
		container.add(inputBlock);
        container.add(btnsBlock);
        frame.add(container);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
	}
}
