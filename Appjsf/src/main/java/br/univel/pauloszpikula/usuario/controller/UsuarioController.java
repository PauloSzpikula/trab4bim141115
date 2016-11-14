package br.univel.pauloszpikula.usuario.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.univel.pauloszpikula.model.UsuarioModel;
import br.univel.pauloszpikula.repository.UsuarioRepository;
import br.univel.pauloszpikula.repository.entity.UsuarioEntity;
import br.univel.pauloszpikula.uteis.Uteis;

/**
 * Controlador de acesso de usuários no sistema.
 * @author Paulo Szpikula 08/11/2016 22:30
 */
@Named(value="usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioModel usuarioModel;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private UsuarioEntity usuarioEntity;

	/**
	 * Pegar o usuário.
	 * @return um UsuarioModel especificando o usuário.
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/**
	 * Setar um usuário para a variável usuarioModel.
	 * @param usuarioModel o usuário.
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	/**
	 * Levar o usuário logado para o sistema.
	 * @return um UsuarioModel especificando o usuário.
	 */
	public UsuarioModel GetUsuarioSession(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (UsuarioModel)facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}

	/**
	 * Finalizar a sessão do usuário e redirecioná-lo para a página de login.
	 * @return uma String especificando a página de login.
	 */
	public String Logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml?faces-redirect=true";
	}

	/**
	 * Realizar a autenticação do usuário.
	 * @return uma String especificando uma mesnsagem específica ao usuário.
	 */
	public String EfetuarLogin(){
		if (StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())){
			Uteis.Mensagem("Favor informar o login!");
			return null;
		}
		else if(StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())){
			Uteis.Mensagem("Favor informara senha!");
			return null;
		}
		else {
			usuarioEntity = usuarioRepository.ValidaUsuario(usuarioModel);
			if (usuarioEntity!= null){
				usuarioModel.setSenha(null);
				usuarioModel.setCodigo(usuarioEntity.getCodigo());

				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);
				return "sistema/home?faces-redirect=true";
			}
			else {
				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}
	}
}
