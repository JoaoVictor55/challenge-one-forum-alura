package com.br.alura.forum.domain.topico;

import java.time.LocalDateTime;

import com.br.alura.forum.modelo.StatusTopico;
import com.br.alura.forum.modelo.Topico;

public record DadosDetalhamentoTopico(Long id, String mensagem, LocalDateTime dataCriacao, StatusTopico status, Long autor, Long curso) {

	public DadosDetalhamentoTopico(Topico topico) {
		
		this(topico.getId(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor().getId(), topico.getCurso().getId());
		
		
		
		
	}
}
