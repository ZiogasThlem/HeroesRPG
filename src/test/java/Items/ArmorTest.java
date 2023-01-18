package Items;

import Heroes.HeroAttributes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArmorTest {

    Armor test_armor = new Armor("Test Armor",10, Slot.BODY,new HeroAttributes(100,100,100),ArmorType.PLATE);


/* Testing Getters */

    @Test
    public void test_GetArmorAttributes(){

        HeroAttributes armorAttributes_expected = new HeroAttributes(100,100,100);
        HeroAttributes armorAttributes_actual = test_armor.getArmorAttributes();

        Assertions.assertEquals(armorAttributes_expected, armorAttributes_actual);
    }

    @Test
    public void test_GetStrength(){

        int strength_expected = test_armor.armorAttributes.getStrength();
        int strength_actual = test_armor.getArmorAttributes().getStrength();

        Assertions.assertEquals(strength_expected, strength_actual);
    }

    @Test
    public void test_GetDexterity(){

        int dexterity_expected = test_armor.armorAttributes.getDexterity();
        int dexterity_actual = test_armor.getArmorAttributes().getDexterity();

        Assertions.assertEquals(dexterity_expected, dexterity_actual);
    }

    @Test
    public void test_GetIntelligence(){

        int intelligence_expected = test_armor.armorAttributes.getIntelligence();
        int intelligence_actual = test_armor.getArmorAttributes().getIntelligence();

        Assertions.assertEquals(intelligence_expected, intelligence_actual);
    }

    @Test
    public void test_GetItemName() {

        String expected_name = test_armor.itemName;
        String actual_name = test_armor.getItemName();
        Assertions.assertEquals(expected_name, actual_name);
    }

    @Test
    public void test_GetItemSlot() {

        Slot expected_slot = test_armor.slot;
        Slot actual_slot = test_armor.getSlot();

        Assertions.assertEquals(expected_slot, actual_slot);
    }

    @Test
    public void test_GetItemLevel() {

        int expected_level = 10;
        int actual_level = test_armor.getItemLevel();

        Assertions.assertEquals(expected_level, actual_level);
    }


    @Test
    public void test_GetItemType() {

        ArmorType expected_armorType = ArmorType.PLATE;
        ArmorType actual_armorType = test_armor.getArmorType();

        Assertions.assertEquals(expected_armorType, actual_armorType);
    }




}