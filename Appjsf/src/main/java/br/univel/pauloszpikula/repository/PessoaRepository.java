package br.univel.pauloszpikula.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.univel.pauloszpikula.model.PessoaModel;
import br.univel.pauloszpikula.model.UsuarioModel;
import br.univel.pauloszpikula.repository.entity.PessoaEntity;
import br.univel.pauloszpikula.repository.entity.UsuarioEntity;
import br.univel.pauloszpikula.uteis.Uteis;

/**
 * Possui o método para salvar uma pessoa e consultar pessoas cadastradas no banco de dados.
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


	/***
	 * Responsável por consultar as pessoas do banco de dados.
	 * @return uma List de pessoas.
	 */
	public List<PessoaModel> GetPessoas(){
		List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>();
		entityManager =  Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");
		@SuppressWarnings("unchecked")
		Collection<PessoaEntity> pessoasEntity = (Collection<PessoaEntity>)query.getResultList();
		PessoaModel pessoaModel = null;
		for (PessoaEntity pessoaEntity : pessoasEntity) {
			pessoaModel = new PessoaModel();
			pessoaModel.setCodigo(pessoaEntity.getCodigo());
			pessoaModel.setDataCadastro(pessoaEntity.getDataCadastro());
			pessoaModel.setEmail(pessoaEntity.getEmail());
			pessoaModel.setEndereco(pessoaEntity.getEndereco());
			pessoaModel.setNome(pessoaEntity.getNome());
			if (pessoaEntity.getOrigemCadastro().equals("X"))
				pessoaModel.setOrigemCadastro("XML");
			else
				pessoaModel.setOrigemCadastro("INPUT");
			if (pessoaEntity.getSexo().equals("M"))
				pessoaModel.setSexo("Masculino");
			else
				pessoaModel.setSexo("Feminino");
			UsuarioEntity usuarioEntity =  pessoaEntity.getUsuarioEntity();
			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());
			pessoaModel.setUsuarioModel(usuarioModel);
			pessoasModel.add(pessoaModel);
		}
		return pessoasModel;
	}

	/***
	 * Consulta uma pessoa cadastrada por seu código.
	 * @param codigo o código.
	 * @return o objeto pessoa localizado.
	 */
	private PessoaEntity GetPessoa(int codigo){
		entityManager =  Uteis.JpaEntityManager();
		return entityManager.find(PessoaEntity.class, codigo);
	}

	/***
	 * Altera um registro cadastrado no banco.
	 * @param pessoaModel o modelo de pessoa.
	 */
	public void AlterarRegistro(PessoaModel pessoaModel){
		entityManager =  Uteis.JpaEntityManager();

		PessoaEntity pessoaEntity = this.GetPessoa(pessoaModel.getCodigo());

		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setSexo(pessoaModel.getSexo());
		entityManager.merge(pessoaEntity);
	}
}
