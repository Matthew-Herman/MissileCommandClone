package edu.nyu.cs.mh3908;

/**
 * Class for CounterMissileBattery
 * @author matthewherman2
 *
 */
public class CounterMissileBattery {
	//Attributes
	protected int counterMissileBatteryPosition; //0-2
	
	//Getters & Setters
	public int getPosition() {
		return this.counterMissileBatteryPosition;
	}
	
	public void setCounterMissileBatteryPosition(int position) {
		if (position >=0 && position < 3) {
			this.counterMissileBatteryPosition = position;
		}
	}
	
	//Constructor
	public CounterMissileBattery() {
		
	}
	
	//Methods

}
