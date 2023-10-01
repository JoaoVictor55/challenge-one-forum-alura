package com.br.alura.forum.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.alura.forum.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query(
	"select u from Usuario as u where u.ativo and u.id = :id"
			)
	public Usuario findAtivoById(Long id);

	public Page<Usuario> findAllByAtivoTrue(Pageable paginacao);
}
