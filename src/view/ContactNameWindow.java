package view;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ContactInfoWindowController;
import model.ContactsModel;

public class ContactNameWindow extends JDialog {

    private static final long serialVersionUID = 1L;

	public ContactNameWindow(ContactsModel model, JList<String> contactList) {

        JDialog dialog = new JDialog((JDialog)null, "Nom du contact");
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
		label.setText("Entrez le nom du contact :");
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
				dialog.dispose();
			}
		});

		ContactInfoWindowController infoListener = new ContactInfoWindowController(dialog, textFieldName, contactList, model);
		btnOK.addMouseListener(infoListener);
		
		container.add(inputBlock);
        container.add(btnsBlock);
        dialog.add(container);

        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
	}
}
