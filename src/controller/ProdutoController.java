/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cliente;
import entity.ItemPedido;
import entity.Pedido;
import entity.Produto;
import entity.TipoUsuario;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import service.PedidoService;
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

    public ProdutoController(Cliente cliente) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.cliente = cliente;
        configurarLarguraColunas();
        preencheTabela(produtoService.buscarProdutos(0));

        if (this.cliente.getTipo().equals(TipoUsuario.CLIENTE)) {
            desabilitarOpcoesAdmin();
            //Instruções relacionadas ao carrinho de compras;
            this.pedido = new Pedido();
            this.pedido.setCliente(this.cliente);
        } else {
            desabilitarOpcoesCliente();
        }
    }

    private void desabilitarOpcoesAdmin() {
        jbNovo.setVisible(false);
        jbEditar.setVisible(false);
        jbExcluir.setVisible(false);
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
        for (int i = 0; i < jtListaProdutos.getRowCount(); i++) {
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

    private boolean linhaEstaSelecionada() {
        return ((jtListaProdutos.getSelectedRow() >= 0)
                && (jtListaProdutos.getValueAt(jtListaProdutos.getSelectedRow(), 0) != null));
    }

    private Produto lerDadosDaLinhaSelecionada() {
        int linha = jtListaProdutos.getSelectedRow();
        Produto produto = new Produto(
                Integer.parseInt(jtListaProdutos.getValueAt(linha, 0).toString()),
                jtListaProdutos.getValueAt(linha, 1).toString(),
                jtListaProdutos.getValueAt(linha, 2).toString(),
                Double.parseDouble(jtListaProdutos.getValueAt(linha, 3).toString()
                        .replace(".", "").replace(",", ".")
                        .substring(3, jtListaProdutos.getValueAt(linha, 3).toString().length() - 1)),
                Integer.parseInt(jtListaProdutos.getValueAt(linha, 4).toString()));
        return produto;
    }

    private int ObterQuantidadeDoItem() {
        try {
            return Integer.parseInt(JOptionPane.showInputDialog("Informe quantas unidades do "
                    + "produto deseja adicionar ao Pedido"));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Você precisa informar um número válido para indicar a quantidade do produto!");
            return 0;
        }
    }

    private boolean verificarEstoque(Produto produto, int quantidade) {
        if ((quantidade > 0) && (produto.getEstoque() >= quantidade)) {
            return true;
        }
        return false;
    }

    private int quantidadeNoPedido(int idProtudo) {
        for (ItemPedido ip : pedido.getItensPedido()) {
            if (ip.getProduto().getId() == idProtudo) {
                return ip.getQuantidade();
            }
        }
        return 0;
    }

    private void incrementaQuantidadeItem(Produto produto, int quantidade) {
        if (verificarEstoque(produto, quantidade)) {
            for (ItemPedido ip : pedido.getItensPedido()) {
                if (ip.getProduto().getId() == produto.getId()) {
                    ip.setQuantidade(quantidade);
                    ip.setValor(ip.getProduto().getPreco() * quantidade);
                    return;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "A quantidade indicada não está "
                    + "no intervalo válido.");
        }
    }

    private void adicionaNovoItem(Produto produto, int quantidade) {
        if (verificarEstoque(produto, quantidade)) {
            pedido.getItensPedido().add(new ItemPedido(quantidade * produto.getPreco(),
                    quantidade, produto));
            jbComprar.setText("Comprar(" + pedido.getItensPedido().size() + ")");
        } else {
            JOptionPane.showMessageDialog(null, "A quantidade indicada não está "
                    + "no intervalo válido.");
        }
    }

    private void buscarPorId() {
        ProdutoService produtoService = new ProdutoService();
        Produto produto = new Produto();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do Produto: "));
        produto = produtoService.buscarProduto(id);
        if (produto != null) {
            limparTabela();
            preencheTabela(produto, 0);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum Produto encontrado.");
        }
    }

    private void buscarPorDescricao() {
        List<Produto> produtos = new ArrayList<>();
        ProdutoService produtoService = new ProdutoService();
        String descricao = JOptionPane.showInputDialog("Pesquisar por:");
        produtos = produtoService.buscarProdutosPorDescricao(descricao);
        if (!produtos.isEmpty()) {
            limparTabela();
            preencheTabela(produtos);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum Produto encontrado.");
        }
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
        jMenu3 = new javax.swing.JMenu();
        jmVerCarrinho = new javax.swing.JMenuItem();
        jmFinalizarPedido = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmLocalizarPedido = new javax.swing.JMenuItem();

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

        jMenu3.setText("Pedido");

        jmVerCarrinho.setText("Ver Carrinho");
        jmVerCarrinho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmVerCarrinhoActionPerformed(evt);
            }
        });
        jMenu3.add(jmVerCarrinho);

        jmFinalizarPedido.setText("Finalizar Pedido");
        jmFinalizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmFinalizarPedidoActionPerformed(evt);
            }
        });
        jMenu3.add(jmFinalizarPedido);
        jMenu3.add(jSeparator1);

        jmLocalizarPedido.setText("Localizar Pedido (Histórico)");
        jmLocalizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmLocalizarPedidoActionPerformed(evt);
            }
        });
        jMenu3.add(jmLocalizarPedido);

        jMenuBar1.add(jMenu3);

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
                        .addComponent(jlPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            new NovoProdutoController(lerDadosDaLinhaSelecionada()).setVisible(true);
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed
        // TODO add your handling code here:
        new NovoProdutoController().setVisible(true);
    }//GEN-LAST:event_jbNovoActionPerformed

    private void jbProximaPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbProximaPaginaActionPerformed
        List<Produto> produtos = produtoService.buscarProdutos(Integer.parseInt(jlPagina.getText()));
        if (!produtos.isEmpty()) {
            limparTabela();
            preencheTabela(produtos);
            jlPagina.setText(
                    (Integer.parseInt(jlPagina.getText()) + 1) + "");
        }
    }//GEN-LAST:event_jbProximaPaginaActionPerformed
    private void jbPaginaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPaginaAnteriorActionPerformed
        if (!jlPagina.getText().equals("1")) {
            int pagina = (Integer.parseInt(jlPagina.getText()) - 1);
            limparTabela();
            preencheTabela(produtoService.buscarProdutos(pagina - 1));
            jlPagina.setText(pagina + "");
        }
    }//GEN-LAST:event_jbPaginaAnteriorActionPerformed

    private void jbComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbComprarActionPerformed
        if (linhaEstaSelecionada()) {
            int quantidade = ObterQuantidadeDoItem();
            Produto produto = lerDadosDaLinhaSelecionada();
            int quantidadeNoPedido = quantidadeNoPedido(produto.getId());
            if (quantidadeNoPedido != 0) {
                incrementaQuantidadeItem(produto, quantidade + quantidadeNoPedido);
            } else {
                adicionaNovoItem(produto, quantidade);
            }
        }
    }//GEN-LAST:event_jbComprarActionPerformed

    private void jbLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLocalizarActionPerformed
        if (this.cliente.getTipo().equals(TipoUsuario.ADMIN)) {
            buscarPorId();
        } else {
            buscarPorDescricao();
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
            preencheTabela(produtoService.buscarProdutos(0));
        }
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jbListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbListarActionPerformed
        // TODO add your handling code here:
        limparTabela();
        preencheTabela(produtoService.buscarProdutos(0));
        jlPagina.setText("1");
    }//GEN-LAST:event_jbListarActionPerformed

    private void jmFinalizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmFinalizarPedidoActionPerformed
        if (pedido.getItensPedido().size() > 0) {
            PedidoService pedidoService = new PedidoService();
            pedidoService.salvarPedido(pedido);
            limparTabela();
            preencheTabela(produtoService.buscarProdutos(0));
            jbComprar.setText("Comprar");
        } else {
            JOptionPane.showMessageDialog(null, "Você não tem produtos no carrinho.");
        }

    }//GEN-LAST:event_jmFinalizarPedidoActionPerformed

    private void jmVerCarrinhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmVerCarrinhoActionPerformed
        // TODO add your handling code here:
        new PedidoController(pedido).setVisible(true);

    }//GEN-LAST:event_jmVerCarrinhoActionPerformed

    private void jmLocalizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmLocalizarPedidoActionPerformed
        // TODO add your handling code here:
        try {
            int idPedido = Integer.parseInt(JOptionPane.showInputDialog("Informe o id do Pedido: "));
            PedidoService pedidoService = new PedidoService();
            pedido = pedidoService.buscarPedido(idPedido);
            if (pedido.getCliente().getEmail().equals(this.cliente.getEmail())) {
                new PedidoController(pedido).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Não existe nenhum pedido seu com este ID!!!");
            }
        } catch (IllegalArgumentException ilex) {
            JOptionPane.showMessageDialog(null, "Aconteceu algo de errado!!!" + ilex);
        }

    }//GEN-LAST:event_jmLocalizarPedidoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JButton jbComprar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbListar;
    private javax.swing.JButton jbLocalizar;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbPaginaAnterior;
    private javax.swing.JButton jbProximaPagina;
    private javax.swing.JLabel jlPagina;
    private javax.swing.JMenuItem jmFinalizarPedido;
    private javax.swing.JMenuItem jmLocalizarPedido;
    private javax.swing.JMenuItem jmVerCarrinho;
    private javax.swing.JTable jtListaProdutos;
    // End of variables declaration//GEN-END:variables
}
