package uo.sdi.presentation.validators.userRegistry;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import uo.sdi.presentation.util.Messages;


@FacesValidator("userRegistryLoginSizeValidator")
public class LoginSizeValidator implements Validator {
	private final int minimo = 5;
	private final int maximo = 20;

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String login = (String) value;

		if (minimo > login.length() || login.length() > maximo) {

			String error = Messages.getMessage(context,
					"userRegistryError_loginSize");

			FacesMessage msg = new FacesMessage(error);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

			throw new ValidatorException(msg);
		}
	}
}