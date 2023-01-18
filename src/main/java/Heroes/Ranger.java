package Heroes;

import Items.ArmorType;
import Items.WeaponType;
import java.util.ArrayList;

public class Ranger extends Hero{


/* Inherited Constructor with Ranger
class specific fields from
project documentation*/
    public Ranger(String heroName) {
        super(heroName);
        /* Specified starting HeroAttributes for Ranger class */
        this.levelAttributes = new HeroAttributes(1,7,1);
        /* Specified attributes gained per level for Ranger class */
        this.attributesPerLevel = new int[]{1,5,1};
        /* Rangers can only equip Mail armor */
        this.validArmor = ArmorType.MAIL;
        /* Rangers can only equip Bows */
        this.validWeapon = new ArrayList<>();
        validWeapon.add(WeaponType.BOW);

    }

}
