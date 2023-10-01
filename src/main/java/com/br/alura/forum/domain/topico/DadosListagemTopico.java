package com.br.alura.forum.domain.topico;

import com.br.alura.forum.modelo.StatusTopico;
import com.br.alura.forum.modelo.Topico;

public record DadosListagemTopico(Long id, String mensagem, StatusTopico status, Long autor, Long curso) {
	
	
	public DadosListagemTopico(Topico topico) {
		
		this(topico.getId(), topico.getMensagem(), topico.getStatus(), topico.getAutor().getId(), topico.getCurso().getId());
	}
	

}
