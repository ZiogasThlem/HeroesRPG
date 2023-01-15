package Heroes;

import Items.*;

import java.util.HashMap;

public class Ranger extends Hero{


/* Overridden Fields with specified values */
    private final ArmorType validArmor = ArmorType.MAIL;
    private final WeaponType[] validWeapon = {WeaponType.BOW};

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
/* Overridden Methods End */
}
