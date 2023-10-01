package com.br.alura.forum.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.alura.forum.domain.topico.DadosCadastroTopico;
import com.br.alura.forum.domain.topico.DadosDetalhamentoTopico;
import com.br.alura.forum.domain.topico.DadosListagemTopico;
import com.br.alura.forum.domain.topico.GerenciarTopicos;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired
	private GerenciarTopicos gerenTopicos;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder ) {
		
		DadosDetalhamentoTopico detalhes = gerenTopicos.cadastrar(dados);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(detalhes.id()).toUri();
		
		return ResponseEntity.created(uri).body(detalhes);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletar(@PathVariable Long id) {
		
		gerenTopicos.deletar(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity <Page<DadosListagemTopico>> listar(@PageableDefault(size=10, sort= {"id"}, direction=Sort.Direction.ASC) Pageable paginacao){
		
		Page<DadosListagemTopico> page = gerenTopicos.findAll(paginacao);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/detalhes/{id}")
	public ResponseEntity buscar(@PathVariable Long id) {
		
		DadosDetalhamentoTopico detalhes = new  DadosDetalhamentoTopico(gerenTopicos.getReferenceById(id));
		
		return ResponseEntity.ok(detalhes);
	}
	
	@GetMapping("/{curso}")
	public ResponseEntity <Page<DadosListagemTopico>> listar(@PageableDefault(size=10, sort= {"id"}, direction=Sort.Direction.ASC) Pageable paginacao,
			@PathVariable String curso){
		
		System.out.println(curso);
		Page<DadosListagemTopico> page = gerenTopicos.findTopicoPorCurso(paginacao, curso);
		
		return ResponseEntity.ok(page);
	}
}
