package com.br.alura.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.alura.forum.modelo.Topico;

public interface TopicosRepository extends JpaRepository<Topico, Long>{

	@Query("""
			select t from Topico as t inner join Curso as c on
			t.curso = c.id and c.nome like :curso
			""")
	public Page<Topico> findTopicoPorCurso(Pageable paginacao, String curso);
	


	

}
