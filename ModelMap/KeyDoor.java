package Model.ModelMap;

import Model.ModelItems.Key;
import Model.ModelCharacter.Hero;
import Model.ModelItems.Item;

import java.util.HashMap;

public class KeyDoor extends Exit {
	private Key key;
	private Boolean isUnlocked;
	
	public KeyDoor(Location entrance, Location wayOut, String name) {
		super(entrance, wayOut, name);
		this.isOpened = false;
		this.isUnlocked = false;
	}
	
	public void setKey(Key key) {
		this.key = key;
	}

	public boolean hero_unlock(Hero hero) {
		if (!isUnlocked) {
			HashMap<String, Item> backpack = hero.getBackpack().getItem_list();
			for (Item item : backpack.values()) {
				if (item.equals(key)) {
					//TODO make controller interact with model for this
					//System.out.println("The key_door is Unlocked, exit crossed successfully");
					isUnlocked = true;
					return true;
				}
			}
			//TODO make controller interact with model for this
			//System.out.println("This exit is a KEY_DOOR, and you have no available key in your backpack");
			return false;
		}
		else return true;
	}


	public void open() {
		if (isUnlocked) {
			//TODO make controller interact with model for this
			//System.out.println("The key_door is Opened");
			//return true;
		}
		else{
			//TODO make controller interact with model for this
			//System.out.println("the key_door is not Opened");
			//return false;
		}
	}
	
	@Override
	public Boolean canBeCrossed() {
		return isOpened;
	}
	
	@Override
	public void isCrossing() {
		//TODO make controller interact with model for this
		//System.out.println("You are crossing a key door");
	}

	public Key getkey(){
		return key;
	}
}
