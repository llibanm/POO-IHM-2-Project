package src.java.org.projet.model.modelMap;


import src.java.org.projet.model.modelCharacter.Hero;

import java.util.*;



public class MyMap {
	private final String worldName;
	private ArrayList<Location> world; //All the location of this world
	private final Hero hero;

	//Default constructor
	public MyMap(String name, Hero hero) {
		this.world = new ArrayList<>();
		this.worldName = name;
		this.hero = hero;
	}
	
	public void addLocation(Location place) {
		world.add(place);
	}

	//TODO make controller interact with model for this

	/*public void describe(){
		System.out.println("HERE IS THE MAP OF THE GAME\n");
		for(Location loc : world){
			loc.describe();
		}
	}*/

}
