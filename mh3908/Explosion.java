package edu.nyu.cs.mh3908;
import processing.core.PApplet;

/**
 * The Explosion class defines the explosion objects created when a laser is removed after a certain amount of time.
 * @author matthewherman2
 * @version 1.0
 */
public class Explosion {
	//Attributes
	/**
	 * This attribute stores the parent object so that processing methods can be used in this class.
	 */
	private playMissileCommand parent;
	/**
	 * This int stores the X position of the center of an explosion object.
	 */
	private int positionX;
	/**
	 * This int stores the Y position of the center of an explosion object.
	 */
	private int positionY;
	/**
	 * This int stores the current diameter of an explosion object.
	 */
	private int diameter = 0;
	/**
	 * This boolean stores if an explosion object should be expanding or not.
	 */
	private boolean expand = true;

	
	//Getters and Setters
	/**
	 * This method returns the X position of a center of an explosion object.
	 * @return returns an int of the X position of the center of an explosion object.
	 */
	public int getPositionX() {
		return this.positionX;
	}
	/**
	 * This methid returns the Y position of a center of an explosion object.
	 * @return returns an int of the Y position of the center of an explosion object.
	 */
	public int getPositionY() {
		return this.positionY;
	}
	/**
	 * This method returns the diameter of an explosion object.
	 * @return returns an int of the diameter of an explosion object.
	 */
	public int getDiameter() {
		return this.diameter;
	}
	/**
	 * This method returns if an explosion object is expanding.
	 * @return returns a boolean if an explosion object is expanding
	 */
	public boolean isExpand() {
		return this.expand;
	}
	
	/**
	 * This method sets an explosion object's X position.
	 * @param positionX is an int of the X position of the center of the explosion object.
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	/**
	 * This method sets an explosion object's Y position.
	 * @param positionY is an int of the Y position of the center of the explosion object.
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	/**
	 * This method sets an explosion object's diameter.
	 * @param diameter is an int of the diameter of an explosion object.
	 */
	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	/**
	 * This method sets the boolean is an explosion object is expanding.
	 * @param isExpand is a boolean that flags whether an explosion object is expanding.
	 */
	public void setExpand(boolean isExpand) {
		this.expand = isExpand;
	}
	
	//Constructors
	/**
	 * This method constructs an explosion object.
	 * @param x is an int of the X position of the center of an explosion object.
	 * @param y is an int of the Y position of the center of an explosion object.
	 * @param parent is a PApplet object.
	 */
	public Explosion(int x, int y, PApplet parent) {
		this.parent = (playMissileCommand) parent;
		setPositionX(x);
		setPositionY(y);
	}
	
	//Methods
	/**
	 * This method draw's an explosion with processing's ellipse() method.
	 * The method processing() is called within this method and once the diameter of the explosion is less than one it is removed from the parent object's list.
	 */
	public void draw() {
		parent.strokeWeight(1);
		parent.fill(255, 125, 0);
		parent.stroke(255, 0 ,0);
		parent.ellipse(getPositionX(), getPositionY(), getDiameter(), getDiameter());
		this.progress();
		if (getDiameter() < 1) {
			parent.explosions.remove(this); 
		}
	}
	/**
	 * This method causes the explosion object to expand once it reaches a diameter of 90 pixels and then contract.
	 */
	public void progress() {
		if (isExpand() == true) {
			setDiameter(getDiameter() + 6);
			if (getDiameter() > 90) {
				setExpand(false);
			}
			
		}
		else {
			setDiameter(getDiameter() - 3);
		}
		
	}
	
	/**
	 * This method checks for a collision between a box around an explosion object and a ballistic missile object and returns true if there is a collision.
	 * @param explosion is an Explosion object.
	 * @param ballisticMissile is a BallisticMissile object.
	 * @return returns true if there is a collision between the explosion and ballistic missile object.
	 */
	public boolean checkCollision(Explosion explosion, BallisticMissile ballisticMissile) {
		boolean isCollision = false;
		
		//check if ballistic missile is in a box around the explosion for simplicity
		if (ballisticMissile.getPositionX() > (explosion.getPositionX() - explosion.getDiameter()) && ballisticMissile.getPositionX() < explosion.getPositionX() + explosion.getDiameter()  ) {
			if (ballisticMissile.getPositionY() > explosion.getPositionY() - explosion.getDiameter() && ballisticMissile.getPositionY() < explosion.getPositionY() + explosion.getDiameter() ) {
				isCollision = true;
			}
		}
		
		return isCollision;
		
	}
	
	
	
}
