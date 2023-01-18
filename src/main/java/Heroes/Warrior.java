package Heroes;

import Items.ArmorType;
import Items.WeaponType;
import java.util.ArrayList;

public class Warrior extends Hero {


/* Inherited Constructor with Warrior
class specific fields from
project documentation*/
    public Warrior(String heroName) {
        super(heroName);
        /* Specified starting HeroAttributes for Warrior class */
        this.levelAttributes = new HeroAttributes(5, 2, 1);
        /* Specified attributes gained per level for Warrior class */
        this.attributesPerLevel = new int[]{3, 2, 1};
        /* Warriors can only equip Plate armor */
        this.validArmor = ArmorType.PLATE;
        /* Warriors can equip Axes, Swords and Hammers */
        this.validWeapon = new ArrayList<>();
        validWeapon.add(WeaponType.AXE);
        validWeapon.add(WeaponType.SWORD);
        validWeapon.add(WeaponType.HAMMER);

    }

}

