package Heroes.subclasses;

import Heroes.Hero;
import Heroes.HeroAttributes;
import Items.enums.ArmorType;
import Items.enums.WeaponType;
import java.util.ArrayList;

/* They say that by the time you spot a Rogue, you are probably already in Death's door.
Her agile, surgical strikes can take down a man five times her size. Often working alone,
carrying out death contracts, running the underworld with her connections, infiltrating
the enemy lines. Always goes unnoticed. Always lethal. */

public class Rogue extends Hero {


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
