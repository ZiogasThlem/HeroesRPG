package Heroes;

import Exceptions.InvalidArmorException;
import Items.*;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.String.format;

public class Rogue extends Hero{


/* Overridden Fields with specified values */
    private final ArmorType validArmor = ArmorType.LEATHER;
    private final ArrayList<WeaponType> validWeapon;
    {
        validWeapon = new ArrayList<>();
        validWeapon.add(WeaponType.DAGGER);
        validWeapon.add(WeaponType.SWORD);
    }
    private final HeroAttributes levelAttributes = new HeroAttributes(2,6,1);
/* Overridden Fields End */


/* Inherited Constructor */
    public Rogue(String heroName) {
        super(heroName);
    }


/* Overridden Methods */

    public void levelUp(int levelsGained) {

        super.increaseHeroLevel(levelsGained);
        getLevelAttributes().increaseStrength(levelsGained);
        getLevelAttributes().increaseDexterity(4*levelsGained);
        getLevelAttributes().increaseIntelligence(levelsGained);

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

        return format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                getLevelAttributes().getStrength(), getLevelAttributes().getDexterity(),
                getLevelAttributes().getIntelligence());
    }

    @Override
    public ArrayList<WeaponType> getValidWeapon() {
        return validWeapon;
    }
    @Override
    public HashMap<Slot, Item> getHeroEquipment() {
        return heroEquipment;
    }

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

            if (getHeroEquipment().get(armor.getSlot())!=null){
                levelAttributes.decreaseStrength(getHeroEquipment().get(armor.getSlot()).getStrength());
                levelAttributes.decreaseDexterity(getHeroEquipment().get(armor.getSlot()).getDexterity());
                levelAttributes.decreaseIntelligence(getHeroEquipment().get(armor.getSlot()).getIntelligence());
            }

            getHeroEquipment().put(armor.getSlot(), armor);
            System.out.println("Equipped "+ armor.getItemName());
            levelAttributes.increaseStrength(getHeroEquipment().get(armor.getSlot()).getStrength());
            levelAttributes.increaseDexterity(getHeroEquipment().get(armor.getSlot()).getDexterity());
            levelAttributes.increaseIntelligence(getHeroEquipment().get(armor.getSlot()).getIntelligence());
        }

    }


    /* Overridden Methods End */

}
