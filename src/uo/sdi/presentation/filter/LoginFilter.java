package uo.sdi.presentation.filter;

import java.io.IOException;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.presentation.util.UserManager;
import alb.util.log.Log;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, description = "Filtro de seguridad", urlPatterns = { "/*" }, initParams = {
		@WebInitParam(name = "login", value = "/login.xhtml", description = "Pagina de login"),

		@WebInitParam(name = "listaViajes", value = "/listaViajes_Registrado.xhtml", description = "Listado de viajes") })
public class LoginFilter implements Filter {
	// Necesitamos acceder a los parámetros de inicialización en
	// el método doFilter por lo que necesitamos la variable
	// config que se inicializará en init()
	//
	FilterConfig config = null;

	/**
	 * Default constructor.
	 * 
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#init(FilterConfig)
	 * 
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// Iniciamos la variable de instancia config
		config = fConfig;
	}

	/**
	 * @see Filter#destroy()
	 * 
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// Si no es petición HTTP nada que hacer
		//
		if (!(request instanceof HttpServletRequest)) {
			chain.doFilter(request, response);
			return;
		}

		/* Posibles redirecciones */

		String loginForm = config.getInitParameter("login");
		String tripList = config.getInitParameter("listaViajes");

		/* Peticion, respuesta, sesion y URL */

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession sesion = req.getSession();

		String urlStr = req.getRequestURL().toString().toLowerCase();

		/* Empieza el filtrado */

		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		boolean estaLogueado = UserManager.comprobarUsuarioRegistrado(session) != null;

		/* =============================== */
		/* ======= Usuario publico ======= */
		/* =============================== */

		if (!estaLogueado) {
			if (necesitaLoggin(urlStr)) {
				Log.debug("No esta logueado. Se necesita login para acceder a "
						+ "[%s]", urlStr);

				res.sendRedirect(req.getContextPath() + loginForm);

				return;
			}

			else {
				chain.doFilter(request, response);

				return;
			}
		}

		/* ================================== */
		/* ======= Usuario registrado ======= */
		/* ================================== */

		else {
			if (URL_ParaNoRegistrados(urlStr)) {
				Log.debug("Esta logueado. No puede acceder al recurso publico "
						+ "[%s]", urlStr);

				res.sendRedirect(req.getContextPath() + tripList);

				return;
			}

			else {
				chain.doFilter(request, response);

				return;
			}
		}
	}

	private boolean necesitaLoggin(String url) {
		boolean proteger = url.endsWith("/listapasajeros.xhtml")
				|| url.endsWith("/listasolicitudes_promotor.xhtml")
				|| url.endsWith("/listasolicitudes_usuario.xhtml")
				|| url.endsWith("/listaviajes_registrado.xhtml")
				|| url.endsWith("/listaViajes_promotor.xhtml")
				|| url.endsWith("/registrarviaje.xhtml");

		return proteger;
	}

	private boolean URL_ParaNoRegistrados(String url) {
		boolean publica = url.endsWith("/listaviajes_publico.xhtml")
				|| url.endsWith("/login.xhtml")
				|| url.endsWith("/registro.xhtml");

		return publica;
	}
}