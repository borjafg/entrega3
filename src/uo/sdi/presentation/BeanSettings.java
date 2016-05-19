package uo.sdi.presentation;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import alb.util.log.Log;

@ManagedBean(name = "settings")
@SessionScoped
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = -1445466L;

	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private Locale locale = new Locale("es");

	public Locale getLocale() {
		return (locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;

		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);

			Log.debug("Se ha cambiado el idioma a [%s]",
					locale.getDisplayLanguage());
		}

		catch (Exception ex) {
			Log.error("No se ha podido cambiar el idioma a español. El idioma "
					+ "actual es [%s]", locale.getDisplayLanguage());
		}
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;

		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);

			Log.debug("Se ha cambiado el idioma a [%s]",
					locale.getDisplayLanguage());
		}

		catch (Exception ex) {
			Log.error("No se ha podido cambiar el idioma a inglés. El idioma "
					+ "actual es [%s]", locale.getDisplayLanguage());
		}
	}
}