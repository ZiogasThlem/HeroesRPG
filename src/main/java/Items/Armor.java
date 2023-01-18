package Items;

import Heroes.HeroAttributes;
import Items.enums.ArmorType;
import Items.enums.Slot;

public class Armor extends Item{


/* Inherited Constructor with
ArmorAttributes Armor specific field */
    public Armor(String itemName, int levelRequired, Slot slot,
                 HeroAttributes armorAttributes, ArmorType armorType) {

        super(itemName, levelRequired, slot, armorType);
        /* ArmorAttributes are specified when Armor is created */
        this.armorAttributes = armorAttributes;

    }


}


