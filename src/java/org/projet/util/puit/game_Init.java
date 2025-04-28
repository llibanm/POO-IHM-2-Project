package src.java.org.projet.util;


import src.java.org.projet.model.modelMap.Location;

public class game_Init {



    public static gameState initialization() {

        //******************************************* MAP INITIALISATION ********************************************************

        //CREATION OF THE LOCATIONS OF THE MAP
        Location earth = new Location("EARTH", "Your starting location", "Find a way to mars");
        Location mars = new Location("MARS", "The red planet.", "Look for Jupiter");
        Location moon = new Location("MOON", "A quiet, lonely place.", "Escape to Venus");
        Location neptune = new Location("NEPTUNE", "An icy giant. No possible exit here. Game Over!", "Look for ...");
        Location saturn = new Location("SATURN", "A planet with rings.", "Look for ...");
        Location jupiter = new Location("JUPITER", "The largest planet.", "Find Uranus");
        Location venus = new Location("VENUS", "A hot and hostile environment.", "Find Saturn");
        Location uranus = new Location("URANUS", "If you are here, congratulations! You reached your Goal ! You win!", " ");

        //CREATION OF THE HERO
        Hero hero = new Hero("HERO", 20, earth, "URANUS", "SATURN", "FINISHED");

        //CREATION OF THE MAP
        MyMap gameMap = new MyMap("SOLAR SYSTEM", hero);


        //ADDING THE LOCATION TO THE MAP
        gameMap.addLocation(earth);
        gameMap.addLocation(mars);
        gameMap.addLocation(moon);
        gameMap.addLocation(saturn);
        gameMap.addLocation(venus);
        gameMap.addLocation(jupiter);
        gameMap.addLocation(neptune);
        gameMap.addLocation(uranus);


        //CREATION OF THE EXITS OF EACH LOCATION
        //EARTH :
        KeyDoor earthToMars = new KeyDoor(earth, mars, "KD_Earth2Mars");
        SimpleDoor earthToMoon = new SimpleDoor(earth, moon, "SD_Earth2Moon");

        //MARS :
        CodeDoor marsToSaturn = new CodeDoor(mars, saturn, "CD_Mars2Saturn");
        SimpleDoor marsToJupiter = new SimpleDoor(mars, jupiter, "SD_Mars2Jupiter");

        //MOON :
        SimpleDoor moonToEarth = new SimpleDoor(moon, earth, "SD_Moon2Earth");
        SimpleDoor moonToNeptune = new SimpleDoor(moon, neptune, "SD_Moon2Neptune");
        SimpleDoor moonToVenus = new SimpleDoor(moon, venus, "SD_Moon2Venus");

        //SATURN :
        SimpleDoor saturnToMars = new SimpleDoor(saturn, mars, "SD_Saturn2Mars");
        CodeDoor saturnToVenus = new CodeDoor(saturn, venus, "CD_Saturn2Venus");

        //JUPITER :
        SimpleDoor jupiterToSaturn = new SimpleDoor(jupiter, saturn, "SD_Jupier2Saturn");
        CodeDoor jupiterToUranus = new CodeDoor(jupiter, uranus, "CD_Jupiter2Uranus");

        //VENUS :
        CodeDoor venusToMoon = new CodeDoor(venus, moon, "CD_Venus2Moon");
        SimpleDoor venusToSaturn = new SimpleDoor(venus, saturn, "SD_Venus2Saturn");
        KeyDoor venusToUranus = new KeyDoor(venus, uranus, "KD_Venus2Uranus");


        //INITIALISATION OF THE EXITS FOR EACH LOCATION
        //EARTH :
        earth.addExit(earthToMars.getneighbor().getName(), earthToMars);
        earth.addExit(earthToMoon.getneighbor().getName(), earthToMoon);

        // MARS :
        mars.addExit(marsToSaturn.getneighbor().getName(), marsToSaturn);
        mars.addExit(marsToJupiter.getneighbor().getName(), marsToJupiter);

        //MOON :
        moon.addExit(moonToEarth.getneighbor().getName(), moonToEarth);
        moon.addExit(moonToNeptune.getneighbor().getName(), moonToNeptune);
        moon.addExit(moonToVenus.getneighbor().getName(), moonToVenus);

        //SATURN :
        saturn.addExit(saturnToMars.getneighbor().getName(), saturnToMars);
        saturn.addExit(saturnToVenus.getneighbor().getName(), saturnToVenus);

        //JUPITER :
        jupiter.addExit(jupiterToSaturn.getneighbor().getName(), jupiterToSaturn);
        jupiter.addExit(jupiterToUranus.getneighbor().getName(), jupiterToUranus);

        //VENUS :
        venus.addExit(venusToMoon.getneighbor().getName(), venusToMoon);
        venus.addExit(venusToSaturn.getneighbor().getName(), venusToSaturn);
        venus.addExit(venusToUranus.getneighbor().getName(), venusToUranus);


        //CREATION OF THE 2 KEYS OF THE KEYDOORS earthToMars venusToUranus
        Key E2Ma = new Key("KEY_Eart2Mars", "this key allows you to open the KEY_DOOR from EARTH to MARS");
        Key V2U = new Key("KEY_Venus2Uranus", "this key allows you to open the KEY_DOOR from VENUS to URANUS");

        //SET KEYS OF KEYDOORS
        earthToMars.setKey(E2Ma);
        venusToUranus.setKey(V2U);

        //SET CODES OF CODEDOORS
        jupiterToUranus.setCode("FINISHED");
        marsToSaturn.setCode("HELLO");
        saturnToVenus.setCode("TRIP");
        venusToMoon.setCode("IWANTKEY");

        //CREATION OF THE GUIDEs OF EACH LOCATION
        Guide earthGuide = new Guide("EARTH_GUIDE", 2, "you needed my key to cross the exit to MARS," +
                " you have it now");
        Guide moonGuide = new Guide("MOON_GUIDE", 2, "you needed my key to cross the exit to URANUS in VENUS," +
                " you have it now");
        Guide marsGuide = new Guide("MARS_GUIDE", 2, "the code of the exit to SATURN is : "
                + marsToSaturn.getcode());
        Guide saturnGuide = new Guide("SATURN_GUIDE", 2, "the code of the exit to URANUS in JUPITER is : "
                + jupiterToUranus.getcode() + "\nAnd the code of the exit from HERE to VENUS  is : "
                + saturnToVenus.getcode());
        Guide jupiterGuide = new Guide("JUPITER_GUIDE", 2, "You need to go to SATURN to find your way");
        Guide venusGuide = new Guide("VENUS_GUIDE", 2, "you'll find your way in the moon\n"
                + "The code of the exit from HERE to the MOON is : " + venusToMoon.getcode());

        //ADDING KEYS TO THE GUIDES
        earthGuide.setKey(E2Ma);
        moonGuide.setKey(V2U);

        //ADDING GUIDES TO THE LOCATIONS
        earth.addChar(earthGuide);
        mars.addChar(marsGuide);
        moon.addChar(moonGuide);
        saturn.addChar(saturnGuide);
        jupiter.addChar(jupiterGuide);
        venus.addChar(venusGuide);

        //CREATION OF THE DRONES OF THE LOCATIONS
        Drone moondrone = new Drone("MOON_DRONE", 6);
        Drone saturndrone = new Drone("SATURN_DRONE", 6);
        Drone jupiterdrone = new Drone("JUPITER_DRONE", 6);

        //ADDING THE DRONES TO THE LOCATIONS
        moon.addChar(moondrone);
        saturn.addChar(saturndrone);
        jupiter.addChar(jupiterdrone);

        //CREATION OF THE AGRESSORS OF THE LOCATIONS
        Agressor marsagressor = new Agressor("MARS_AGRESSOR", 3);
        Agressor jupiteragressor = new Agressor("JUPITER_AGRESSOR", 3);
        Agressor venusagressor = new Agressor("VENUS_AGRESSOR", 3);

        //ADDING THE AGRESSORS TO THE LOCATIONS
        mars.addChar(marsagressor);
        jupiter.addChar(jupiteragressor);
        venus.addChar(venusagressor);

        //CREATION OF THE DOCTORS OF THE LOCATIONS
        Doctor jupiterdoctor = new Doctor("JUPITER_DOCTOR", 2);
        Doctor venusdoctor = new Doctor("VENUS_DOCTOR", 2);
        Doctor earthdoctor = new Doctor("EARTH_DOCTOR", 2);

        //ADDING THE DOCTORS TO THE LOCATIONS
        jupiter.addChar(jupiterdoctor);
        venus.addChar(venusdoctor);
        earth.addChar(earthdoctor);

        //CREATION OF THE ITEMS OF THE LOCATIONS
        Food saturnfood = new Food("FOOD");
        Food jupiterfood = new Food("FOOD");
        Food venusfood = new Food("FOOD");

        //ADDING THE FOOD TO THE LOCATIONS
        saturn.addItem(saturnfood.getName(), saturnfood);
        jupiter.addItem(jupiterfood.getName(), jupiterfood);
        venus.addItem(venusfood.getName(), venusfood);

        //***************************************** END OF MAP INITIALISATION ******************************************
        return new gameState(hero, gameMap);

    }
}