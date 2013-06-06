package org.thyee.freedomride.client.entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: Thyee Date: 13-3-29 Time: 下午12:48 To change
 * this template use File | Settings | File Templates.
 */
public class Restaurant {
	private String id;
	private String name;
	private String address;
	private String type;
	private String summary;
	private String business_hours;
	private String traffic;
	private double consumption; // 人均消费
	private Double y;
	private Double x;

	private List<Picture> pictures;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public String getBusiness_hours() {
		return business_hours;
	}

	public void setBusiness_hours(String business_hours) {
		this.business_hours = business_hours;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}
}
