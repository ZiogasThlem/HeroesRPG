package Items;

import Heroes.HeroAttributes;

public class Armor extends Item{


/* Constructor */
    public Armor(String itemName, int levelRequired, Slot slot,
                 HeroAttributes armorAttributes,ArmorType armorType) {
        super(itemName, levelRequired, slot, armorType);
        this.armorAttributes = armorAttributes;

    }


}


