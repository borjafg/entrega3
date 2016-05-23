package uo.sdi.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.DatatypeConverter;

public class Authenticator implements ClientRequestFilter {
    private final String user;
    private final String password;

    public Authenticator(String user, String password) {
	this.user = user;
	this.password = password;
    }

    @Override
    public void filter(ClientRequestContext ctx) throws IOException {
	MultivaluedMap<String, Object> headers = ctx.getHeaders();
	final String basicAuthentication = getBasicAuthentication();
	headers.add("Authorization", basicAuthentication);
    }

    private String getBasicAuthentication() {
	String token = this.user + ":" + this.password;
	try {
	    return "Basic "
		    + DatatypeConverter.printBase64Binary(token
			    .getBytes("UTF-8"));
	} catch (UnsupportedEncodingException ex) {
	    throw new IllegalStateException("Cannot encode with UTF-8", ex);
	}
    }
}