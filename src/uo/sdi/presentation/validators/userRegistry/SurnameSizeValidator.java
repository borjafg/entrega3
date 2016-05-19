package uo.sdi.presentation.validators.userRegistry;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import uo.sdi.presentation.util.Messages;


@FacesValidator("userRegistrySurnameSizeValidator")
public class SurnameSizeValidator implements Validator {
	private final int minimo = 3;
	private final int maximo = 25;

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String login = (String) value;

		if (minimo > login.length() || login.length() > maximo) {

			String error = Messages.getMessage(context,
					"userRegistryError_surnameSize");

			FacesMessage msg = new FacesMessage(error);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

			throw new ValidatorException(msg);
		}
	}
}