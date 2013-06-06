package org.thyee.freedomride.client.entity;

import java.util.List;

public class Strategy {
	public static int STATE_PRIVATE = 0;
	public static int STATE_PUBILC = 1;

	private String id;
	private String name;
	private String type;
	private String content;
	private int rating;
	private int state;
	private List<StrategyItem> strategyItems;
	private String length;
	private String updateTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public List<StrategyItem> getStrategyItems() {
		return strategyItems;
	}

	public void setStrategyItems(List<StrategyItem> strategyItems) {
		this.strategyItems = strategyItems;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
