package br.univel.pauloszpikula.pessoa.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.univel.pauloszpikula.model.PessoaModel;
import br.univel.pauloszpikula.repository.PessoaRepository;
import br.univel.pauloszpikula.usuario.controller.UsuarioController;
import br.univel.pauloszpikula.uteis.Uteis;

/**
 * Controlador de pessoa.
 * @author Paulo Szpikula 14/11/2016 20:33
 */
@Named(value="cadastrarPessoaController")
@RequestScoped
public class CadastrarPessoaController {

	@Inject
	PessoaModel pessoaModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	PessoaRepository pessoaRepository;

	/**
	 * Pegar a pessoa.
	 * @return uma PessoaModel especificando a pessoa.
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/**
	 * Setar uma pessoa para a variável pessoaModel.
	 * @param pessoaModel a pessoa.
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 * Salva um novo registro via imput.
	 */
	public void SalvarNovaPessoa(){
		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
		pessoaModel.setOrigemCadastro("I"); // Informa que o cadastro foi feito via imput
		pessoaRepository.SalvarNovoRegistro(this.pessoaModel);
		this.pessoaModel = null;
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
	}
}
