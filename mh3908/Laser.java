package edu.nyu.cs.mh3908;
import processing.core.PApplet;

/**
 * The Laser class defines the laser objects created when the mouse is pressed on the screen.
 * @author matthewherman2
 * @version 1.0
 */
public class Laser {
	//Attributes
	/**
	 * This attribute stores the parent object so that processing methods can be used in this class.
	 */
	private playMissileCommand parent;
	/**
	 * This int stores the starting X position of the laser and is 400.
	 */
	private final int startX = 400;
	/**
	 * This int stores the starting Y position of the laser and is 600.
	 */
	private final int startY = 600;
	/**
	 * This int stores the target X position for the laser.
	 */
	private int targetX;
	/**
	 * This int stores the target Y position for the laser.
	 */
	private int targetY;
	/**
	 * This int stores the laser size.
	 */
	private int laserSize = 1;
	/**
	 * This int stores the time since creation and is also a count of how many "frames" or iterations of draw() the laser has existed for.
	 * This is used to ensure a laser is removed from the program after a certain amount of time.
	 */
	private int timeSinceCreation = 0; 
	/**
	 * This int stores the starting amount for the battery, which is how many lasers can be fired before re-charging.
	 */
	private static int battery = 4; 
	/**
	 * This int stores the total time since the game has started in order to make sure the battery recharges after a certain amount of time.
	 */
	private static int totalTime = 0; 
	
	//Getters & Setters
	/**
	 * This method returns a Laser obejct's target X position.
	 * @return returns an int of a Laser object's target X position.
	 */
	public int getLaserTargetX() {
		return this.targetX;
	}
	/**
	 * This method returns a Laser object's target Y position.
	 * @return returns an int of a Laser object's target Y position.
	 */
	public int getLaserTargetY() {
		return this.targetY;
	}
	/**
	 * This method returns a Laser object's size.
	 * @return returns an int of a Laser object's size.
	 */
	public int getLaserSize() {
		return this.laserSize;
	}
	
	/**
	 * This method returns a Laser object's time since creation.
	 * @return returns an int of Laser object's time since creation.
	 */
	public int getTimeSinceCreation() {
		return this.timeSinceCreation;
	}
	
	/**
	 * This method returns Laser's static battery quantity (How many shots can be fired before re-charging).
	 * @return returns an int of Laser's battery quantity. 
	 */
	public static int getBattery() {
		return Laser.battery;
	}
	
	/**
	 * This method returns Laser's static total time (total time since game start).
	 * @return returns an int of Laser's total time quantity.
	 */
	public static int getTotalTime() {
		return Laser.totalTime;
	}
	/**
	 * Sets the current X position of a Laser object.
	 * @param positionX is an int of the new X position for the Laser object.
	 */
	public void setLaserTargetX(int positionX) {
		this.targetX = positionX;
	}
	/**
	 * Sets the current Y position of a Laser object.
	 * @param positionY is an int of the new Y position for the Laser object.
	 */
	public void setLaserTargetY(int positionY) {
		this.targetY = positionY;
	}
	/**
	 * Sets the laser size.
	 * @param laserSize is an int of the laser size that should be between 0-10 inclusive. Ints larger than 10 set the laser size to 10 and Ints less than 0 set the laser
	 * size to 1.
	 */
	public void setLaserSize(int laserSize) {
		if (laserSize < 1) {
			this.laserSize = 1;
		}
		else if (laserSize > 10) {
			this.laserSize = 10;
		}
		else {
				this.laserSize = laserSize;
		}
	}
	/**
	 * This method increments a Laser object's time since it was created.
	 */
	public void incrementTimeSinceCreation() {
		this.timeSinceCreation++;
	}
	
	/**
	 * This method increments Laser's static attribute battery.
	 */
	public static void incrementBattery() {
		Laser.battery++;
	}
	
	/**
	 * This method decrements Laser's static attribute battery.
	 */
	public static void decrementBattery() {
		Laser.battery--;
	}
	
	/**
	 * This method increments Laser's static attribute total time.
	 */
	public static void incrementTotalTime() {
		Laser.totalTime++;
	}
	
	//Constructor
	/**
	 * This constructor constructs a default laser.
	 * @param mouseX is an int of the mouse's current X position.
	 * @param mouseY is an int of the mouse's current Y position.
	 * @param parent is an object of the PApplet class.
	 */
	public Laser(int mouseX, int mouseY, PApplet parent) {
		this.parent = (playMissileCommand) parent;
		setLaserTargetX(mouseX);
		setLaserTargetY(mouseY);
	}
	
	/**
	 * This constructor constructs a super laser.
	 * @param mouseX is an int of the mouse's current X position.
	 * @param mouseY is an int of the mouse's current Y postion.
	 * @param parent is an object of the PApplet class.
	 * @param laserSize is an int that sets laser Size.
	 */
	public Laser(int mouseX, int mouseY, PApplet parent, int laserSize) {
		this.parent = (playMissileCommand) parent;
		setLaserTargetX(mouseX);
		setLaserTargetY(mouseY);
		setLaserSize(laserSize);
	}
	
	//Methods
	/**
	 * This method draws a laser object using processing's line() method. Every time draw() is called, TimeSinceCreation is incremented.
	 * When TimeSinceCreation >= 45 the laser is removed from its parent's array list and an explosion object is generated at the Laser's target X and Y position.
	 */
	public void draw() {
		parent.stroke(0, 100, 255);
		parent.strokeWeight(getLaserSize());
		parent.line(this.startX, this.startY, getLaserTargetX(), getLaserTargetY());
		incrementTimeSinceCreation();
		
		//after 1.5 seconds or 45 frames, create an explosion and remove the laser from the arrayList
		if (getTimeSinceCreation() >= 45) {
			Explosion newExplosion = new Explosion(this.targetX, this.targetY, this.parent);
 			parent.explosions.add(newExplosion);
			parent.lasers.remove(this);
		}

	}
	
	/**
	 * This method increments Laser's total time and recharges the battery by incrementing it if (total time % 50 == 0) and (battery < 4). Thus 4 is the max value
	 * of battery.
	 */
	public static void recharge() {
		Laser.incrementTotalTime();
		if (getBattery() < 4) {
			if (getTotalTime() % 50 == 0) {
				incrementBattery();
			}
		}
		
	}
	
	/**
	 * This method decrements laser's battery.
	 */
	public static void useBattery() {
		decrementBattery();
	}
	
	/**
	 * This method returns true if Laser's battery is not <= 0.
	 * @return returns a boolean true if Laser's battery is not <= 0
	 */
	public static boolean checkBattery() {
		boolean isEmpty = false;
		if (getBattery() <= 0) {
			isEmpty = true;
		}
		return !isEmpty;
	}

}
