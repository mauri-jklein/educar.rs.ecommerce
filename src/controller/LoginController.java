/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cliente;
import entity.TipoUsuario;
import javax.swing.JOptionPane;
import service.ClienteService;

/**
 *
 * @author Mauri
 */
public class LoginController extends javax.swing.JFrame {

    public LoginController() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void validarLogin(Cliente cliente) {
        ClienteService clienteService = new ClienteService();
        cliente = clienteService.validarLogin(cliente);
        if (cliente != null) {
            if (cliente.getTipo().equals(TipoUsuario.CLIENTE)) {
                new ProdutoController(cliente).setVisible(true);
                this.dispose();
            } else {
                new ProdutoController().setVisible(true);
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível fazer login\n"
                    + "Confira seus dados e tente novamente");
        }
    }

    private void cadastrarLogin(Cliente cliente) {
        ClienteService clienteService = new ClienteService();
        cliente.setTipo(TipoUsuario.CLIENTE);
        cliente.setNome(jtfEmail.getText().substring(0, jtfEmail.getText().indexOf('@')));
        cliente = clienteService.salvarCliente(cliente);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlLogin = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jbEntrar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jpfSenha = new javax.swing.JPasswordField();
        jlNovoCadastro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jlLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlLogin.setText("Fazer Login");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Usuario:");

        jtfEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtfEmail.setToolTipText("");
        jtfEmail.setName(""); // NOI18N
        jtfEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfEmailMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Senha:");

        jbEntrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbEntrar.setText("Entrar");
        jbEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbEntrarMouseEntered(evt);
            }
        });
        jbEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEntrarActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Cancelar");

        jpfSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jlNovoCadastro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlNovoCadastro.setForeground(new java.awt.Color(0, 0, 153));
        jlNovoCadastro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlNovoCadastro.setText("Cadastrar Novo Cliente");
        jlNovoCadastro.setToolTipText("TESTE");
        jlNovoCadastro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlNovoCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlNovoCadastroMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jpfSenha)))
                        .addContainerGap(54, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(148, 148, 148))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlNovoCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlLogin)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jpfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jbEntrar)
                .addGap(4, 4, 4)
                .addComponent(jlNovoCadastro)
                .addGap(11, 11, 11)
                .addComponent(jButton2)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfEmailMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jtfEmailMouseClicked

    private void jbEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEntrarActionPerformed
        // TODO add your handling code here:
        Cliente cliente = new Cliente();
        cliente.setEmail(jtfEmail.getText());
        cliente.setSenha(jpfSenha.getText());

        if (jbEntrar.getText().equals("Entrar")) {
            validarLogin(cliente);
        } else {
            cadastrarLogin(cliente);
        }
    }//GEN-LAST:event_jbEntrarActionPerformed

    private void jlNovoCadastroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlNovoCadastroMouseClicked
        // TODO add your handling code here:
        jlLogin.setText("Novo Cliente");
        jbEntrar.setText("Cadastrar");
        jlNovoCadastro.setText("");
    }//GEN-LAST:event_jlNovoCadastroMouseClicked

    private void jbEntrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbEntrarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jbEntrarMouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginController().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jbEntrar;
    private javax.swing.JLabel jlLogin;
    private javax.swing.JLabel jlNovoCadastro;
    private javax.swing.JPasswordField jpfSenha;
    private javax.swing.JTextField jtfEmail;
    // End of variables declaration//GEN-END:variables

}
