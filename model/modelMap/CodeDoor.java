package model.modelMap;



import model.modelCharacter.Hero;

import java.util.Scanner;

public class CodeDoor extends Exit{
	private String code;
	private Boolean isUnlocked;
	
	public CodeDoor(Location entrance, Location wayOut, String name) {
		super(entrance, wayOut, name);
		this.isOpened = false;
		this.isUnlocked = false;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public boolean hero_unlock(Hero hero) {
		if (!isUnlocked) {
			//TODO make controller interact with model for this
			//System.out.println("the exit is a CODEDOOR, write a code to go through it : ");
			Scanner scanner = new Scanner(System.in);
			String word = scanner.nextLine().toUpperCase();
			if (this.code.equals(word)) {
				isUnlocked = true;
				//TODO make controller interact with model for this
				//System.out.println("The code_door is Unlocked, exit crossed successfully");
				return true;
			} else {
				//TODO make controller interact with model for this
				//System.out.println("Wrong code, check your entry or ask the guide");
				return false;
			}
		}
		else return true;
	}

	//PS Est ce que open est sensé retourner quelque chose ?
	public void open(String code) {
		if (isUnlocked) {
			//TODO make controller interact with model for this
			//System.out.println("The code_door is Opened");
			//return true;
		}
		else{
			//TODO make controller interact with model for this
			//System.out.println("the code_door is not Opened");
			//return false;
		}
	}

	public void lock() {
		isUnlocked = false;
		isOpened = false;
	}

	
	@Override
	public Boolean canBeCrossed() {
		return isUnlocked;
	}
	
	@Override
	public void isCrossing() {
		//TODO make controller interact with model for this
		//System.out.println("You are crossing a code door");
	}

	public String getcode(){
		return code;
	}

}
