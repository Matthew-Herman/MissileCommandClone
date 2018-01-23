package edu.nyu.cs.mh3908;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * The City class defines the city object created in playMissileCommand's setup() method;
 * @author matthewherman2
 * @version 1.0
 */
public class City{
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
	 * This final int stores the X position of a city object.
	 */
	private final int positionX = 0;
	/**
	 * This final int stores the Y position of a city object.
	 */
	private final int positionY = 600;
	/**
	 * This String stores the file path to Earth.png.
	 */
	private static final String Earth_Image_Path = "Earth.png";
	/**
	 * This int stores the number of people killed in a city by missiles.
	 */
	public long numberPeopleKilled = 0;
	
	/**
	 * This method increments the number of people killed in a city object.
	 */
	public void killPeople() {
		this.numberPeopleKilled += 1234;
	}
	
	//constructor
	/**
	 * This method constructs a city object and loads the image of the city.
	 * @param parent
	 */
	public City(PApplet parent) {
		this.parent = (playMissileCommand) parent;
		this.image = parent.loadImage(Earth_Image_Path);
	}
	
	//Methods
	/**
	 * This method draws the city which is an image of earth.
	 */
	public void draw() {
		//draw earth image
		parent.image(this.image, this.positionX, this.positionY);
		
	}

}
