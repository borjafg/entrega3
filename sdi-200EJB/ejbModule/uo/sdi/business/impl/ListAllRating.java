package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Rating;

public class ListAllRating {

    public List<Rating> listAll() {
	return Factories.persistence.newRatingDao().findAll();
    }

}
