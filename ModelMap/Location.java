package Model.ModelMap;
import java.util.*;
import Model.ModelCharacter.MyCharacter;
import Model.ModelItems.Item;
import java.util.HashMap;

public class Location {
	private final String name;
	private final String description; //What you can see in this place
	private final String goal;  //how to go to the next room
	private HashMap<String, Exit> exits = new HashMap<>(); //All the exits of this location are associated with their name
	private ArrayList<MyCharacter> characters;
	private HashMap<String, Item> items;
	
	public Location(String name, String description, String goal) {
		this.name = name;
		this.description = description;
		this.goal = goal;
		characters = new ArrayList<>();
		items = new HashMap<>();
	}

	public void addItem(String name, Item myitem) {
		items.put(name, myitem);
	}

	public void remItem(String myitem) {
		items.remove(myitem);
	}
	
	public void addChar(MyCharacter myChar) {
		characters.add(myChar);
	}
	
	public void remChar(MyCharacter mychar) {
		characters.remove(mychar);
	}
	
	public void addExit(String name, Exit myExit) {  //Add an exit to the Location
		exits.put(name, myExit);
	}
	
	public void remExit(String name) {  //Remove an exit to the Location
		exits.remove(name);
	}
//TODO make controller interact with model for this
/*	@Override
	public void describe() { //print the caracteristics of the exits and of this location
		System.out.println(name + " : " + description);
		if (exits.isEmpty()) {
			System.out.println(name + " has no exits");
		}else{
			System.out.println("Possible exits :");
			printExits();
		}
		System.out.println("\n");
	}
*/

	public void printExits() {
		for (Exit exit : exits.values()) {
			//TODO make controller interact with model for this
			//System.out.println("The exit " + exit.getName() + " leads to " + exit.getneighbor().getName());
		}
	}

	//TODO make controller interact with model for this
	/*public void printItems(){
		for (Map.Entry<String, Item> entry : items.entrySet()) {
			String name = entry.getKey();
			Item item = entry.getValue();
			System.out.println("The item " + name + " : ");
			item.describe();
		}
	}*/
	//TODO make controller interact with model for this
	/*public void printCharacters(){
		for (MyCharacter character : characters) {
			System.out.println(character.getName());
			character.describe();
		}
	}*/

	public String getName(){
		return name;
	}


	public Exit getExit(String name) { //returns the exit associated to the name in this location
		return exits.get(name);
	}
	
	public boolean isContainExit(String name) { //true if the location contain an exit associated with the name
        return exits.containsKey(name);
    }

	public Item getItem(String name) { //returns the item associated to the name in this location
		return items.get(name);
	}

	public boolean isContainsItem(String name) { //true if the location contain an item associated with the name
        String toUC = name.toUpperCase();
		return items.containsKey(name);
    }

	public ArrayList<MyCharacter> getCharacters() {
		return characters;
	}
	public HashMap<String, Item> getItems(){
		return items;
	}

	public void print_attackable_targets(){
		for (MyCharacter character : this.characters) {
			if (character.getHP() > 2){
				//TODO make controller interact with model for this
				//System.out.println("You can attack the : " + character.getName() + " by using your weapons with USE command");
			}
		}
	}

	public void remove_dead_chars(){
		Iterator<MyCharacter> it = characters.iterator();
		while(it.hasNext()){
			MyCharacter character = it.next();
			if (character.getHP() <= 0){
				System.out.println(character.getName() + " IS DEAD.");
				it.remove();
			}
		}
	}


}
