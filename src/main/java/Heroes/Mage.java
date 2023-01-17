package Heroes;

import Items.ArmorType;
import Items.WeaponType;
import java.util.ArrayList;

public class Mage extends Hero{


/* Inherited Constructor */
    public Mage(String heroName) {
        super(heroName);
        this.levelAttributes = new HeroAttributes(1,1,8);
        this.validArmor = ArmorType.CLOTH;
        this.validWeapon = new ArrayList<>();
        validWeapon.add(WeaponType.STAFF);
        validWeapon.add(WeaponType.WAND);
        this.attributesPerLevel = new int[]{1, 1, 5};
    }

}

