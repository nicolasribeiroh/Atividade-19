package com.terceiraapi.Projeto3aAPI.Repository;

import com.terceiraapi.Projeto3aAPI.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // save(), findAll(), findById(), deleteById() ja vem prontos do JpaRepository
}
