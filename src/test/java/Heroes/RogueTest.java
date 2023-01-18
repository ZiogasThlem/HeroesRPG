package Heroes;

import Exceptions.InvalidArmorException;
import Exceptions.InvalidWeaponException;
import Items.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.String.format;

class RogueTest {


/* Creating Warrior object and all Armor and
 Weapon objects needed for testing */

    Hero rogue = new Rogue("Valeera Sanguinar");

    Weapon steel_rappier = new Weapon("Steel rapierr",1, 8, WeaponType.SWORD);
    Weapon stardust_wand = new Weapon("Stardust Wand",1,5, WeaponType.WAND);

    Weapon wolf_fangs = new Weapon("Wolf Fangs",50,500,WeaponType.DAGGER);

    Armor wildboar_pelt = new Armor("Wildboar Pelt",1,Slot.BODY,new HeroAttributes(3,10,1),ArmorType.LEATHER);
    Armor dragonscale_chestguard = new Armor("Dragonscale Chestguard",1,Slot.BODY,new HeroAttributes(5,12,3),ArmorType.MAIL);
    Armor cursed_eyepatch = new Armor("Cursed Eyepatch",90,Slot.HEAD,new HeroAttributes(30,100,10),ArmorType.LEATHER);
    Armor bloodfang_pants = new Armor("Bloodfang Pants",1,Slot.LEGS,new HeroAttributes(10,20,5),ArmorType.LEATHER);
    Armor grizzly_pants = new Armor("Grizzly Pants",1,Slot.LEGS,new HeroAttributes(8,15,3),ArmorType.LEATHER);


    /* Methods Testing */

    /* Testing equip() for weapons*/
    @Test
    public void test_equip_forWeapons() throws InvalidWeaponException {


        rogue.equipItem(steel_rappier);
        Item expected_equipped_weapon = rogue.heroEquipment.get(Slot.WEAPON);

        Assertions.assertEquals(expected_equipped_weapon, steel_rappier);

        /* Assertion for Weapon of invalid WeaponType for Ranger Class */
        Assertions.assertThrows(InvalidWeaponException.class, () -> rogue.equipItem(stardust_wand) );

        /* Assertion for Weapon of higher ItemLevel of Ranger Class */
        Assertions.assertThrows(InvalidWeaponException.class, () -> rogue.equipItem(wolf_fangs) );

    }

    /* Testing equip() for armor*/
    @Test
    public void test_equip_forArmor() throws InvalidArmorException {

        rogue.equipItem(wildboar_pelt);
        Item expected_equipped_armor = rogue.heroEquipment.get(Slot.BODY);

        Assertions.assertEquals(expected_equipped_armor, wildboar_pelt);

        /*Assertion for armor of invalid ArmorType for Warrior Class*/
        Assertions.assertThrows(InvalidArmorException.class, () -> rogue.equipItem(dragonscale_chestguard));

        /*Assertion for armor of higher ItemLevel of warrior */
        Assertions.assertThrows(InvalidArmorException.class, () -> rogue.equipItem(cursed_eyepatch));

    }


    /* Testing damage() */
    @Test
    public void test_damage() throws InvalidWeaponException, InvalidArmorException {

        double expected_damage = (1 + rogue.getPrimary() / 100.0);
        double actual_damage = rogue.damage();

        /*Assertion without weapon */
        Assertions.assertEquals(expected_damage, actual_damage);


        rogue.equipItem(steel_rappier);
        int weapon_damage = rogue.heroEquipment.get(Slot.WEAPON).getWeaponDamage();
        double expected_with_weapon = expected_damage * weapon_damage;
        double actual_with_weapon = rogue.damage();

        /*Assertion with weapon */
        Assertions.assertEquals(expected_with_weapon, actual_with_weapon);


        rogue.levelUp(89); // using levelUp() to equip weapon of higher level
        double expected_afterLevelUp = (1 + rogue.getPrimary() / 100.0);
        rogue.equipItem(wolf_fangs);
        int weapon_damage_withReplacedWeapon = rogue.heroEquipment.get(Slot.WEAPON).getWeaponDamage();
        double expected_withReplacedWeapon = expected_afterLevelUp * weapon_damage_withReplacedWeapon;
        double actual_withReplacedWeapon = rogue.damage();

        /*Assertion with replaced weapon */
        Assertions.assertEquals(expected_withReplacedWeapon, actual_withReplacedWeapon);

        rogue.equipItem(cursed_eyepatch);
        double expected_afterEquippingArmor = (1 + rogue.getPrimary() / 100.0) * weapon_damage_withReplacedWeapon;
        double actual_afterEquippingArmor = rogue.damage();

        /*Assertion with armor equipped */
        Assertions.assertEquals(expected_afterEquippingArmor, actual_afterEquippingArmor);
    }

