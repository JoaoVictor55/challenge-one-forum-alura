package com.br.alura.forum.domain.topico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.alura.forum.domain.usuario.UsuarioRepository;
import com.br.alura.forum.modelo.Curso;
import com.br.alura.forum.modelo.Topico;
import com.br.alura.forum.modelo.Usuario;
import com.br.alura.forum.repository.CursoRepository;
import com.br.alura.forum.repository.RespostaRepository;
import com.br.alura.forum.repository.TopicosRepository;

import jakarta.validation.Valid;

@Service
public class GerenciarTopicos {

	@Autowired
	private TopicosRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RespostaRepository respostaRepository;
	
	
	public DadosDetalhamentoTopico cadastrar(DadosCadastroTopico dados) {
	
		Curso curso = cursoRepository.getReferenceById(dados.curso());
		Usuario autor = usuarioRepository.getReferenceById(dados.autor());
		
		Topico topico = new Topico(dados.titulo(), dados.mensagem(), curso, autor);
		
		topicoRepository.save(topico);
		
		DadosDetalhamentoTopico topicoCriado =  new DadosDetalhamentoTopico(topico);
		
		return topicoCriado;
	}
	
	public void deletar(Long id) {
		
		topicoRepository.deleteById(id);
	}

	public Page<DadosListagemTopico> findAll(Pageable paginacao) {
		
		return topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
	}

	public Page<DadosListagemTopico> findTopicoPorCurso(Pageable paginacao, String curso) {
		
		return topicoRepository.findTopicoPorCurso(paginacao, curso).map(DadosListagemTopico::new);
	}

	public Topico getReferenceById(Long id) {
		 
		return topicoRepository.getReferenceById(id);
		
	}

	public DadosDetalhamentoTopico atualizar(@Valid DadosAtualizarTopico dados) {
		
		//Curso curso = ;
		
		Topico topico = topicoRepository.getReferenceById(dados.id());
		
		if(dados.curso() != null) {
			
			topico.setCurso(cursoRepository.getReferenceById(dados.curso()));
		}
			
		if(dados.mensagem() != null) {
			
			topico.setMensagem(dados.mensagem());
		}
		
		if(dados.titulo() != null) {
			
			topico.setTitulo(dados.titulo());
		}
		
		
		
		return new DadosDetalhamentoTopico(topico);
	}
	

}
