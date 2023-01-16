package Heroes;

import Items.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Ranger extends Hero{


/* Overridden Fields with specified values */
    private final ArmorType validArmor = ArmorType.MAIL;
    private final ArrayList<WeaponType> validWeapon;
    {
        validWeapon = new ArrayList<>();
        validWeapon.add(WeaponType.BOW);
    }

    private final HeroAttributes levelAttributes = new HeroAttributes(1,7,1);
/* Overridden Fields End */


/* Inherited Constructor */
    public Ranger(String heroName) {
        super(heroName);
    }


/* Overridden Methods */
    public void levelUp() {

        super.setHeroLevel(heroLevel);
        levelAttributes.setStrength(1);
        levelAttributes.setDexterity(5);
        levelAttributes.setIntelligence(1);

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
    public ArrayList<WeaponType> getValidWeapon() {
        return validWeapon;
    }
    @Override
    public HashMap<Slot, Item> getHeroEquipment() {
        return heroEquipment;
    }
/* Overridden Methods End */
}
