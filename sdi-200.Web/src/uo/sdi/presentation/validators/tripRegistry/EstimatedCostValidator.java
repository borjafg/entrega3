package uo.sdi.presentation.validators.tripRegistry;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import uo.sdi.presentation.util.Messages;
import uo.sdi.presentation.util.TypeManager;

@FacesValidator("tripRegistryEstimatedCostValidator")
public class EstimatedCostValidator implements Validator {
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String entrada = (String) value;

		// Campo opcional por lo que puede no tener valor
		if (entrada == null || entrada.equals("")) {
			return;
		}

		// Si tiene valor hay que validarlo

		String[] coordenadas = entrada.split(",");

		if (coordenadas.length != 2) {
			String error = Messages.getMessage(context,
					"tripRegistryError_Coordinates");

			FacesMessage msg = new FacesMessage(error);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

			throw new ValidatorException(msg);
		}

		// Coordenadas.length == 2
		else {
			try {
				Double latitud = TypeManager.stringToDouble(coordenadas[0]);
				Double longitud = TypeManager.stringToDouble(coordenadas[1]);

				if (latitud == null || longitud == null) {
					throw new Exception();
				}

				if (latitud < -90 || latitud > 90) {
					throw new Exception();
				}

				if (longitud < -180 || longitud > 180) {
					throw new Exception();
				}
			}

			catch (Exception excep) {
				String error = Messages.getMessage(context,
						"tripRegistryError_Coordinates");

				FacesMessage msg = new FacesMessage(error);
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);

				throw new ValidatorException(msg);
			}
		}
	}
}