package Heroes;

import Exceptions.InvalidArmorException;
import Items.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Warrior extends Hero{


/* Overridden Fields with specified values */
    private final ArmorType validArmor = ArmorType.PLATE;
    private final ArrayList<WeaponType> validWeapon;
    {
        validWeapon = new ArrayList<>();
        validWeapon.add(WeaponType.AXE);
        validWeapon.add(WeaponType.SWORD);
        validWeapon.add(WeaponType.HAMMER);
    }


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

    @Override
    public void equipItem(Armor armor) throws InvalidArmorException {

        /*
        Checking if Hero's level is lower than that
        of the Armor, and if the Hero can equip armor of this type.
        If not, an exception is throw to inform the user that
        his Hero's level is too low to equip this piece of armor */
        if (getValidArmor() != armor.getArmorType()) {
            throw new InvalidArmorException("Your cannot equip armor of " + armor.getArmorType()+ " .");
        }
        else if (getHeroLevel() < armor.getItemLevel()) {
            throw new InvalidArmorException("Your level is too low. You need to be "
                    + "at least " + armor.getItemLevel() +  " level to be " +
                    "able to equip " + armor.getItemName());
        }
        /* If not, the Hero may equip the piece of armor */
        else {
            getHeroEquipment().put(armor.getSlot(), armor);
            System.out.println("Equipped "+ armor.getItemName());
            levelAttributes.setStrength(getHeroEquipment().get(armor.getSlot()).getStrength());
            levelAttributes.setDexterity(getHeroEquipment().get(armor.getSlot()).getDexterity());
            levelAttributes.setIntelligence(getHeroEquipment().get(armor.getSlot()).getIntelligence());
        }

    }

/* Overridden Methods End */

}
