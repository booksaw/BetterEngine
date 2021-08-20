package com.booksaw.betterEngine.game;

/**
 * A location component, represents a 2d location
 * 
 * @author James
 *
 */
public class Location {

	public double x;
	public double y;
	public double angle;

	public Location(double x, double y) {
		this.x = x;
		this.y = y;
		angle = 0;
	}

	public Location(double x, double y, double angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}

	/**
	 * @return A new location object with the same position
	 */
	public Location getCopy() {
		return new Location(x, y, angle);
	}

	/**
	 * @return The x component of the location
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the new x component
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return The y component of the location
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the new y component
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return The angle of the location
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * @param angle the new angle
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}

}
