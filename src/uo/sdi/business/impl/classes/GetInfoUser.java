package uo.sdi.business.impl.classes;

import java.util.HashMap;
import java.util.Map;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;

public class GetInfoUser {
    public final String USUARIO_INVALIDO = "El usuario no es valido";
    public final String USUARIO_CANCELADO = "El usuario esta cancelado";
    public final String ERROR = "Ha habido un error al buscar la informacion"
	    + "del usuario";

    public Map<String, Object> getInfo(Long id) throws Exception {
	Map<String, Object> info = new HashMap<String, Object>();

	try {
	    User user = Factories.persistence.newUserDao().findById(id);

	    if (user == null) {
		throw new Exception(USUARIO_INVALIDO);
	    }
	    
	    if(user.getStatus().equals(UserStatus.CANCELLED)) {
		throw new Exception(USUARIO_CANCELADO);
	    }
	    
	    info.put("id", user.getId());
	    info.put("name", user.getName());
	    info.put("surname", user.getSurname());
	}

	catch (Exception excep) {
	    switch (excep.getMessage()) {
	    case USUARIO_INVALIDO:
		throw excep;
		
	    case USUARIO_CANCELADO:
		throw excep;

	    default:
		throw new Exception(ERROR);
	    }
	}

	return info;
    }
}