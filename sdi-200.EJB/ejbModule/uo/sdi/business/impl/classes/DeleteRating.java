package uo.sdi.business.impl.classes;

import uo.sdi.infrastructure.Factories;

public class DeleteRating {

    public void delete(Long id) {
	try {
	    Factories.persistence.newRatingDao().delete(id);
	}

	catch (Exception excep) {

	}
    }
}