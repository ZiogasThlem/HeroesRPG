package Heroes;

import Items.Item;
import Items.Slot;

import java.util.HashMap;

public class Ranger extends Hero{

    private final String validArmor = "Mail"; //to be modified to have enum values
    private final String[] validWeapon = {"Bow"};
    private HeroAttributes levelAttributes = new HeroAttributes(1,7,1);
    private final int lvl_strn = 1, lvl_dextr = 5 , lvl_intl = 1;
    private int heroLevel = 1;
    private HashMap<Slot, Item> equipment = new HashMap<>();
    public Ranger(String heroName) {
        super(heroName);
    }

}
