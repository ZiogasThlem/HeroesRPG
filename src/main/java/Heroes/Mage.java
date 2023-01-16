package Heroes;

import Exceptions.InvalidArmorException;
import Items.*;

import java.util.ArrayList;
import java.util.HashMap;

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
        getLevelAttributes().setStrength(1);
        getLevelAttributes().setDexterity(1);
        getLevelAttributes().setIntelligence(5);

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

    /* Overridden Methods End*/
}

