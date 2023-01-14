package Heroes;

import Items.Item;
import Items.Slot;

import java.util.HashMap;

public class Rogue extends Hero{

    private final String validArmor = "Leather"; //to be modified to have enum values
    private final String[] validWeapon = {"Dagger","Sword"}; //to be modified to have enum values
    private HeroAttributes levelAttributes = new HeroAttributes(5,2,1);
    private final int lvl_strn = 3, lvl_dextr = 2 , lvl_intl = 1;
    private int heroLevel = 1;
    private HashMap<Slot, Item> equipment = new HashMap<>();

    public Rogue(String heroName) {
        super(heroName);
    }
}
