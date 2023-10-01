package com.br.alura.forum.domain.usuario;

import com.br.alura.forum.modelo.Usuario;

public record DadosListagemUsuario(Long id, String nome, String email) {
	
	public DadosListagemUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getEmail());
	}

}
