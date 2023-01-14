package Heroes;

import Items.Item;
import Items.Slot;
import java.util.HashMap;

public class Mage extends Hero{
    private final String validArmor = "Cloth"; //to be modified to have enum values
    private final String[] validWeapon = {"Staff","Wand"};
    private HeroAttributes levelAttributes = new HeroAttributes(1,1,8);
    private final int lvl_strn = 1, lvl_dextr = 1 , lvl_intl = 5;
    private int heroLevel = 1;
    private HashMap<Slot, Item> equipment = new HashMap<>();


    public Mage(String heroName) {
        super(heroName);
    }

}
