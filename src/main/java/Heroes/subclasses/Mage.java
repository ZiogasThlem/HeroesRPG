package Heroes.subclasses;

import Heroes.Hero;
import Heroes.HeroAttributes;
import Items.enums.ArmorType;
import Items.enums.WeaponType;
import java.util.ArrayList;

/* Mage can invoke and manipulate the powers of Frost, Fire and Arcane to his
advantage. Grown up in elite cycles, respected by the King, esteemed within the
commons. Mage is equally capable of defending his city from any kind of intruder,
as well as bringing down havoc to be spoken for hundreds of years later. */

public class Mage extends Hero {


/* Inherited Constructor with Mage
class specific fields from
project documentation */
    public Mage(String heroName) {

        super(heroName);
        /* Specified starting HeroAttributes for Mage class */
        this.levelAttributes = new HeroAttributes(1,1,8);
        /* Specified attributes gained per level for Mage class */
        this.attributesPerLevel = new int[]{1, 1, 5};
        /* Mages can only equip Cloth armor */
        this.validArmor = ArmorType.CLOTH;
        /* Mages can equip Staffs and Wands */
        this.validWeapon = new ArrayList<>();
        validWeapon.add(WeaponType.STAFF);
        validWeapon.add(WeaponType.WAND);
    }

}

