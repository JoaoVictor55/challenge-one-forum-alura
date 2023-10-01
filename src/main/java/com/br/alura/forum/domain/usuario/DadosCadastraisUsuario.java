package com.br.alura.forum.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastraisUsuario(
		@NotBlank
		String nome,
		@NotBlank
		String email,
		@NotBlank
		String senha
		) {

}
