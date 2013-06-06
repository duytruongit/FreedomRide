package org.thyee.freedomride.client.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created with IntelliJ IDEA. User: Thyee Date: 13-3-25 Time: 下午3:07 To change
 * this template use File | Settings | File Templates.
 */

public class Attractions {

	private String id;
	private String name;
	private String city;
	private String address;
	private String ticket;
	private String visit_time;
	private String type;
	private String contact;
	private Date create_time;
	private String visit_length;
	private String season;
	private String summary;
	private double x;
	private double y;

	@JsonIgnore
	private boolean isAdd;

	private List<Picture> pictures;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getVisit_time() {
		return visit_time;
	}

	public void setVisit_time(String visit_time) {
		this.visit_time = visit_time;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getVisit_length() {
		return visit_length;
	}

	public void setVisit_length(String visit_length) {
		this.visit_length = visit_length;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAdd() {
		return isAdd;
	}

	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

}
