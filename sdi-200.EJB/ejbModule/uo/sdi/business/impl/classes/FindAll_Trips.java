package uo.sdi.business.impl.classes;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class FindAll_Trips {

    public List<Trip> findAll() throws Exception {
	try {
	    return Factories.persistence.newTripDao().findAll();
	}

	catch (Exception excep) {
	    
	}
    }
}