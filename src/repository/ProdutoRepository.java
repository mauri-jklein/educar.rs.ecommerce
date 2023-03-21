/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import resources.Util;

/**
 *
 * @author Mauri
 */
public class ProdutoRepository {

    private Util util = new Util();
    Connection conn;
    PreparedStatement ppst;

    public List<Produto> buscarProdutos() {
        try {
            String sql = "SELECT * FROM produto";
            conn = util.conexao();
            ppst = conn.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                Produto produto = new Produto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5));
                produtos.add(produto);
            }
            return produtos;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível consultar o banco.");
        }
        return null;
    }

    public Produto salvarProduto(Produto produto) {
        conn = util.conexao();
        String sql = "INSERT INTO produto("
                + "nome,"
                + "descricao,"
                + "preco,"
                + "estoque) "
                + "VALUES(?,?,?,?)";
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, produto.getNome());
            ppst.setString(2, produto.getDescricao());
            ppst.setDouble(3, produto.getPreco());
            ppst.setInt(4, produto.getEstoque());

            ppst.executeUpdate();
            ppst.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
}
