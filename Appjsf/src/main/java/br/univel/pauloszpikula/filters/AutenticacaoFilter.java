package br.univel.pauloszpikula.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.univel.pauloszpikula.model.UsuarioModel;

/**
 * Filtro que valida se o usuário está logado par acessar as páginas dentro da pasta sistema.
 * @author Paulo Szpikula 08/11/2016 22:42
 */
@WebFilter("/sistema/*")
public class AutenticacaoFilter implements Filter {

	/**
	 * Construtor de AutenticacaoFilter vazio.
	 */
    public AutenticacaoFilter() {
    }

	/**
	 * Responsável por fechar o entityManagerFactory.
	 */
	public void destroy() {
	}

	/**
	 * Responsável por se comunicar com o Servlet.
	 * @param request a requisição
	 * @param response para quem será respondido
	 * @param chain inicia o faces servlet.
	 * @throws uma exceção do Servlet.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpSession httpSession = ((HttpServletRequest) request).getSession();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if (httpServletRequest.getRequestURI().indexOf("index.xhtml") <= -1){
			UsuarioModel usuarioModel =(UsuarioModel) httpSession.getAttribute("usuarioAutenticado");
			if (usuarioModel == null){
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+ "/index.xhtml");
			}
			else {
				chain.doFilter(request, response);
			}
		}
		else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * Responsável por criar o entityManagerFactory com os parâmetros definidos no persistence.xml
	 * @param fConfig um objeto de configuração de filtro usado por um contêiner de servlet para passar informações para um filtro durante a inicialização.
	 * @throws uma exceção do Servlet.
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}