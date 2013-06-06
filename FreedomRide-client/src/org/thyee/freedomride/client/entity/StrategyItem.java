package org.thyee.freedomride.client.entity;

/**
 * Created with IntelliJ IDEA. User: Thyee Date: 13-4-8 Time: 下午9:02 To change
 * this template use File | Settings | File Templates.
 */

public class StrategyItem {

	private String id;
	private String time;
	private int step;
	private Restaurant restaurant;
	private Attractions attractions;
	private Hotel hotel;
	private String content;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Attractions getAttractions() {
		return attractions;
	}

	public void setAttractions(Attractions attractions) {
		this.attractions = attractions;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
