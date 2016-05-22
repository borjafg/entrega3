package uo.sdi.business;

import java.util.List;

import uo.sdi.model.Rating;

public interface RatingService {
    public void deleteRating(Long id);
    public List<Rating> listAllRating();
}
