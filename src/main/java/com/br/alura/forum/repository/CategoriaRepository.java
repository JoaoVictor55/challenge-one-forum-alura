package com.br.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.alura.forum.modelo.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
