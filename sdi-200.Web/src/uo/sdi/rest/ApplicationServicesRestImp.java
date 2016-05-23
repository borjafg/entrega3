package uo.sdi.rest;

import uo.sdi.business.ApplicationService;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;

public class ApplicationServicesRestImp implements ApplicationServicesRest {
    ApplicationService service = Factories.services.getApplicationService();
    @Override
    public void confirmarPasajeros(Long idUser, Application application) {
	try {
	    service.acceptApplication(idUser, application);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

}
