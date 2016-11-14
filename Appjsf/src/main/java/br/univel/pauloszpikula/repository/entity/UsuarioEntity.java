package br.univel.pauloszpikula.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entidade responsável por persistir na tabela de usuários do banco de dados.
 * @author Paulo Szpikula 08/11/2016 19:53
 */
@Table(name="tb_usuario")
@Entity
@NamedQuery(name = "UsuarioEntity.findUser",
		    query= "SELECT u FROM UsuarioEntity u WHERE u.usuario = :usuario AND u.senha = :senha")
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_usuario")
	private String codigo;

	@Column(name="ds_login")
	private String usuario;

	@Column(name="ds_senha")
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