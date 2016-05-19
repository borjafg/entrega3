package uo.sdi.presentation.validators.userRegistry;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import uo.sdi.infrastructure.Factories;
import uo.sdi.presentation.util.Messages;


@FacesValidator("userRegistryLoginExistValidator")
public class LoginExistValidator implements Validator {
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (Factories.persistence.newUserDao().findByLogin((String) value) != null) {

			String error = Messages.getMessage(context, "userRegistryError_loginExist");

			FacesMessage msg = new FacesMessage(error);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

			throw new ValidatorException(msg);
		}
	}
}