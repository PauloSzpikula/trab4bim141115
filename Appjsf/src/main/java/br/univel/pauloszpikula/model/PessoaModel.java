package br.univel.pauloszpikula.model;

import java.time.LocalDateTime;

/**
 * Modelo de pessoa.
 * @author Paulo Szpikula 14/11/2016 20:24
 */
public class PessoaModel {

	private Integer codigo;
	private String nome;
	private String sexo;
	private LocalDateTime dataCadastro;
	private String email;
	private String endereco;
	private String origemCadastro;
	private UsuarioModel usuarioModel;

	/**
	 * Pega o valor da variável codigo.
	 * @return um Integer especificando o código.
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Atribui valor a variável codigo.
	 * @param codigo o código.
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Pega o valor da variável nome.
	 * @return uma String especificando o nome.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Atribui valor a variável nome.
	 * @param nome o nome.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Pega o valor da variável sexo.
	 * @return uma String especificando o sexo.
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Atribui valor a variável sexo.
	 * @param sexo o sexo.
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Pega o valor da variável dataCadastro.
	 * @return uma LocalDateTime especificando uma data.
	 */
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * Atribui valor a variável dataCadastro.
	 * @param dataCadastro a data de cadastro.
	 */
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * Pega o valor da variável email.
	 * @return uma String especificando o e-mail.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Atribui valor a variável email.
	 * @param email o e-mail.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Pega o valor da variável endereco.
	 * @return uma String especificando o endereço.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Atribui valor a variável endereco.
	 * @param endereco o endereço.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * Pega o valor da variável origemCadastro.
	 * @return uma String especificando a origem do cadastro.
	 */
	public String getOrigemCadastro() {
		return origemCadastro;
	}

	/**
	 * Atribui valor a variável origemCadastro.
	 * @param origemCadastro a origem do cadastro.
	 */
	public void setOrigemCadastro(String origemCadastro) {
		this.origemCadastro = origemCadastro;
	}

	/**
	 * Pega o valor da variável UsuarioModel.
	 * @return um UsuarioModel especificando o modelo de usuário.
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/**
	 * Atribui valor a variável usuarioModel.
	 * @param usuarioModel o modelo de usuário.
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
}
