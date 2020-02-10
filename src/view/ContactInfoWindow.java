package view;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.ContactsModel;

public class ContactInfoWindow extends JDialog {

    private static final long serialVersionUID = 1L; 

    public ContactInfoWindow(JDialog previousDialog, JTextField textFieldName, JList<String> contactList,
			ContactsModel model) {
		
		JDialog dialog = new JDialog(previousDialog, "Informations du contact");
        dialog.setSize(350, 135);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
        dialog.setModalityType(ModalityType.APPLICATION_MODAL);

		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		
		//blocs
		JPanel inputBlock = new JPanel();
		JPanel btnsBlock = new JPanel();
		
		//label input
		JLabel label = new JLabel();
		label.setText("Entrez les informations du contact :");
		inputBlock.add(label);
		
		//input
		JTextField textFieldInfo = new JTextField();
		textFieldInfo.setPreferredSize(new Dimension(300,20));
		inputBlock.add(textFieldInfo);
		
		//boutons
		JButton btnOK = new JButton("OK");
		JButton btnBack = new JButton("Retour");
		btnsBlock.add(btnOK);
		btnsBlock.add(btnBack);
		
		//gère bouton retour
		btnBack.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				dialog.dispose();
				previousDialog.setVisible(true);
			}
		});
		
		btnOK.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				dialog.dispose();
				dialog.dispose();

				String contactName = textFieldName.getText();
				String contactInfo = textFieldInfo.getText();

				int index = model.insertAndGenerateList(contactName, contactInfo);

				contactList.setSelectedIndex(index);
				contactList.ensureIndexIsVisible(index); //scroll si hors champ
			}
        });
		
		container.add(inputBlock);
		container.add(btnsBlock);
		dialog.add(container);
        
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
	}
}
