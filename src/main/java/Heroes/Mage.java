package Heroes;

import Items.*;
import java.util.HashMap;


public class Mage extends Hero{

    private final ArmorType validArmor = ArmorType.CLOTH;
    private final WeaponType[] validWeapon = {WeaponType.STAFF,WeaponType.WAND};
    private final HeroAttributes levelAttributes = new HeroAttributes(1,1,8);


    public Mage(String heroName) {
        super(heroName);
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
        return HeroEquipment;
    }


    public void levelUp() {
        super.setHeroLevel(heroLevel);
        levelAttributes.setStrength(1);
        levelAttributes.setDexterity(1);
        levelAttributes.setIntelligence(5);
    }

}

