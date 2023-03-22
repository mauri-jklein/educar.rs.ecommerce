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
            String sql = "SELECT * FROM produto order by nome";
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

    public Produto buscarProduto(int id) {
        try {
            String sql = "SELECT * FROM produto where id = ?";
            conn = util.conexao();
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, id);
            ResultSet rs = ppst.executeQuery();
            while (rs.next()) {
                return new Produto(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4), rs.getInt(5));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível consultar o banco.");
        }
        return null;
    }

    public Produto editarProduto(Produto produto) {
        conn = util.conexao();
        String sql = "UPDATE produto SET nome = ?, "
                + "descricao = ?, "
                + "preco = ?, "
                + "estoque = ? "
                + "WHERE id = ?";
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, produto.getNome());
            ppst.setString(2, produto.getDescricao());
            ppst.setDouble(3, produto.getPreco());
            ppst.setInt(4, produto.getEstoque());
            ppst.setInt(5, produto.getId());
            System.out.println(produto);
            System.out.println(sql);
            ppst.executeUpdate();
            ppst.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    
     public Produto excluirProduto(int id) {
        conn = util.conexao();
        String sql = "DELETE FROM produto WHERE id = ?";
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, id);
            ppst.executeUpdate();
            ppst.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }

}
