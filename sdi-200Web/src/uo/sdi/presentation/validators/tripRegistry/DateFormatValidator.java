package uo.sdi.presentation.validators.tripRegistry;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import uo.sdi.presentation.util.Messages;
import uo.sdi.presentation.util.TypeManager;

@FacesValidator("tripRegistryDateFormatValidator")
public class DateFormatValidator implements Validator {
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Date fecha = TypeManager.stringToDate((String) value,
				"dd/MM/yyyy HH:mm");

		if (fecha == null) {

			String error = Messages.getMessage(context,
					"tripRegistryError_DateFormat");

			FacesMessage msg = new FacesMessage(error);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

			throw new ValidatorException(msg);
		}
	}
}