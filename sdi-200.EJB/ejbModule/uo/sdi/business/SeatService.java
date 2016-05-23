package uo.sdi.business;

import java.util.List;

import uo.sdi.model.Seat;

public interface SeatService {

    List<Seat> findById(Long id);

}