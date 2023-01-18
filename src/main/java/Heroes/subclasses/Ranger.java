package Heroes.subclasses;

import Heroes.Hero;
import Heroes.HeroAttributes;
import Items.enums.ArmorType;
import Items.enums.WeaponType;
import java.util.ArrayList;

/* Only a single spark is enough for the Ranger to track her target from hundreds
of meters away. With vision of an eagle, velocity of a tiger and slyness of a fox,
a Ranger's arrows are considered a certain death. An excellent marksman, moreover
a fearless companion to her teammates, assisting them from distance. */

public class Ranger extends Hero {


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
