package uo.sdi.presentation.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ErrorMessageManager {
	/**
	 * Registra un error relacionado con un componente para que sea mostrado en
	 * la vista correspondiente
	 * 
	 * @param context
	 *            se usara para almacenar el mensaje de error
	 * @param componentID
	 *            id del componente sobre el que hay que registrar el error
	 * @param messageKey
	 *            identificador que permite obtener el mensaje a mostrar de
	 *            entre los que hay en un fichero de properties
	 * @param errorSeverity
	 *            gravedad del error que se va a registrar
	 * 
	 */
	public static void register(FacesContext context, String componentID,
			String messageKey, FacesMessage.Severity errorSeverity) {

		context.addMessage(componentID, new FacesMessage(errorSeverity,
				"Error", Messages.getMessage(context, messageKey)));
	}
}