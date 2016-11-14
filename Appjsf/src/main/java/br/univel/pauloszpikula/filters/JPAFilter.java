package br.univel.pauloszpikula.filters;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Toda vez que for realizado uma requisição para o Faces Servlet, esse filter será chamado.
 * @author Paulo Szpikula 08/11/2016 19:17
 */
@WebFilter(servletNames ={ "Faces Servlet" })
public class JPAFilter implements Filter {

	private EntityManagerFactory entityManagerFactory;
	private String persistence_unit_name = "unit_app";


	/**
	 * Construtor de JPAFilter vazio.
	 */
    public JPAFilter() {
    }

	/**
	 * Responsável por fechar o entityManagerFactory.
	 */
	public void destroy() {
		this.entityManagerFactory.close();
	}

	/**
	 * Responsável por se comunicar com o Servlet.
	 * @param request a requisição
	 * @param response para quem será respondido
	 * @param chain inicia o faces servlet.
	 * @throws uma exceção do Servlet.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		EntityManager entityManager =  this.entityManagerFactory.createEntityManager(); // Criando um entitymanager
		request.setAttribute("entityManager", entityManager); // Adicionando ele na requisição
		entityManager.getTransaction().begin(); // Iniciando uma transação
		chain.doFilter(request, response); // Iniciando faces servlet

		try {
			entityManager.getTransaction().commit(); // Se não tiver erro na operação ele executa o commit
		} catch (Exception e) {
			entityManager.getTransaction().rollback(); // Se ocorrer um erro na operação será executado o rollback
		}

		finally {
			entityManager.close(); // Depois de dar o commit ou rollback ele finaliza o entitymanager
		}
	}

	/**
	 * Responsável por criar o entityManagerFactory com os parâmetros definidos no persistence.xml
	 * @param fConfig um objeto de configuração de filtro usado por um contêiner de servlet para passar informações para um filtro durante a inicialização.
	 * @throws uma exceção do Servlet.
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name);
	}
}
