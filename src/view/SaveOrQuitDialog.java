package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.SaveOrQuitController;
import model.ContactsModel;

public class SaveOrQuitDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public SaveOrQuitDialog(ContactsModel model) {

        JDialog dialog = new JDialog((JDialog)null, "Modifications non enregistrées");
        dialog.setSize(400, 120);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setModalityType(ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // blocs
        JPanel msgBlock = new JPanel();
        JPanel btnsBlock = new JPanel();

        // label input
        JLabel label = new JLabel();
        label.setText("Souhaitez-vous enregistrer vos modifications ?");
        msgBlock.add(label);

        // boutons
        JButton btnSave = new JButton("Enregistrer");
        JButton btnQuit = new JButton("Ne pas enregistrer");
        JButton btnCancel = new JButton("Annuler");
        btnsBlock.add(btnSave);
        btnsBlock.add(btnQuit);
        btnsBlock.add(btnCancel);

        SaveOrQuitController listener = new SaveOrQuitController(model);
        btnSave.addActionListener(listener);
        btnQuit.addActionListener(listener);
        btnCancel.addActionListener(listener);
        
        container.add(msgBlock);
        container.add(btnsBlock);
        dialog.add(container);

        dialog.setVisible(true);
	}
}
