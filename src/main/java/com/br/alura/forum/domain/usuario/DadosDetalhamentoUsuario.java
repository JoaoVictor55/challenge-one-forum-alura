package com.br.alura.forum.domain.usuario;

import com.br.alura.forum.modelo.Usuario;

public record DadosDetalhamentoUsuario(
		Long id,
		String nome,
		String email
		) 

{
	
	public DadosDetalhamentoUsuario(Usuario usuario) {
		
		this(usuario.getId(), usuario.getNome(), usuario.getEmail());
	}

}
