package Items;

import Heroes.HeroAttributes;
import Items.enums.ArmorType;
import Items.enums.Slot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArmorTest {

    Armor test_armor = new Armor("Test Armor",10, Slot.BODY,new HeroAttributes(100,100,100), ArmorType.PLATE);


/* Testing Getters */

    /* Testing getArmorAttributes() */
    @Test
    public void test_GetArmorAttributes(){

        HeroAttributes armorAttributes_expected = test_armor.armorAttributes;
        HeroAttributes armorAttributes_actual = test_armor.getArmorAttributes();

        Assertions.assertEquals(armorAttributes_expected, armorAttributes_actual);
    }

    /* Testing getStrength() */
    @Test
    public void test_GetStrength(){

        int strength_expected = test_armor.armorAttributes.getStrength();
        int strength_actual = test_armor.getArmorAttributes().getStrength();

        Assertions.assertEquals(strength_expected, strength_actual);
    }

    /* Testing getDexterity() */
    @Test
    public void test_GetDexterity(){

        int dexterity_expected = test_armor.armorAttributes.getDexterity();
        int dexterity_actual = test_armor.getArmorAttributes().getDexterity();

        Assertions.assertEquals(dexterity_expected, dexterity_actual);
    }

    /* Testing getIntelligence() */
    @Test
    public void test_GetIntelligence(){

        int intelligence_expected = test_armor.armorAttributes.getIntelligence();
        int intelligence_actual = test_armor.getArmorAttributes().getIntelligence();

        Assertions.assertEquals(intelligence_expected, intelligence_actual);
    }

    /* Testing getItemName() */
    @Test
    public void test_GetItemName() {

        String expected_name = test_armor.itemName;
        String actual_name = test_armor.getItemName();
        Assertions.assertEquals(expected_name, actual_name);
    }

    /* Testing getItemSlot() */
    @Test
    public void test_GetItemSlot() {

        Slot expected_slot = test_armor.slot;
        Slot actual_slot = test_armor.getSlot();

        Assertions.assertEquals(expected_slot, actual_slot);
    }

    /* Testing getItemLevel() */
    @Test
    public void test_GetItemLevel() {

        int expected_level = test_armor.itemLevel;
        int actual_level = test_armor.getItemLevel();

        Assertions.assertEquals(expected_level, actual_level);
    }

    /* Testing getIetArmorType() */
    @Test
    public void test_GetArmorType() {

        ArmorType expected_armorType = test_armor.armorType;
        ArmorType actual_armorType = test_armor.getArmorType();

        Assertions.assertEquals(expected_armorType, actual_armorType);
    }




}