    /* Testing display() */
    @Test
    public void test_display() {

        String expected_display = """
                    Hero name: Valeera Sanguinar
                    Class: Rogue
                    Level: 1
                    Total Strength: 2
                    Total Dexterity: 6
                    Total Intelligence: 1
                    Damage: 1,060000""";

        String actual_display = format("""
                    Hero name: %s
                    Class: %s
                    Level: %d
                    Total Strength: %d
                    Total Dexterity: %d
                    Total Intelligence: %d
                    Damage: %f""", rogue.heroName,
                rogue.className, rogue.heroLevel,
                rogue.levelAttributes.getStrength(), rogue.levelAttributes.getDexterity(),
                rogue.levelAttributes.getIntelligence(), rogue.damage());

        Assertions.assertEquals(expected_display, actual_display);
    }


    /* Testing totalHeroAttributes() */
    @Test
    void test_totalHeroAttributes() throws InvalidArmorException {


        String expected_withNoEquipment = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                rogue.levelAttributes.getStrength(),
                rogue.levelAttributes.getDexterity(),
                rogue.levelAttributes.getIntelligence());

        String actual_withNoEquipment = rogue.totalHeroAttributes();

        /* Assertion without any equipment */
        Assertions.assertEquals(expected_withNoEquipment, actual_withNoEquipment);


