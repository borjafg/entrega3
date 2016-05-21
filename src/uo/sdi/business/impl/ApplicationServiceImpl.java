package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.ApplicationService;
import uo.sdi.business.impl.classes.AcceptApplication;
import uo.sdi.business.impl.classes.ApplicateForTrip;
import uo.sdi.business.impl.classes.CancelApplication;
import uo.sdi.business.impl.classes.ExcludeUserFromTrip;
import uo.sdi.business.impl.classes.ListApplicationsPromoter;
import uo.sdi.business.impl.classes.ListApplicationsUser;
import uo.sdi.model.Application;
import uo.sdi.model.User;

public class ApplicationServiceImpl implements ApplicationService {
    @Override
    public void applicateForTrip(Long idTrip, Long idUser) throws Exception {
	new ApplicateForTrip().applicate(idTrip, idUser);
    }

    @Override
    public List<Application> listApplicationsUser(User user) throws Exception {
	return new ListApplicationsUser().list(user);
    }

    @Override
    public List<Application> listApplicationsPromoter(User user)
	    throws Exception {

	return new ListApplicationsPromoter().list(user);
    }

    @Override
    public void cancelApplication(Long idUser, Application application)
	    throws Exception {

	new CancelApplication().cancel(idUser, application);
    }

    @Override
    public void acceptApplication(Long idUser, Application application)
	    throws Exception {

	new AcceptApplication().accept(idUser, application);
    }

    @Override
    public void excludeUserFromTrip(Long idPromotor, Application application)
	    throws Exception {

	new ExcludeUserFromTrip().exclude(idPromotor, application);
    }
}