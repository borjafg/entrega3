package uo.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.impl.classes.DeleteRating;
import uo.sdi.model.Rating;

@Stateless
@WebService(name = "RatingService")
public class EjbRatingService implements LocalRatingService, RemoteRatingService {

    @Override
    public void deleteRating(Long id) {
	new DeleteRating().delete(id);
	
    }

    @Override
    public List<Rating> listAllRating() {
	return new ListAllRating().listAll();
    }

   

}