package uo.sdi.presentation.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import uo.sdi.model.User;
import uo.sdi.presentation.BeanUser;

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
		String redireccionPrivada = "listTripsRegistered";

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
				|| url.contains("registrarViaje");

		return proteger;
	}

	private boolean url_paraPublico(String url) {
		boolean publica = url.contains("listaViajes_Publico")
				|| url.contains("login") || url.contains("registro");

		return publica;
	}

	private boolean estaRegistrado() {
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(
				context, "#{user}", BeanUser.class).getUser();

		return user != null;
	}
}