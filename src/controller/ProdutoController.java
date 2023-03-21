/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cliente;
import entity.Pedido;
import entity.Produto;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import service.ProdutoService;

/**
 *
 * @author Mauri
 */
public class ProdutoController extends javax.swing.JFrame {
private ProdutoService produtoService = new ProdutoService();
DecimalFormat df = new DecimalFormat("###,###,##0.00");

private Cliente cliente;
private Pedido pedido;


    public ProdutoController() {
        initComponents();
        this.setLocationRelativeTo(null);
        desabilitarOpcoesCliente();
        configurarLarguraColunas();
        preencheTabela(produtoService.buscarProdutos());
    }
    
    public ProdutoController(Cliente cliente){
        initComponents();
        this.setLocationRelativeTo(null);
        this.cliente = cliente;
        this.pedido = pedido;
        desabilitarOpcoesAdmin();
        configurarLarguraColunas();
        preencheTabela(produtoService.buscarProdutos());
    }
    
    private void desabilitarOpcoesAdmin(){
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jButton4.setVisible(false);
    }
    
    private void desabilitarOpcoesCliente(){
        jbComprar.setVisible(false);
    }
    
    private void preencheTabela(List<Produto> produtos){
        int i=0;
        for(Produto p: produtos){
            jtListaProdutos.setValueAt(p.getId(), i, 0);
            jtListaProdutos.setValueAt(p.getNome(), i, 1);
            jtListaProdutos.setValueAt(p.getDescricao(), i, 2);
            jtListaProdutos.setValueAt("R$ "+df.format(p.getPreco()), i, 3);
            jtListaProdutos.setValueAt(p.getEstoque(), i, 4);
            i++;
        }
    }
    
    private void configurarLarguraColunas(){
        jtListaProdutos.getColumnModel().getColumn(0).setPreferredWidth(jtListaProdutos.getWidth()/20);//5%
        jtListaProdutos.getColumnModel().getColumn(1).setPreferredWidth(jtListaProdutos.getWidth()/4);//25%
        jtListaProdutos.getColumnModel().getColumn(2).setPreferredWidth(jtListaProdutos.getWidth()/2);//50%
        jtListaProdutos.getColumnModel().getColumn(3).setPreferredWidth(jtListaProdutos.getWidth()/10);//10%
        jtListaProdutos.getColumnModel().getColumn(4).setPreferredWidth(jtListaProdutos.getWidth()/10);//10%
        alinharColuna(0, 0);
        alinharColuna(3, 4);
        
    }
    
    private void alinharColuna(int coluna, int alinhameto){
        DefaultTableCellRenderer alinharDireita = new DefaultTableCellRenderer();
	alinharDireita.setHorizontalAlignment(alinhameto);
        jtListaProdutos.getColumnModel().getColumn(coluna).setCellRenderer(alinharDireita);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtListaProdutos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jbComprar = new javax.swing.JButton();
        jbProximaPagina = new javax.swing.JButton();
        jbPaginaAnterior = new javax.swing.JButton();
        jlPagina = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PRODUTOS");

        jtListaProdutos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtListaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "DESCRIÇÃO", "PREÇO", "ESTOQUE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtListaProdutos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtListaProdutos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jtListaProdutos);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Novo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Excluir");

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Localizar");

        jbComprar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbComprar.setText("Comprar");
        jbComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbComprarActionPerformed(evt);
            }
        });

        jbProximaPagina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbProximaPagina.setText(">>");
        jbProximaPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbProximaPaginaActionPerformed(evt);
            }
        });

        jbPaginaAnterior.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbPaginaAnterior.setText("<<");
        jbPaginaAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPaginaAnteriorActionPerformed(evt);
            }
        });

        jlPagina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlPagina.setText("1");

        jMenu1.setText("Arquivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbPaginaAnterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbProximaPagina))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
                        .addComponent(jbComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlPagina, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbProximaPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbPaginaAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new NovoProdutoController().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbProximaPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbProximaPaginaActionPerformed
        // TODO add your handling code here:
        jlPagina.setText((Integer.parseInt(jlPagina.getText())+1)+"");
    }//GEN-LAST:event_jbProximaPaginaActionPerformed

    private void jbPaginaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPaginaAnteriorActionPerformed
        // TODO add your handling code here:
        if(!jlPagina.getText().equals("1")){
            jlPagina.setText((Integer.parseInt(jlPagina.getText())-1)+"");
        }
    }//GEN-LAST:event_jbPaginaAnteriorActionPerformed

    private void jbComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbComprarActionPerformed
        // TODO add your handling code here:
        Pedido pedido = new Pedido();
        int i=1;
        jbComprar.setText("Comprar ("+ i +")");
    }//GEN-LAST:event_jbComprarActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbComprar;
    private javax.swing.JButton jbPaginaAnterior;
    private javax.swing.JButton jbProximaPagina;
    private javax.swing.JLabel jlPagina;
    private javax.swing.JTable jtListaProdutos;
    // End of variables declaration//GEN-END:variables
}
