package Heroes;

import Items.Item;
import Items.Slot;

import java.util.HashMap;

public class Warrior extends Hero{

    private final String validArmor = "Plate"; //to be modified to have enum values
    private final String[] validWeapon = {"Axe","Hammer","Sword"}; //to be modified to have enum values
    private HeroAttributes levelAttributes = new HeroAttributes(5,2,1);
    private final int lvl_strn = 3, lvl_dextr = 2 , lvl_intl = 1;
    private int heroLevel = 1;
    private HashMap<Slot, Item> equipment = new HashMap<>();

    public Warrior(String heroName) {
        super(heroName);
    }

}
