package uo.sdi.business;

import java.util.List;

import uo.sdi.model.Seat;

public interface SeatService {

    public List<Seat> findBy_userId(Long id) throws Exception;
}