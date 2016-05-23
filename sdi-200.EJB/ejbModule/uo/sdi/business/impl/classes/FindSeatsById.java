package uo.sdi.business.impl.classes;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;

public class FindSeatsById {

    public List<Seat> findById(Long id) {
	List<Seat> seatList = Factories.persistence.newSeatDao().findByUser(id);
	return seatList;
    }

}