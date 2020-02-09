package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.SaveOrQuitController;
import model.ContactsModel;

public class SaveOrQuitWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    public SaveOrQuitWindow(ContactsModel model) {

        // fenetre de demande de sauvegarde
        JFrame frame = new JFrame("Modifications non enregistrées");
        frame.setSize(400, 150); // dimension de la fenetre
        frame.setLocationRelativeTo(null); // RelativeTo pour centrer la fenetre
        frame.setResizable(false);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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

        SaveOrQuitController listener = new SaveOrQuitController(btnSave, btnQuit, btnCancel, frame, model);
        btnSave.addMouseListener(listener);
        btnQuit.addMouseListener(listener);
        btnCancel.addMouseListener(listener);
        
        container.add(msgBlock);
        container.add(btnsBlock);
        frame.add(container);

        frame.add(container);
        frame.setVisible(true);
	}
}
