package uo.sdi.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Solicitud de un usuario para asistir a un viaje
 * 
 */
@XmlRootElement(name="application")
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long tripId;

    public Application() {
    }

    public Application(Long userId, Long tripId) {
	this.userId = userId;
	this.tripId = tripId;
    }
    @XmlElement
    public Long getUserId() {
	return userId;
    }

    public void setUserId(Long userId) {
	this.userId = userId;
    }
    @XmlElement
    public Long getTripId() {
	return tripId;
    }

    public void setTripId(Long tripId) {
	this.tripId = tripId;
    }

    @Override
    public String toString() {
	return "Application [userId=" + userId + ", tripId=" + tripId + "]";
    }

    public Long[] makeKey() {
	return new Long[] { userId, tripId };
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;

	result = prime * result + ((tripId == null) ? 0 : tripId.hashCode());
	result = prime * result + ((userId == null) ? 0 : userId.hashCode());

	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;

	if (obj == null)
	    return false;

	if (getClass() != obj.getClass())
	    return false;

	Application other = (Application) obj;

	if (tripId == null) {
	    if (other.tripId != null)
		return false;
	}

	else if (!tripId.equals(other.tripId))
	    return false;

	if (userId == null) {
	    if (other.userId != null)
		return false;
	}

	else if (!userId.equals(other.userId))
	    return false;

	return true;
    }
}