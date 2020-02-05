package view;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SaveOrQuitWindow {

    private static final long serialVersionUID = 1L;
    protected JPanel container;

    public SaveOrQuitWindow() {
        this.container = new JPanel();
        this.container.setLayout(new BoxLayout(this.container, BoxLayout.Y_AXIS));

        // blocs
        JPanel msgBlock = new JPanel();
        JPanel btnsBlock = new JPanel();

        // label input
        JLabel label = new JLabel();
        label.setText("Souhaitez-vous sauvegarder vos changements ?");
        msgBlock.add(label);

        // boutons
        JButton btnSave = new JButton("Enregistrer");
        JButton btnQuit = new JButton("Ne pas enregistrer");
        JButton btnCancel = new JButton("Annuler");
        btnsBlock.add(btnSave);
        btnsBlock.add(btnQuit);
        btnsBlock.add(btnCancel);

        // listeners
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                super.mouseClicked(e);
            }
        });

        btnQuit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component c = (Component) e.getSource();
				JFrame frame = (JFrame) SwingUtilities.getRoot(c);
				frame.dispose();
            }
        });
        
        this.container.add(msgBlock);
		this.container.add(btnsBlock);
	}
}
