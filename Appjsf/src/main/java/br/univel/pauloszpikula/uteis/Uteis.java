package br.univel.pauloszpikula.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Responsável por implementar métodos úteis, como por exemplo mensagens para o usuário.
 * @author Paulo Szpikula 08/11/2016 19:50
 */
public class Uteis {

	/**
	 * Responsável por recuperar o EntityManger que será criado no JPAFilter.
	 * @return um EntityManager especificando seus atributos.
	 */
	public static EntityManager JpaEntityManager(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request  = (HttpServletRequest)externalContext.getRequest();
		return (EntityManager)request.getAttribute("entityManager");
	}

	/**
	 * Responsável por implementar a exibição de mensagens ao usuário.
	 * @param mensagem mostra uma mensagem de alerta.
	 */
	public static void Mensagem(String mensagem){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage("Alerta",mensagem));
	}

	/**
	 * Responsável por implementar a exibição de mensagens ao usuário.
	 * @param mensagem mostra uma mensagem de atenção.
	 */
	public static void MensagemAtencao(String mensagem){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", mensagem));
	}

	/**
	 * Responsável por implementar a exibição de mensagens ao usuário.
	 * @param mensagem mostra uma mensagem de informação.
	 */
	public static void MensagemInfo(String mensagem){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
	}
}