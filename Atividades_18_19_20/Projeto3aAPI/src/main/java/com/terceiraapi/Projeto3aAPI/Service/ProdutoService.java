package com.terceiraapi.Projeto3aAPI.Service;

import com.terceiraapi.Projeto3aAPI.Model.Produto;
import com.terceiraapi.Projeto3aAPI.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto salvar(Produto produto) {
        validar(produto);
        produto.setId(null); // o banco gera o ID
        return produtoRepository.save(produto);
    }

    public Optional<Produto> atualizar(Long id, Produto dados) {
        validar(dados);
        return produtoRepository.findById(id).map(existente -> {
            existente.setNome(dados.getNome());
            existente.setPreco(dados.getPreco());
            return produtoRepository.save(existente);
        });
    }

    public boolean deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            return false;
        }
        produtoRepository.deleteById(id);
        return true;
    }

    // Regra de negocio: nome obrigatorio e preco nao negativo
    private void validar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do produto e obrigatorio.");
        }
        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("O preco nao pode ser negativo.");
        }
    }
}
