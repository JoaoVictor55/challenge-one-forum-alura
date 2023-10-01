package com.br.alura.forum.domain.usuario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoUsuarios(
		@NotNull
		Long id,
		String nome,
		@Pattern(regexp="[a-zA-Z0-9]+@[a-z]+.com|^$")
		String email,
		String senha
		) {

}
