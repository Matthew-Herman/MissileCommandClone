package edu.nyu.cs.mh3908;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Class for CounterMissile, a subclass of CounterMissile Battery
 * @author matthewherman2
 *
 */
public class BallisticMissile {
	//Attributes
	/**
	 * This attribute stores the parent object so that processing methods can be used in this class.
	 */
	private playMissileCommand parent;
	/**
	 * This PImage stores an image.
	 */
	private PImage image;
	/**
	 * This string stores the file path to image Bmissile.png.
	 */
	private String Rocket_Image_path = "Bmissile.png";
	/**
	 * This int stores the X position of a ballistic missile. This is given a pseudo-random number later.
	 */
	private int positionX;
	/**
	 * This int stores the Y position of a ballistic missile. The default is 0 so the ballistic missiles are spawned at the top of the screen.
	 */
	private int positionY = 0; 
	/**
	 * This flag indicates if a missile has been destroyed.
	 */
	private boolean isDestroyed = false;
	/**
	 * This static int stores the total time since the game was started.
	 */
	private static int timeCounter = 0; //stores the time based on frames to spawn missiles every second
	
	//Getters & Setters
	/**
	 * This method returns the X position of a ballistic missile object.
	 * @return returns an int of the X position of a ballistic missile object.
	 */
	public int getPositionX() {
		return this.positionX;
	}
	/**
	 * This method returns the Y position of a ballistic missile object.
	 * @return returns an int of the Y position of a ballistic missile object.
	 */
	public int getPositionY() {
		return this.positionY;
	}
	/**
	 * This method returns a boolean that indicates if a ballistic missile object has been destroyed.
	 * @return returns a boolean that indicates if a ballistic missile object has been destroyed.
	 */
	public boolean getIsDestroyed() {
		return this.isDestroyed;
	}
	/**
	 * This method returns the time since the game was started.
	 * @return returns an int that is the time since the game was started.
	 */
	public static int getTimeCounter() {
		return BallisticMissile.timeCounter;
	}
	/**
	 * This method returns the height of the image loaded for a city object.
	 * @return returns an int that is the height of the image loaded for a city object.
	 */
	public int getHeight() {
		return this.image.height;
	}
	/**
	 * This method returns the width of the image loaded for a city object.
	 * @return returns an int that is the width of the image loaded for a city object.
	 */
	public int getWidth() {
		return this.image.width;
	}
	/**
	 * This method sets the X position of a ballistic missile object.
	 * @param positionX is an int that is the new X position for a ballistic missile object.
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	/**
	 * This method sets the Y position of a ballistic missile object.
	 * @param positionY is an int that is the new X position for a ballistic missile object.
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	/**
	 * This method sets the flag if a ballistic missile object is destroyed
	 * @param isDestroyed is a boolean.
	 */
	public void setIsDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
	/**
	 * This method increments the static time counter.
	 */
	public static void incrementTimeCounter() {
		BallisticMissile.timeCounter++;
	}
	
	//constructor
	/**
	 * This method constructs a ballistic missile with a random X position between the width of the screen and loads the image of the ballistic missile.
	 * @param parent
	 */
	public BallisticMissile(PApplet parent) {
		this.parent = (playMissileCommand) parent;
		this.image = parent.loadImage(Rocket_Image_path);
		setPositionX( (int) (40 + Math.random()*691) );
	}
	
	//Methods
	/**
	 * This method draw's a ballistic missile and calls the move() method.
	 */
	public void draw() {
		if (!getIsDestroyed()) {
			parent.image(this.image, getPositionX(), getPositionY());
			move();
		}
	}
	
	/**
	 * This method makes the ballistic missile move down screen by incrementing the Y position of the ballistic missile object.
	 */
	public void move() {
		setPositionY(getPositionY() + 2);
		if (getPositionY() >= 600) {
			
		}
	}
	
	/**
	 * This method removes a ballistic missile object from its parent's array list.
	 */
	public void detonate() {
		//remove ballistic missile object from parent list
		parent.ballisticMissiles.remove(this);
		

	}
	
	/**
	 * This method checks if a ballistic missile object has collided with the earth.
	 * @return returns true if a ballistic missile has passed a Y position of 600.
	 */
	public boolean checkCollision() {
		boolean isCollision = false;
		if (getPositionY() >= 600) {
			isCollision = true;
		}
		return isCollision;
	}
	
	/**
	 * This function increments time counter and returns true if the time counter meets certain conditions.
	 * @return returns a flag if a ballistic missile should be spawned.
	 */
	public static boolean checkSpawn() {
		boolean isSpawn = false;
		BallisticMissile.incrementTimeCounter();
		if (getTimeCounter() % 60 == 0 || getTimeCounter() %200 == 0 || getTimeCounter() %110 == 0) {
			isSpawn = true;
		}
		return isSpawn;
	}
}
