package uo.sdi.presentation.validators.tripRegistry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import uo.sdi.presentation.util.Messages;

@FacesValidator("tripRegistryPostCodeValidator")
public class PostCodeValidator implements Validator {
	private final String FORMATO = "^([1-9]{2}|[0-9][1-9]|[1-9][0-9])[0-9]{3}$";

	private Pattern pattern;
	private Matcher matcher;

	public PostCodeValidator() {
		pattern = Pattern.compile(FORMATO);
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		matcher = pattern.matcher(value.toString());

		if (!matcher.matches()) {

			String error = Messages.getMessage(context,
					"tripRegistryError_PostCode");

			FacesMessage msg = new FacesMessage(error);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

			throw new ValidatorException(msg);
		}
	}
}