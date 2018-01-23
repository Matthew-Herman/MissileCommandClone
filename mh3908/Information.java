package edu.nyu.cs.mh3908;

import processing.core.PApplet;
import processing.core.PImage;

public class Information {
	//Attributes
	/**
	 * This attribute stores the parent object so that processing methods can be used in this class.
	 */
	private PApplet parent;
	/**
	 * This PImage stores an image.
	 */
	private PImage image;
	/**
	 * This string stores the file path to me.png.
	 */
	private String Photo_Image_Path = "me.png";
	
	/**
	 * This long stores the time since the game was started.
	 */
	private long timeSinceStart = 0;
	
	//constructor
	/**
	 * This constructs an Information object and loads an image.
	 * @param parent
	 */
	public Information(PApplet parent) {
		this.parent = parent;
		this.image = parent.loadImage(Photo_Image_Path);
	}
	
	//Getters & Setters
	/**
	 * This increments the time since the game was started.
	 */
	public void incrementTime() {
		this.timeSinceStart++;
	}
	
	/**
	 * This gets the actual time since the game was started by dividing the timeSinceStart by 30 because the FPS of draw() was set to 30 in playMissileCommand.setup().
	 * @return
	 */
	public long getRealTime() {
		return (this.timeSinceStart/30);
	}
	
	/**
	 * This method draws text of various game information.
	 * @param city
	 */
	public void draw(City city) {
		//draw photo of me
		//parent.image(image, 600, 0);
		
		//increment time
		this.incrementTime();
		
		//text color
		parent.fill(255, 0, 255);
		
		//draw City people killed information
		parent.text("PEOPLE KILLED: " + city.numberPeopleKilled, 5, 40 );
		
		//draw Laser battery information
		parent.text("BATTERY LEVEL: " + Laser.getBattery(), 5, 60);
		
		//draw time
		parent.text("TIME PLAYED: " + getRealTime(), 5, 20);
		
		//draw instructions
		parent.text("DEFEND EARTH FROM ATTACK!", 200, 20);
		parent.text("Click to shoot a laser. Press a key to shoot a super Laser", 200, 40);
		
	}

}
