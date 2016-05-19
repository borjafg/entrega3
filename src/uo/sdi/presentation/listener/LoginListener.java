package uo.sdi.presentation.listener;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
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

import uo.sdi.presentation.BeanLogin;
import uo.sdi.presentation.util.UserManager;
import alb.util.log.Log;

/**
 * Filtra las peticiones para evitar que el usuario pueda acceder a un recurso
 * al que no esta autorizado.
 * 
 */
public class LoginListener implements PhaseListener {
	private static final long serialVersionUID = -27847482123L;

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterPhase(PhaseEvent event) {
		/* ============================== */
		/* === Posibles redirecciones === */
		/* ============================== */

		String redireccionPublica = "login";
		String redireccionPrivada = "listaViajes";

		/* =================================================== */
		/* === Id de la vista y navegacion a otras paginas === */
		/* =================================================== */

		FacesContext fc = event.getFacesContext();
		String view = fc.getViewRoot().getViewId();
		NavigationHandler nav = fc.getApplication().getNavigationHandler();

		/* =================================== */
		/* === Realizar las comprobaciones === */
		/* =================================== */

		if (url_paraRegistrado(view)) { // URL para usuarios registrados
			if (!estaRegistrado()) { // No esta registrado
				nav.handleNavigation(fc, null, redireccionPublica);

				return;
			}
		}

		if (url_paraPublico(view)) { // URL para usuarios publicos
			if (estaRegistrado()) { // Esta registrado
				nav.handleNavigation(fc, null, redireccionPrivada);

				return;
			}
		}
	}

	/* =================================================================== */
	/* === Métodos necesarios para comprobar la validez de la peticion === */
	/* =================================================================== */

	private boolean url_paraRegistrado(String url) {
		boolean proteger = url.contains("listaPasajeros")
				|| url.contains("listaSolicitudes_Promotor")
				|| url.contains("listaSolicitudes_Usuario")
				|| url.contains("listaViajes_Promotor")
				|| url.contains("listaViajes_Registrado")
				|| url.contains("registrarViaje.xhtml");

		return proteger;
	}

	private boolean url_paraPublico(String url) {
		boolean publica = url.contains("listaViajes_Publico")
				|| url.contains("login") || url.contains("registro");

		return publica;
	}

	private boolean estaRegistrado() {
		FacesContext context = FacesContext.getCurrentInstance();
		BeanLogin user = context.getApplication().evaluateExpressionGet(
				context, "#{user}", BeanLogin.class);

		return user != null;
	}
}