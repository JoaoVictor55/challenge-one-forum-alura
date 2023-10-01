package com.br.alura.forum.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.alura.forum.domain.usuario.DadosAtualizacaoUsuarios;
import com.br.alura.forum.domain.usuario.DadosCadastraisUsuario;
import com.br.alura.forum.domain.usuario.DadosDetalhamentoUsuario;
import com.br.alura.forum.domain.usuario.DadosListagemUsuario;
import com.br.alura.forum.domain.usuario.UsuarioRepository;
import com.br.alura.forum.modelo.Usuario;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastraisUsuario dados, UriComponentsBuilder uriBuilder) {
		
		Usuario novoUsuario = new Usuario(dados);
		
		repository.save(novoUsuario);
		
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(novoUsuario.getId()).toUri();
		
		DadosDetalhamentoUsuario detalhes = new DadosDetalhamentoUsuario(novoUsuario);
		
		return ResponseEntity.created(uri).body(detalhes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		
		return ResponseEntity.ok(new DadosDetalhamentoUsuario(repository.getReferenceById(id)));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity detelar(@PathVariable Long id) {
		
		repository.getReferenceById(id).excluir();
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUsuarios dados) {
		
		Usuario usuario = repository.getReferenceById(dados.id());
		
		usuario.atualizar(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
	}
	
	@GetMapping
	public ResponseEntity <Page<DadosListagemUsuario>> listar(@PageableDefault(size = 5, page=0, sort= {"nome"}) Pageable paginacao){
		
		Page<DadosListagemUsuario> page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuario::new);
		
		return ResponseEntity.ok(page);
	}
}
