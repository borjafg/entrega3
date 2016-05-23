package uo.sdi;

import uo.sdi.acciones.ConfirmarPasajeros;
import uo.sdi.acciones.ListarViajesPromovidos;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {
    public MainMenu() {

	menuOptions = new Object[][] {
		{ "Listar viajes promovidos por el usuario y abiertos",
			ListarViajesPromovidos.class },
		{ "Confirmar viajes", ConfirmarPasajeros.class } };
    }

    public static void main(String[] args) {
	new MainMenu().execute();
    }

}
