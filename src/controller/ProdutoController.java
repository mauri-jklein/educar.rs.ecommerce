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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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

    public ProdutoController(Cliente cliente) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.cliente = cliente;
        this.pedido = pedido;
        desabilitarOpcoesAdmin();
        configurarLarguraColunas();
        preencheTabela(produtoService.buscarProdutos());
    }

    private void desabilitarOpcoesAdmin() {
        jbNovo.setVisible(false);
        jbEditar.setVisible(false);
        jbExcluir.setVisible(false);
        jbLocalizar.setVisible(false);
    }

    private void desabilitarOpcoesCliente() {
        jbComprar.setVisible(false);
    }

    private void preencheTabela(List<Produto> produtos) {
        int i = 0;
        for (Produto p : produtos) {
            jtListaProdutos.setValueAt(p.getId(), i, 0);
            jtListaProdutos.setValueAt(p.getNome(), i, 1);
            jtListaProdutos.setValueAt(p.getDescricao(), i, 2);
            jtListaProdutos.setValueAt("R$ " + df.format(p.getPreco()), i, 3);
            jtListaProdutos.setValueAt(p.getEstoque(), i, 4);
            i++;
        }
    }

    public void preencheTabela(Produto produto, int linha) {
        jtListaProdutos.setValueAt("", linha, 0);
        jtListaProdutos.setValueAt(produto.getId(), linha, 0);
        jtListaProdutos.setValueAt("", linha, 1);
        jtListaProdutos.setValueAt(produto.getNome(), linha, 1);
        jtListaProdutos.setValueAt("", linha, 2);
        jtListaProdutos.setValueAt(produto.getDescricao(), linha, 2);
        jtListaProdutos.setValueAt("", linha, 3);
        jtListaProdutos.setValueAt("R$ " + df.format(produto.getPreco()), linha, 3);
        jtListaProdutos.setValueAt("", linha, 4);
        jtListaProdutos.setValueAt(produto.getEstoque(), linha, 4);
    }

    public void limparTabela() {
        for (int i = 1; i < jtListaProdutos.getRowCount(); i++) {
            jtListaProdutos.setValueAt(null, i, 0);
            jtListaProdutos.setValueAt(null, i, 1);
            jtListaProdutos.setValueAt(null, i, 2);
            jtListaProdutos.setValueAt(null, i, 3);
            jtListaProdutos.setValueAt(null, i, 4);
        }
    }

    private void configurarLarguraColunas() {
        jtListaProdutos.getColumnModel().getColumn(0).setPreferredWidth(jtListaProdutos.getWidth() / 20);//5%
        jtListaProdutos.getColumnModel().getColumn(1).setPreferredWidth(jtListaProdutos.getWidth() / 4);//25%
        jtListaProdutos.getColumnModel().getColumn(2).setPreferredWidth(jtListaProdutos.getWidth() / 2);//50%
        jtListaProdutos.getColumnModel().getColumn(3).setPreferredWidth(jtListaProdutos.getWidth() / 10);//10%
        jtListaProdutos.getColumnModel().getColumn(4).setPreferredWidth(jtListaProdutos.getWidth() / 10);//10%
        alinharColuna(0, 0);
        alinharColuna(3, 4);
    }

    private void alinharColuna(int coluna, int alinhameto) {
        DefaultTableCellRenderer alinharDireita = new DefaultTableCellRenderer();
        alinharDireita.setHorizontalAlignment(alinhameto);
        jtListaProdutos.getColumnModel().getColumn(coluna).setCellRenderer(alinharDireita);
    }

    private Produto lerDadosDaLinhaSelecionada() {
        int linha = jtListaProdutos.getSelectedRow();
        Produto produto = new Produto(
                Integer.parseInt(jtListaProdutos.getValueAt(linha, 0).toString()),
                jtListaProdutos.getValueAt(linha, 1).toString(),
                jtListaProdutos.getValueAt(linha, 2).toString(),
                Double.parseDouble(
                        jtListaProdutos.getValueAt(linha, 3).toString().replace(".", "").replace(",", ".").substring(3, jtListaProdutos.getValueAt(linha, 3).toString().length() - 2)),
                Integer.parseInt(jtListaProdutos.getValueAt(linha, 4).toString()));
        return produto;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtListaProdutos = new javax.swing.JTable();
        jbNovo = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jbLocalizar = new javax.swing.JButton();
        jbComprar = new javax.swing.JButton();
        jbProximaPagina = new javax.swing.JButton();
        jbPaginaAnterior = new javax.swing.JButton();
        jlPagina = new javax.swing.JLabel();
        jbListar = new javax.swing.JButton();
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

        jbNovo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbNovo.setText("Novo");
        jbNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNovoActionPerformed(evt);
            }
        });

        jbEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbEditar.setText("Editar");
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbExcluir.setText("Excluir");
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });

        jbLocalizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbLocalizar.setText("Localizar");
        jbLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLocalizarActionPerformed(evt);
            }
        });

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

        jbListar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbListar.setText("Listar");
        jbListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbListarActionPerformed(evt);
            }
        });

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
                        .addComponent(jbNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbListar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
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
                    .addComponent(jbNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbListar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        // TODO add your handling code here:
        if ((jtListaProdutos.getSelectedRow() < 0) || (jtListaProdutos.getValueAt(jtListaProdutos.getSelectedRow(), 0) == null)) {
            JOptionPane.showMessageDialog(null, "Você precisa selecionar um registro para Editar");
        } else {
            new NovoProdutoController(lerDadosDaLinhaSelecionada(), jtListaProdutos.getSelectedRow()).setVisible(true);
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed
        // TODO add your handling code here:
        new NovoProdutoController().setVisible(true);
    }//GEN-LAST:event_jbNovoActionPerformed

    private void jbProximaPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbProximaPaginaActionPerformed
        // TODO add your handling code here:
        jlPagina.setText((Integer.parseInt(jlPagina.getText()) + 1) + "");
    }//GEN-LAST:event_jbProximaPaginaActionPerformed

    private void jbPaginaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPaginaAnteriorActionPerformed
        // TODO add your handling code here:
        if (!jlPagina.getText().equals("1")) {
            jlPagina.setText((Integer.parseInt(jlPagina.getText()) - 1) + "");
        }
    }//GEN-LAST:event_jbPaginaAnteriorActionPerformed

    private void jbComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbComprarActionPerformed
        // TODO add your handling code here:
        Pedido pedido = new Pedido();
        int i = 1;
        jbComprar.setText("Comprar (" + i + ")");
    }//GEN-LAST:event_jbComprarActionPerformed

    private void jbLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLocalizarActionPerformed
        // TODO add your handling code here:
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do Produto: "));
            ProdutoService produtoService = new ProdutoService();
            Produto produto = produtoService.buscarProduto(id);
            if (produto != null) {
                preencheTabela(produto, 0);
                limparTabela();
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum Produto encontrado com esse ID.");
            }
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jbLocalizarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        // TODO add your handling code here:
        if ((jtListaProdutos.getSelectedRow() < 0) || (jtListaProdutos.getValueAt(jtListaProdutos.getSelectedRow(), 0) == null)) {
            JOptionPane.showMessageDialog(null, "Você precisa selecionar um registro para Editar");
        } else if (JOptionPane.showConfirmDialog(null, "Voce tem certeza que deseja excluir o produto?\n"
                + lerDadosDaLinhaSelecionada()) == 0) {
            produtoService.excluirProduto(lerDadosDaLinhaSelecionada().getId());
            limparTabela();
            preencheTabela(produtoService.buscarProdutos());
        }
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jbListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbListarActionPerformed
        // TODO add your handling code here:
        limparTabela();
        preencheTabela(produtoService.buscarProdutos());
    }//GEN-LAST:event_jbListarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbComprar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbListar;
    private javax.swing.JButton jbLocalizar;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbPaginaAnterior;
    private javax.swing.JButton jbProximaPagina;
    private javax.swing.JLabel jlPagina;
    private javax.swing.JTable jtListaProdutos;
    // End of variables declaration//GEN-END:variables
}
