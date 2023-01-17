package Heroes;

import Items.ArmorType;
import Items.WeaponType;
import java.util.ArrayList;

public class Rogue extends Hero{


/* Inherited Constructor */
    public Rogue(String heroName) {
        super(heroName);
        this.levelAttributes = new HeroAttributes(2,6,1);
        this.validArmor = ArmorType.LEATHER;
        this.validWeapon = new ArrayList<>();
        validWeapon.add(WeaponType.DAGGER);
        validWeapon.add(WeaponType.SWORD);
        this.attributesPerLevel = new int[]{1,4,1};
    }


}
