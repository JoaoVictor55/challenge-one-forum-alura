package com.br.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.alura.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
