package Heroes;

import Items.*;
import java.util.HashMap;

/*
 mage class details
*/

public class Mage extends Hero{


/* Overridden Fields with specified values */
    private final ArmorType validArmor = ArmorType.CLOTH;
    private final WeaponType[] validWeapon = {WeaponType.STAFF,WeaponType.WAND};
    private final HeroAttributes levelAttributes = new HeroAttributes(1,1,8);
/* Overridden Fields End */


/* Inherited Constructor */
    public Mage(String heroName) {
        super(heroName);
    }


/* Overridden Methods */

    public void levelUp() {

        super.setHeroLevel(heroLevel);
        levelAttributes.setStrength(1);
        levelAttributes.setDexterity(1);
        levelAttributes.setIntelligence(5);

        System.out.printf("Congrats! You leveled up!" +
                "\nYou are now level %d",getHeroLevel());
    }


    @Override
    public ArmorType getValidArmor() {
        return validArmor;
    }

    @Override
    public String getLevelAttributes() {
        return levelAttributes.getHeroAttributes();
    }

    @Override
    public WeaponType[] getValidWeapon() {
        return validWeapon;
    }

    @Override
    public HashMap<Slot, Item> getHeroEquipment() {
        return heroEquipment;
    }
/* Overridden Methods End*/

}

