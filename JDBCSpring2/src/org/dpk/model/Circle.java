package org.dpk.model;

public class Circle {
	@Override
	public String toString() {
		return "Circle [circleId=" + circleId + ", name=" + name + "]";
	}
	private int circleId;
	private String name;
	public Circle(int circleId, String name) {
		super();
		this.circleId = circleId;
		this.name = name;
	}
	public Circle() {
		// TODO Auto-generated constructor stub
	}
	public int getCircleId() {
		return circleId;
	}
	public void setCircleId(int circleId) {
		this.circleId = circleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
