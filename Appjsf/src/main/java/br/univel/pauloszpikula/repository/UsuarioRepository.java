package br.univel.pauloszpikula.repository;

import java.io.Serializable;
import javax.persistence.Query;
import br.univel.pauloszpikula.model.UsuarioModel;
import br.univel.pauloszpikula.repository.entity.UsuarioEntity;
import br.univel.pauloszpikula.uteis.Uteis;

/**
 * Possui o método para validar a existência de um usuário cadastrado no banco de dados.
 * @author Paulo Szpikula 08/11/2016 22:20
 */
public class UsuarioRepository implements Serializable {


	private static final long serialVersionUID = 1L;

	/**
	 * Responsável por validar a existência de um usuário cadastrado no banco.
	 * @param usuarioModel o usuário buscado.
	 * @return um UsuarioEntity especificando o usuário, ou nulo caso o usuário não exista.
	 */
	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel) {

		try {
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser"); // Pesquisa que será executada

			query.setParameter("usuario", usuarioModel.getUsuario()); // Parâmetros da pesquisa
			query.setParameter("senha", usuarioModel.getSenha()); // Parâmetros da pesquisa

			return (UsuarioEntity)query.getSingleResult(); // Retorna o usuário localizado
		} catch (Exception e) {
			return null;
		}
	}
}