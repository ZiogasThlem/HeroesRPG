package Heroes;

import Items.*;

import java.util.ArrayList;
import java.util.HashMap;

/*
 mage class details
*/

public class Mage extends Hero{


/* Overridden Fields with specified values */
    private final ArmorType validArmor = ArmorType.CLOTH;
    private final ArrayList<WeaponType> validWeapon;
    {
        validWeapon = new ArrayList<>();
        validWeapon.add(WeaponType.STAFF);
        validWeapon.add(WeaponType.WAND);

    }
    private final HeroAttributes levelAttributes = new HeroAttributes(1,1,8);
/* Overridden Fields End */


/* Inherited Constructor */
    public Mage(String heroName) {
        super(heroName);
    }


/* Overridden Methods */

    public void levelUp() {

        super.setHeroLevel(1);
        levelAttributes.setStrength(1);
        levelAttributes.setDexterity(1);
        levelAttributes.setIntelligence(5);

        System.out.printf("""
                Congrats! You leveled up!
                You are now level %d
                """,getHeroLevel());
    }


    @Override
    public ArmorType getValidArmor() {
        return validArmor;
    }


    @Override
    public String getLevelAttributesString() {
        return levelAttributes.getHeroAttributesString();
    }

    @Override
    public HeroAttributes getLevelAttributes() {
        return levelAttributes;
    }

    @Override
    public String totalHeroAttributes() {
        return "Strength: " + getLevelAttributes().getStrength() +
                "\nDexterity: " + getLevelAttributes().getDexterity() +
                "\nIntelligence: " + getLevelAttributes().getIntelligence();
    }

    @Override
    public ArrayList<WeaponType> getValidWeapon() {
        return validWeapon;
    }

    @Override
    public HashMap<Slot, Item> getHeroEquipment() {
        return heroEquipment;
    }

/* Overridden Methods End*/


}

