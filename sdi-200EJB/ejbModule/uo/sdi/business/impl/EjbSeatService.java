package uo.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.impl.classes.FindSeatsById;
import uo.sdi.model.Seat;

@Stateless
@WebService(name = "SeatService")
public class EjbSeatService implements LocalSeatService, RemoteSeatService {

    @Override
    public List<Seat> findById(Long id) {
	return new FindSeatsById().findById(id);
    }

}