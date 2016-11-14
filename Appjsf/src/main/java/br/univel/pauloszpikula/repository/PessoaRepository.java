package br.univel.pauloszpikula.repository;

import java.time.LocalDateTime;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.univel.pauloszpikula.model.PessoaModel;
import br.univel.pauloszpikula.repository.entity.PessoaEntity;
import br.univel.pauloszpikula.repository.entity.UsuarioEntity;
import br.univel.pauloszpikula.uteis.Uteis;

/**
 * Possui o método para validar a existência de uma pessoa cadastrada no banco de dados.
 * @author Paulo Szpikula 14/11/2016 20:30
 */
public class PessoaRepository {

	@Inject
	PessoaEntity pessoaEntity;

	EntityManager entityManager;

	/***
	 * Responsável por salvar uma nova pessoa.
	 * @param pessoaModel modelo de pessoa.
	 */
	public void SalvarNovoRegistro(PessoaModel pessoaModel){
		entityManager =  Uteis.JpaEntityManager();
		pessoaEntity = new PessoaEntity();
		pessoaEntity.setDataCadastro(LocalDateTime.now());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setOrigemCadastro(pessoaModel.getOrigemCadastro());
		pessoaEntity.setSexo(pessoaModel.getSexo());
		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, pessoaModel.getUsuarioModel().getCodigo());
		pessoaEntity.setUsuarioEntity(usuarioEntity);
		entityManager.persist(pessoaEntity);
	}
}
