package Heroes;

import Items.*;

import java.util.HashMap;

public class Warrior extends Hero{


/* Overridden Fields with specified values */
    private final ArmorType validArmor = ArmorType.PLATE;
    private final WeaponType[] validWeapon = {WeaponType.HAMMER,WeaponType.SWORD,WeaponType.AXE};
    private final HeroAttributes levelAttributes = new HeroAttributes(5,2,1);
/* Overridden Fields End */


/* Inherited Constructor */
    public Warrior(String heroName) {
        super(heroName);
    }


/* Overridden Methods */
    public void levelUp() {

        super.setHeroLevel(heroLevel);
        levelAttributes.setStrength(3);
        levelAttributes.setDexterity(2);
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
