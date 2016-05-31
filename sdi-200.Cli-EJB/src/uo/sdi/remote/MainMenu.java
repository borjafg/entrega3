package uo.sdi.remote;

import uo.sdi.remote.actions.BorrarCalificaciones;
import uo.sdi.remote.actions.DesactivarUsuario;
import uo.sdi.remote.actions.GetUsuariosAction;
import uo.sdi.remote.actions.ListarComentarios;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {
    public MainMenu() {

	menuOptions = new Object[][] {
		{ "Consultar usuarios del sistema", GetUsuariosAction.class },
		{ "Desactivar usuarios", DesactivarUsuario.class },
		{ "Borrar calificaciones", BorrarCalificaciones.class },
		{ "Listar comentarios", ListarComentarios.class } };
    }

    public static void main(String[] args) {
	new MainMenu().execute();
    }

}