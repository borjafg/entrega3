package uo.sdi.presentation.validators.userRegistry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import uo.sdi.presentation.util.Messages;


@FacesValidator("userRegistryEmailFormatValidator")
public class EmailFormatValidator implements Validator {
	private final String FORMATO = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Pattern pattern;
	private Matcher matcher;


	public EmailFormatValidator() {
		pattern = Pattern.compile(FORMATO);
	}


	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		matcher = pattern.matcher(value.toString());

		if (!matcher.matches()) {

			String error = Messages.getMessage(context,
					"userRegistryError_emailFormat");

			FacesMessage msg = new FacesMessage(error);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

			throw new ValidatorException(msg);
		}
	}
}