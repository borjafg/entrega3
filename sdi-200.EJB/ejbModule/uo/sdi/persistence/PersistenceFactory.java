package uo.sdi.persistence;

import uo.sdi.persistence.impl.ApplicationDaoJdbcImpl;
import uo.sdi.persistence.impl.RatingDaoJdbcImpl;
import uo.sdi.persistence.impl.SeatDaoJdbcImpl;
import uo.sdi.persistence.impl.TransactionJdbcImpl;
import uo.sdi.persistence.impl.TripDaoJdbcImpl;
import uo.sdi.persistence.impl.UserDaoJdbcImpl;


public class PersistenceFactory
{
	public Transaction newTransaction() {
		return new TransactionJdbcImpl();
	}
	
	public RatingDao newRatingDao() {
		return new RatingDaoJdbcImpl();
	}

	public UserDao newUserDao() {
		return new UserDaoJdbcImpl();
	}

	public TripDao newTripDao() {
		return new TripDaoJdbcImpl();
	}

	public SeatDao newSeatDao() {
		return new SeatDaoJdbcImpl();
	}

	public ApplicationDao newApplicationDao() {
		return new ApplicationDaoJdbcImpl();
	}
}