package Heroes;

import Items.ArmorType;
import Items.WeaponType;
import java.util.ArrayList;

public class Ranger extends Hero{


/* Inherited Constructor */
    public Ranger(String heroName) {
        super(heroName);
        this.levelAttributes = new HeroAttributes(1,7,1);
        this.validArmor = ArmorType.MAIL;
        this.validWeapon = new ArrayList<>();
        validWeapon.add(WeaponType.BOW);
        this.attributesPerLevel = new int[]{1,5,1};

    }

}
