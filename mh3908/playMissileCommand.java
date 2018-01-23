package edu.nyu.cs.mh3908;

import processing.core.*;
import java.util.ArrayList;

/**
 * This class inherits from PApplet which automatically runs setup() and draw() to enable graphical programming. 
 * This program allows the user to play a game of missile command, where the user can to shoot down ballistic missiles that are falling towards the earth with a laser.
 * @author matthewherman2
 * @version 1.0
 */
public class playMissileCommand extends PApplet {
	
	//Initialize array lists
	public ArrayList<Explosion> explosions = new ArrayList<Explosion>();
	public ArrayList<BallisticMissile> ballisticMissiles = new ArrayList<BallisticMissile>();
	public ArrayList<Laser> lasers = new ArrayList<Laser>();
	public ArrayList<City> cities = new ArrayList<City>();
	public ArrayList<BallisticMissile> ballisticMissilesToDetonate = new ArrayList<BallisticMissile>();
	public ArrayList<Information> information = new ArrayList<Information>();

	/**
	 * This method is called when the mouse is released and generates a small laser from earth to the mouse position.
	 */
	public void mouseReleased() {
		if (Laser.checkBattery()) {
			
 			Laser newLaser = new Laser(mouseX, mouseY, this);
 			this.lasers.add(newLaser);
 			Laser.useBattery();
 			
 		}
	}
	
	/**
	 * This method is called when a key is pressed and generated a large laser from earth the the mouse position.
	 */
	public void keyPressed() {
		if (Laser.checkBattery()) {
			
 			Laser newLaser = new Laser(mouseX, mouseY, this, 10);
 			this.lasers.add(newLaser);
 			Laser.useBattery();
 			
 		}
	}
	
	/**
	 * This method is called automatically and run once before draw(). It is used to set the screen size and frame rate and initialize a city and information object.
	 */
	public void setup() {
		this.size(800,700);
		this.frameRate(30);
		
		//initialize city
 		City newCity = new City(this);
 		this.cities.add(newCity);
 		
 		//initialize information
 		Information newInformation = new Information(this);
 		this.information.add(newInformation);
		
	}
	
	
	/**
	 * This method is called automatically and loops continuously according to the frame rate set in setup().
	 * This method contains for loops that loop through the array lists and draw each object in them.
	 * This method also contains for loops that loop through explosions and ballistic missiles to check for collisions.
	 */
 	public void draw() {
 		//sets background screen to black
 		this.background(0, 0, 0);
 		
 		//loops through the array list of cities and draws each object
 		for (int i=0; i<cities.size(); i++) {
 			City newCity1 = cities.get(i);
 			newCity1.draw();
 		}
 		
 		//draws information; Score, time played, people killed, instructions, and a picture of the creator)
 		this.information.get(0).draw(this.cities.get(0));
 		
 		//spawns ballistic missiles
 		if (BallisticMissile.checkSpawn()) {
 			BallisticMissile newMissile = new BallisticMissile (this);
 			this.ballisticMissiles.add(newMissile);
 		}
 		
 		//loops through the array list of ballistic missiles and draws each object
 		for (int i=0; i<ballisticMissiles.size(); i++) {
 			BallisticMissile newBallisticMissile = ballisticMissiles.get(i);
 			newBallisticMissile.draw();
 		}
 		
 		//re-charge Laser's battery, 
 		Laser.recharge();
 		
 		//loop through the array list of lasers and draws each object
 		for (int j=0; j<lasers.size(); j++) {
 			Laser newLaser = lasers.get(j);
 			newLaser.draw();
 		}
 		
 		//loops through the array list of explosion and draws each object	
 		for (int k=0; k<explosions.size(); k++) {
 			Explosion newExplosion = explosions.get(k);
 			newExplosion.draw();
 		}
 		
 		//loops through the array lists of explosions and ballistic missiles and check for collision between any of these two objects. If a collision is detected,
 		//the ballistic missile is added to an array list to remove the ballistic missile from its own list
 		for (Explosion explosion:explosions) {
 			for (BallisticMissile ballisticMissile:ballisticMissiles) {
 				
 				if (explosion.checkCollision(explosion, ballisticMissile)) {
 					ballisticMissilesToDetonate.add(ballisticMissile);
 				}
 				
 			}
 		}
 		
 		//loops through the array list of ballistic missiles to check if they have hit earth. If a collision is detected, the number of people killed is incremented and 
 		//the ballistic missile is added to an array list to remove the ballistic missile from its own list
 		for (BallisticMissile ballisticMissile: ballisticMissiles) {
 			if (ballisticMissile.checkCollision()) {
 				
 				cities.get(0).killPeople();
 				ballisticMissilesToDetonate.add(ballisticMissile);
 				
 			}
 		}
 		
 		//Removes ballistic missiles from the array list of ballistic missiles. Ballistic missiles will only be in this list if they have collided with earth or an explosion.
 		for (BallisticMissile ballisticMissile: ballisticMissilesToDetonate ) {
// 			int posX = ballisticMissile.getPositionX();
// 			int posY = ballisticMissile.getPositionY();
 			
 			ballisticMissile.detonate();
// 			Explosion newExplosion = new Explosion(posX, posY, this);
// 			explosions.add(newExplosion);
 			
 		}
 		
 	}
	

}
