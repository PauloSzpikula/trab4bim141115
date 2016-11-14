package br.univel.pauloszpikula.model;

import java.io.Serializable;

/**
 * Modelo de usuários.
 * @author Paulo Szpikula 08/11/2016 22:12
 */
public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo;
	private String usuario;
	private String senha;

	/**
	 * Pega o valor da variável codigo.
	 * @return uma String especificando o código.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Atribui valor a variável codigo.
	 * @param codigo o código.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Pega o valor da variável usuario.
	 * @return uma String especificando o usuário.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Atribui valor a variável usuario.
	 * @param usuario o usuário.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Pega o valor da variável senha.
	 * @return uma String especificando a senha.
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Atribui valor a variável senha.
	 * @param senha a senha.
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
