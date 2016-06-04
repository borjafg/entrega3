package uo.sdi.business.errorMessages;

import java.util.ResourceBundle;

public class ErrorMessages {
    private static final ResourceBundle errores = ResourceBundle
	    .getBundle("errorMessages.properties");

    public static String getMessage(String key) {
	return errores.getString(key);
    }
}