package com.br.alura.forum.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastrarNovoTopico(
		
		@NotNull
		Long idAutor,
		
		@NotNull
		Long idCurso,
		
		@NotBlank
		String titulo, 
		
		@NotBlank
		String mensagem) {

}
