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
    
    public List<Produto> buscarProdutos(){
        return produtoRepository.buscarProdutos();
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.salvarProduto(produto);
    }
    
}
