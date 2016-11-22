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
 * Controlador de consulta de pessoas.
 * @author Paulo Szpikula 14/11/2016 22:10
 */
@Named(value="consultarPessoaController")
@ViewScoped
public class ConsultarPessoaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject transient
	private PessoaModel pessoaModel;

	@Produces
	private List<PessoaModel> pessoas;

	@Inject transient
	private PessoaRepository pessoaRepository;

	/**
	 * Pega o valor da variável pessoas.
	 * @return uma List especificando a lista de pessoas.
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	/**
	 * Atribui valor a variável pessoas.
	 * @param pessoas uma lista de pessoas.
	 */
	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}

	/**
	 * Pega o valor da variável pessoaModel.
	 * @return uma PessoaModel especificando a pessoa.
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/**
	 * Atribui valor a variável pessoaModel.
	 * @param pessoaModel a pessoa.
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/***
	 * Carrega as pessoas na inicialização.
	 */
	@PostConstruct
	public void init(){
		this.pessoas = pessoaRepository.GetPessoas(); // Retorna as pessoas cadastradas
	}

	/***
	 * Carregar informações de uma pessoa a ser editada.
	 * @param pessoaModel o modelo pessoa.
	 */
	public void Editar(PessoaModel pessoaModel){
		pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1)); // Primeira letra do sexo
		this.pessoaModel = pessoaModel;
	}

	/***
	 * Atualiza o registro que foi alterado.
	 */
	public void AlterarRegistro(){
		this.pessoaRepository.AlterarRegistro(this.pessoaModel);
		this.init(); // Recarrega os registros
	}
}
