package Heroes;

import Items.ArmorType;
import Items.WeaponType;
import java.util.ArrayList;

public class Mage extends Hero{


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

