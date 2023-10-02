package com.br.alura.forum.domain.topico;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizarTopico(
		@NotNull
		Long id,
		
		Long curso,
		
		String titulo,
		
		String mensagem
		) {

}
