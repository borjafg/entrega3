package uo.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.impl.classes.FindSeatsBy_userId;
import uo.sdi.model.Seat;

@Stateless
@WebService(name = "SeatService")
public class EjbSeatService implements LocalSeatService, RemoteSeatService {

    @Override
    public List<Seat> findBy_userId(Long id) throws Exception {
	return new FindSeatsBy_userId().find(id);
    }

}