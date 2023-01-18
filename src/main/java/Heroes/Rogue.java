package Heroes;

import Items.ArmorType;
import Items.WeaponType;
import java.util.ArrayList;

public class Rogue extends Hero{


/* Inherited Constructor with Rogue
class specific fields from
project documentation */
    public Rogue(String heroName) {
        super(heroName);
        /* Specified starting HeroAttributes for Rogue class */
        this.levelAttributes = new HeroAttributes(2,6,1);
        /* Specified attributes gained per level for Rogue class */
        this.attributesPerLevel = new int[]{1,4,1};
        /* Rogue can only equip Leather armor */
        this.validArmor = ArmorType.LEATHER;
        /* Rogue can equip Daggers and Swords */
        this.validWeapon = new ArrayList<>();
        validWeapon.add(WeaponType.DAGGER);
        validWeapon.add(WeaponType.SWORD);
    }


}
