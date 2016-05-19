package uo.sdi.infrastructure;

import uo.sdi.business.ServicesFactory;
import uo.sdi.business.impl.ServicesFactoryImpl;
import uo.sdi.persistence.PersistenceFactory;


public class Factories
{
	public static ServicesFactory services = new ServicesFactoryImpl();
	public static PersistenceFactory persistence = new PersistenceFactory();
}