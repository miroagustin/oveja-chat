package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Chat.SocketManager;
import Chat.SocketReader;
import Chat.SocketWriter;

public class ClienteUI extends JFrame {
    private SocketWriter socketWriter;
    private SocketReader socketReader;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextField txtInput;
	private static final long serialVersionUID = 1L;
	private String nombreUsuario;
	
	public ClienteUI() {
        String username = JOptionPane.showInputDialog("Por favor ingrese un nombre de usuario");
        nombreUsuario = username.isEmpty() ? "Anonimo" : username;
        initComponents();
        socketReader = new SocketReader();
        socketReader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = e.getActionCommand();
                textArea.append(text);
                textArea.append("\n");
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        });
        socketReader.execute();
        socketWriter = new SocketWriter();

    }
                      
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        txtInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Oveja Chat - Usuario: " + nombreUsuario);
        setLocationRelativeTo(null);

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setText(nombreUsuario + ":\nBienvenid@ a Oveja Chat, envie un mensaje con enter\n");
        textArea.setWrapStyleWord(true);
        textArea.setCaretPosition(textArea.getDocument().getLength());
        scrollPane.setViewportView(textArea);

        txtInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane,500, javax.swing.GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
                    .addComponent(txtInput))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane,300,javax.swing.GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
                .addContainerGap()
                .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE )
            .addContainerGap())
        );

        pack();
    }                    



    private void txtInputActionPerformed(java.awt.event.ActionEvent evt) {
        if (SocketManager.getInstance().isOpen()) {
            socketWriter.write(nombreUsuario + ": " + txtInput.getText());
        } else {
            System.out.println("!! Not open");
        }
        txtInput.setText("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(ClienteUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(ClienteUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(ClienteUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(ClienteUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }


            SocketManager.getInstance().open();


            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new ClienteUI().setVisible(true);
                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
        }    

    }


}
