package uo.sdi.business.impl;

import uo.sdi.business.ApplicationService;
import uo.sdi.business.impl.classes.ApplicateForTrip;

public class ApplicationServiceImpl implements ApplicationService {
	@Override
	public void applicateForTrip(Long idTrip, Long idUser) throws Exception {
		new ApplicateForTrip().applicate(idTrip, idUser);
	}
}