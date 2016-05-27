package uo.sdi.business.impl.classes;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;

public class FindSeatsBy_userId {
    private final String USUARIO_INVALIDO = "El usuario no es valido";
    private final String ERROR = "Error al buscar las asistencias del usuario";

    public List<Seat> find(Long id) throws Exception {
	try {
	    validar(id);

	    List<Seat> seatList = Factories.persistence.newSeatDao()
		    .findByUser(id);

	    return seatList;
	}

	catch (Exception excep) {
	    switch (excep.getMessage()) {
	    case USUARIO_INVALIDO:
		throw excep;

	    default:
		throw new Exception(ERROR);
	    }
	}
    }

    private void validar(Long idUsuario) throws Exception {
	if (idUsuario == null) {
	    throw new Exception(USUARIO_INVALIDO);
	}

	User user = Factories.persistence.newUserDao().findById(idUsuario);

	if (user == null || user.getStatus().equals(UserStatus.CANCELLED)) {
	    throw new Exception(USUARIO_INVALIDO);
	}
    }

}