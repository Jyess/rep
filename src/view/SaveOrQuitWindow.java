// package view;

// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;

// import javax.swing.BoxLayout;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;

// import controller.FrameController;

// public class SaveOrQuitWindow extends JFrame {

//     private static final long serialVersionUID = 1L;

//     public SaveOrQuitWindow() {

//         FrameController fc = new FrameController();

//         // fenetre de demande de sauvegarde
//         setTitle("Modifications non enregistrées");
//         setSize(400, 150); // dimension de la fenetre
//         setLocationRelativeTo(null); // RelativeTo pour centrer la fenetre
//         setResizable(false);
//         setDefaultCloseOperation(DISPOSE_ON_CLOSE);

//         JPanel container = new JPanel();
//         container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

//         // blocs
//         JPanel msgBlock = new JPanel();
//         JPanel btnsBlock = new JPanel();

//         // label input
//         JLabel label = new JLabel();
//         label.setText("Souhaitez-vous sauvegarder vos changements ?");
//         msgBlock.add(label);

//         // boutons
//         JButton btnSave = new JButton("Enregistrer");
//         JButton btnQuit = new JButton("Ne pas enregistrer");
//         JButton btnCancel = new JButton("Annuler");
//         btnsBlock.add(btnSave);
//         btnsBlock.add(btnQuit);
//         btnsBlock.add(btnCancel);

//         // listeners
//         btnSave.addMouseListener(new MouseAdapter() {
//             @Override
//             public void mouseClicked(MouseEvent e) {
//                 fc.saveContactList();
//             }
//         });

//         btnQuit.addMouseListener(new MouseAdapter() {
//             @Override
//             public void mouseClicked(MouseEvent e) {
//                 System.exit(0);
//             }
//         });

//         btnCancel.addMouseListener(new MouseAdapter() {
//             @Override
//             public void mouseClicked(MouseEvent e) {
//                 dispose();
//             }
//         });
        
//         container.add(msgBlock);
//         container.add(btnsBlock);

//         add(container);
//         setVisible(true);
// 	}
// }
