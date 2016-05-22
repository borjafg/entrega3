package uo.sdi.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.crypto.Data;

/**
 * This class is not an entity, it is a DTO with the same fields as a row in the
 * table
 * 
 * @see Data Transfer Object pattern
 * @author alb
 * 
 */
@XmlRootElement(name = "rating")
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Long seatFromTripId;
    private Long seatFromUserId;
    private Long seatAboutTripId;
    private Long seatAboutUserId;

    private String comment;
    private Integer value = 0;

    @XmlElement
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @XmlElement
    public Long getSeatFromTripId() {
	return seatFromTripId;
    }

    public void setSeatFromTripId(Long seatFromTripId) {
	this.seatFromTripId = seatFromTripId;
    }

    @XmlElement
    public Long getSeatFromUserId() {
	return seatFromUserId;
    }

    public void setSeatFromUserId(Long seatFromUserId) {
	this.seatFromUserId = seatFromUserId;
    }

    @XmlElement
    public Long getSeatAboutTripId() {
	return seatAboutTripId;
    }

    public void setSeatAboutTripId(Long seatAboutTripId) {
	this.seatAboutTripId = seatAboutTripId;
    }

    @XmlElement
    public Long getSeatAboutUserId() {
	return seatAboutUserId;
    }

    public void setSeatAboutUserId(Long seatAboutUserId) {
	this.seatAboutUserId = seatAboutUserId;
    }

    @XmlElement
    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    @XmlElement
    public Integer getValue() {
	return value;
    }

    public void setValue(Integer value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return "Rating [id=" + id + ", comment=" + comment + ", value=" + value
		+ ", seatFromTripId=" + seatFromTripId + ", seatFromUserId="
		+ seatFromUserId + ", seatAboutTripId=" + seatAboutTripId
		+ ", seatAboutUserId=" + seatAboutUserId + "]";
    }
}