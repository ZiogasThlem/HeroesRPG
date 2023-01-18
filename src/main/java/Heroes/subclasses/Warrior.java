package Heroes.subclasses;

import Heroes.Hero;
import Heroes.HeroAttributes;
import Items.enums.ArmorType;
import Items.enums.WeaponType;
import java.util.ArrayList;

/* Savage, ruthless, an unstoppable force. That's how his enemies describe the
Warrior. A combination of brutality, unbeatable combat skills and strength of a
whole regiment of mere soldiers forges the Warrior to become a deadly to any fool who
dared to cause his wrath. */

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

