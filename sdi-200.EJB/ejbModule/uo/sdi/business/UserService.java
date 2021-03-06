package uo.sdi.business;

import java.util.List;
import java.util.Map;

import uo.sdi.model.User;

public interface UserService {

    public void save(User user) throws Exception;

    public List<User> findPassengers(Long idTrip) throws Exception;

    /**
     * Devuelve la informacion de un usuario (identificador, nombre, apellidos)
     * 
     * @param id
     *            identificador del usuario del que se quieren obtener los datos
     * @return informacion del viaje
     * 
     */
    public Map<String, Object> getInfoUser(Long id) throws Exception;

    public List<User> getUsers() throws Exception;

    public void desUsuario(String login) throws Exception;

    public User getUserByLogin(String login) throws Exception;
}