        rogue.equipItem(wildboar_pelt);
        String expected_withOnePiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                rogue.levelAttributes.getStrength(),
                rogue.levelAttributes.getDexterity(),
                rogue.levelAttributes.getIntelligence());

        String actual_withOnePiece = rogue.totalHeroAttributes();

        /* Assertion with one piece of equipment */
        Assertions.assertEquals(expected_withOnePiece, actual_withOnePiece);


        rogue.equipItem(bloodfang_pants);
        String expected_withTwoPieces = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                rogue.levelAttributes.getStrength(),
                rogue.levelAttributes.getDexterity(),
                rogue.levelAttributes.getIntelligence());

        String actual_withTwoPieces = rogue.totalHeroAttributes();

        /* Assertion with two pieces of equipment */
        Assertions.assertEquals(expected_withTwoPieces, actual_withTwoPieces);


        rogue.equipItem(grizzly_pants);
        String expected_withReplacedPiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                rogue.levelAttributes.getStrength(),
                rogue.levelAttributes.getDexterity(),
                rogue.levelAttributes.getIntelligence());

        String actual_withReplacedPiece = rogue.totalHeroAttributes();

        /* Assertion with a replaced piece of equipment */
        Assertions.assertEquals(expected_withReplacedPiece, actual_withReplacedPiece);

    }

    /* Testing levelUP() */
    @Test
    public void test_levelUP(){


        int expectedStrength= rogue.levelAttributes.getStrength();
        int expectedDexterity = rogue.levelAttributes.getDexterity();
        int expectedIntelligence = rogue.levelAttributes.getIntelligence();

        rogue.levelUp(10);

        int expectedLevel_afterLevelUp = rogue.heroLevel += 10;
        int actualLevel = rogue.getHeroLevel();

        int expectedStrength_afterLevelUp = expectedStrength + 10 * rogue.attributesPerLevel[0];
        int actualStrength = rogue.getLevelAttributes().getStrength();

        int expectedDexterity_afterLevelUp = expectedDexterity + 10 * rogue.attributesPerLevel[1];
        int actualDexterity = rogue.getLevelAttributes().getDexterity();

        int expectedIntelligence_afterLevelUp = expectedIntelligence + 10 * rogue.attributesPerLevel[2];
        int actualIntelligence = rogue.getLevelAttributes().getIntelligence();


        Assertions.assertEquals(expectedLevel_afterLevelUp, actualLevel);
        Assertions.assertEquals(expectedStrength_afterLevelUp, actualStrength);
        Assertions.assertEquals(expectedDexterity_afterLevelUp, actualDexterity);
        Assertions.assertEquals(expectedIntelligence_afterLevelUp, actualIntelligence);

    }

    /* Methods Testing End */

    /* Getters and Setters Testing */

    @Test
    public void test_GetHeroName(){

        String expected = rogue.heroName;
        String actual = rogue.getHeroName();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_GetHeroClassName() {

        String expected = rogue.className;
        String actual = rogue.getClassName();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void test_GetHeroLevel() {

        int expected = rogue.heroLevel;
        int actual = rogue.getHeroLevel();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_IncreaseHeroLevel() {

        int expected = rogue.heroLevel;
        rogue.increaseHeroLevel(4);
        int expected2 = expected + 4;
        int actual = rogue.getHeroLevel();

        Assertions.assertEquals(expected2, actual);
    }

    @Test
    public void test_GetHeroEquipment() throws InvalidArmorException, InvalidWeaponException {

        HashMap<Slot, Item> expected_equipment = rogue.heroEquipment;
        HashMap<Slot, Item> actual_equipment = rogue.getHeroEquipment();

        Assertions.assertEquals(expected_equipment, actual_equipment);


        HashMap<Slot, Item> expected_equipment_afterEquipping = new HashMap<>();
        expected_equipment_afterEquipping.put(Slot.HEAD,cursed_eyepatch);
        expected_equipment_afterEquipping.put(Slot.BODY,wildboar_pelt);
        expected_equipment_afterEquipping.put(Slot.LEGS,bloodfang_pants);
        expected_equipment_afterEquipping.put(Slot.WEAPON,wolf_fangs);

        rogue.levelUp(100); // using levelUp() to equip higher level Items
        rogue.equipItem(wildboar_pelt);
        rogue.equipItem(cursed_eyepatch);
        rogue.equipItem(bloodfang_pants);
        rogue.equipItem(wolf_fangs);

        HashMap<Slot, Item> actual_equipment_afterEquipping = rogue.getHeroEquipment();
        Assertions.assertEquals(expected_equipment_afterEquipping, actual_equipment_afterEquipping);
    }

    @Test
    public void test_GetValidArmor() {

        ArmorType armorType_expected = rogue.validArmor;
        ArmorType armorType_actual = rogue.getValidArmor();

        Assertions.assertEquals(armorType_expected, armorType_actual);
    }

    @Test
    public void test_GetValidWeapon() {

        ArrayList<WeaponType> weaponTypes_expected = rogue.validWeapon;
        ArrayList<WeaponType> weaponTypes_actual = rogue.getValidWeapon();

        Assertions.assertEquals(weaponTypes_expected, weaponTypes_actual);
    }
    @Test
    public void test_GetLevelAttributes() {

        HeroAttributes heroAttributes_expected = rogue.levelAttributes;
        HeroAttributes heroAttributes_actual = rogue.getLevelAttributes();

        Assertions.assertEquals(heroAttributes_expected, heroAttributes_actual);
    }

    @Test
    public void test_GetPrimary() {

        int expected_primary = Math.max(Math.max(
                        rogue.getLevelAttributes().getStrength(),
                        rogue.getLevelAttributes().getDexterity()),
                        rogue.getLevelAttributes().getIntelligence());

        int actual_primary = rogue.getPrimary();

        Assertions.assertEquals(expected_primary, actual_primary);

    }

/* Getters and Setters Testing End */


}