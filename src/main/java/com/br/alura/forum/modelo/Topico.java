package com.br.alura.forum.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.br.alura.forum.domain.topico.DadosCadastrarNovoTopico;
import com.br.alura.forum.domain.topico.DadosCadastroTopico;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name="Topico")
@Table(name = "tbl_topicos")

public class Topico {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensagem;
	
	
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	
	private StatusTopico statusTopico = StatusTopico.NAO_RESPONDIDO;
	
	@ManyToOne
	@JoinColumn(name="autor", referencedColumnName="id")
	private Usuario autor;
	
	@ManyToOne
	@JoinColumn(name="curso", referencedColumnName="id")
	private Curso curso;
	
	@OneToMany(mappedBy="topico")//criando relacionamento bidirecional. Aqui Topico é a entidade referecing e a referência é controlada pela coluna "topico"
	private List<Resposta> respostas = new ArrayList<>();
	
	public Topico() {
		
		super();
		autor = null;
		curso = null;
	}
	
	public Topico(String titulo, String mensagem, Curso curso, Usuario autor) {
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.curso = curso;
		this.autor = autor;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topico other = (Topico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public StatusTopico getStatus() {
		return statusTopico;
	}

	public void setStatus(StatusTopico status) {
		this.statusTopico = status;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

}
