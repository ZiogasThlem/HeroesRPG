package Items;

import Heroes.HeroAttributes;

public class Armor extends Item{

/* Armor specific Fields */
    private final HeroAttributes armorAttributes;


/* Constructor */
    public Armor(String itemName, int levelRequired, Slot slot,
                 HeroAttributes armorAttributes,ArmorType armorType) {
        super(itemName, levelRequired, slot, armorType);
        this.armorAttributes = armorAttributes;
        //add armor type
    }

/* Setters and Getters */

    @Override
    public HeroAttributes getArmorAttributes() {
        return armorAttributes;
    }

    @Override
    public int getStrength() {
        return armorAttributes.getStrength();
    }

    @Override
    public int getDexterity() {
        return armorAttributes.getDexterity();
    }

    @Override
    public int getIntelligence() {
        return armorAttributes.getIntelligence();
    }

/* Setters and Getters End*/


}


