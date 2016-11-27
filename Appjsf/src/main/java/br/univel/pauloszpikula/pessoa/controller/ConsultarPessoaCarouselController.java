package br.univel.pauloszpikula.pessoa.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.univel.pauloszpikula.model.PessoaModel;
import br.univel.pauloszpikula.repository.PessoaRepository;

/**
 * Controlador de consulta carousel para pessoa.
 * @author Paulo Szpikula 27/11/2016 16:46
 */
@Named(value="consultarPessoaCarouselController")
@ViewScoped
public class ConsultarPessoaCarouselController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject transient
	private PessoaRepository pessoaRepository;

	@Produces
	private List<PessoaModel> pessoas;

	/**
	 * Responasável por exibir a lista de pessoas.
	 * @return pessoas lista de pessoas.
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	/**
	 * Responasável por consultar pessoas cadastradas .
	 */
	@PostConstruct
	private void init(){
		this.pessoas = pessoaRepository.GetPessoas();
	}
}