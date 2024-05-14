/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Produto;
import java.util.List;
import repository.ProdutoRepository;

/**
 *
 * @author Mauri
 */
public class ProdutoService {

    private final ProdutoRepository produtoRepository = new ProdutoRepository();

    public List<Produto> buscarProdutos(int pagina) {
        return produtoRepository.buscarProdutos(pagina);
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.salvarProduto(produto);
    }

    public Produto buscarProduto(int id) {
        return produtoRepository.buscarProduto(id);
    }

     public List<Produto> buscarProdutosPorDescricao(String descricao) {
        return produtoRepository.buscarProdutosPorDescricao(descricao);
    }
     
    public Produto editarProduto(Produto produto) {
        return produtoRepository.editarProduto(produto);
    }

    public Produto excluirProduto(int id) {
        return produtoRepository.excluirProduto(id);
    }

}
