package uo.sdi.presentation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import uo.sdi.business.LoginService;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;
import uo.sdi.presentation.util.ErrorMessageManager;
import uo.sdi.presentation.util.UserManager;
import alb.util.log.Log;

@ManagedBean(name = "login")
@ViewScoped
public class BeanLogin implements Serializable {
    private static final long serialVersionUID = 6L;

    @ManagedProperty("user")
    private BeanUser user;

    private String userName = "";
    private String password = "";

    @PostConstruct
    public void inicializar() {
	user = (new UserManager()).crearBeanUser();
    }

    /**
     * Comprueba que el usuario y la contraseña son correctos y registra un
     * error (FacesMessage) si no lo son o si ocurre un error durante la
     * comprobación (por ejemplo, un fallo de conexión a la base de datos)
     * 
     * @return exito si son correctos, fallo en cualquier otro caso
     * 
     */
    public String validar() {
	LoginService login = Factories.services.createLoginService();
	User usuario = null;

	try {
	    // Verifica que el usuario y la contraseña son correctos y la
	    // cuenta de usuario con la que intenta loguearse no está cancelada
	    usuario = login.verify(userName, password);
	}

	catch (Exception excep) { // Error al comprobar los datos
	    Log.error("Ha ocurrido un error al intentar comprobar los datos del"
		    + " usuario");

	    return "error";
	}

	if (usuario != null && usuario.getStatus().equals(UserStatus.ACTIVE)) {
	    user.setUser(usuario);

	    Log.info("Ha iniciado sesión el usuario: [%s]", usuario.getLogin());

	    return "exito";
	}

	else {
	    FacesContext contexto = FacesContext.getCurrentInstance();

	    ErrorMessageManager.register(contexto, "j_idt9:loginButton",
		    "loginError_sendButton", FacesMessage.SEVERITY_ERROR);

	    return "fallo";
	}
    }

    public String logout() {
	user.setUser(null);

	FacesContext.getCurrentInstance().getExternalContext()
		.invalidateSession();

	Log.debug("El usuario ha cerrado sesion");

	return "logout";
    }

    /* =========================================== */
    /* ========== Geters y Seters ================ */
    /* =========================================== */

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }
}