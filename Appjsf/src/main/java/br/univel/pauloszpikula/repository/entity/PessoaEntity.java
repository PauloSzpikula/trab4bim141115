package br.univel.pauloszpikula.repository.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entidade responsável por persistir na tabela de pessoa do banco de dados.
 * @author Paulo Szpikula 14/11/2016 20:22
 */
@Entity
@Table(name="tb_pessoa")
@NamedQueries({
	@NamedQuery(name = "PessoaEntity.findAll",query= "SELECT p FROM PessoaEntity p"),
	@NamedQuery(name="PessoaEntity.GroupByOrigemCadastro",query= "SELECT p.origemCadastro, count(p) as total FROM PessoaEntity p GROUP By p.origemCadastro")
})
public class PessoaEntity {

	@Id
	@GeneratedValue
	@Column(name = "id_pessoa")
	private Integer codigo;

	@Column(name = "nm_pessoa")
	private String  nome;

	@Column(name = "fl_sexo")
	private String  sexo;

	@Column(name = "dt_cadastro")
	private LocalDateTime	dataCadastro;

	@Column(name = "ds_email")
	private String  email;

	@Column(name = "ds_endereco")
	private String  endereco;

	@Column(name = "fl_origemCadastro")
	private String  origemCadastro;

	@OneToOne
	@JoinColumn(name="id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;

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
	 * Pega o valor da variável usuarioEntity.
	 * @return um UsuarioEntity especificando a entidade do usuário.
	 */
	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	/**
	 * Atribui valor a variável usuarioEntity.
	 * @param usuarioEntity a entidade do usuário.
	 */
	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}
}