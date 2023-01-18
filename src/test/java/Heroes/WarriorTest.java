package Heroes;

import Exceptions.InvalidArmorException;
import Exceptions.InvalidWeaponException;
import Heroes.subclasses.Warrior;
import Items.*;
import Items.enums.ArmorType;
import Items.enums.Slot;
import Items.enums.WeaponType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.String.format;

class WarriorTest {


/* Creating Warrior object and all Armor and
 Weapon objects needed for testing */

    Hero warrior = new Warrior("Garrosh Hellscream");

    Weapon bronzeLongblade = new Weapon("Bronze Longblade",1,100, WeaponType.SWORD);
    Weapon ashbringer = new Weapon("Ashbringer",80,1000, WeaponType.SWORD);
    Weapon kings_bane = new Weapon("King's Bane",1,1000, WeaponType.DAGGER);


    Armor bronze_plate_helmet = new Armor("Bronze Plate Helmet", 1, Slot.HEAD,
            new HeroAttributes(4,2,1), ArmorType.PLATE);
    Armor simple_cloth_shirt = new Armor("Simple Cloth Shirt", 1, Slot.BODY,
            new HeroAttributes(1,1,3),ArmorType.CLOTH);
    Armor icebane_breastplate = new Armor("Icebane Breastplate", 60, Slot.BODY,
            new HeroAttributes(200,50,25),ArmorType.PLATE);
    Armor glad_legguards = new Armor("Gladiator's Legguards", 1, Slot.LEGS,
            new HeroAttributes(300,100,50),ArmorType.PLATE);
    Armor apprent_legguards = new Armor("Apprentice's Legguards", 1, Slot.LEGS,
            new HeroAttributes(4,1,1),ArmorType.PLATE);


/* Methods Testing */

    /* Testing equip() for weapons*/
    @Test
    public void test_equip_forWeapons() throws InvalidWeaponException {

        /* Testing by attempting to equip a valid weapon */
        warrior.equipItem(bronzeLongblade);
        Item expected_equipped_weapon = warrior.heroEquipment.get(Slot.WEAPON);

        Assertions.assertEquals(expected_equipped_weapon, bronzeLongblade);

        /* Assertion for Weapon of invalid WeaponType for Warrior Class */
        Assertions.assertThrows(InvalidWeaponException.class, () -> warrior.equipItem(kings_bane) );

        /* Assertion for Weapon of higher ItemLevel of Warrior Class */
        Assertions.assertThrows(InvalidWeaponException.class, () -> warrior.equipItem(ashbringer) );

    }

    /* Testing equip() for armor*/
    @Test
    public void test_equip_forArmor() throws InvalidArmorException {

        /* Testing by attempting to equip a valid piece of armor */
        warrior.equipItem(bronze_plate_helmet);
        Item expected_equipped_armor = warrior.heroEquipment.get(Slot.HEAD);

        Assertions.assertEquals(expected_equipped_armor, bronze_plate_helmet);

        /*Assertion for armor of invalid ArmorType for Warrior Class*/
        Assertions.assertThrows(InvalidArmorException.class, () -> warrior.equipItem(simple_cloth_shirt));

        /*Assertion for armor of higher ItemLevel of warrior */
        Assertions.assertThrows(InvalidArmorException.class, () -> warrior.equipItem(icebane_breastplate));

    }


    /* Testing damage() */
    @Test
    public void test_damage() throws InvalidWeaponException, InvalidArmorException {

        /* From the first part of damage() method, testing damage
        with just the primary attribute through the given equation */
        double expected_damage = (1 + warrior.getPrimary() / 100.0);
        double actual_damage = warrior.damage();

        /*Assertion without weapon */
        Assertions.assertEquals(expected_damage, actual_damage);


        /* equipping weapon and testing */
        warrior.equipItem(bronzeLongblade);
        int weapon_damage = warrior.heroEquipment.get(Slot.WEAPON).getWeaponDamage();
        double expected_with_weapon = expected_damage * weapon_damage;
        double actual_with_weapon = warrior.damage();

        /*Assertion with weapon */
        Assertions.assertEquals(expected_with_weapon, actual_with_weapon);

        /* replacing weapon  and testing again */
        warrior.levelUp(79); // using levelUp() to equip weapon of higher level
        double expected_afterLevelUp = (1 + warrior.getPrimary() / 100.0);
        warrior.equipItem(ashbringer);
        int weapon_damage_withReplacedWeapon = warrior.heroEquipment.get(Slot.WEAPON).getWeaponDamage();
        double expected_withReplacedWeapon = expected_afterLevelUp * weapon_damage_withReplacedWeapon;
        double actual_withReplacedWeapon = warrior.damage();

        /*Assertion with replaced weapon */
        Assertions.assertEquals(expected_withReplacedWeapon, actual_withReplacedWeapon);

        /* equipping piece of armor and testing */
        warrior.equipItem(icebane_breastplate);
        double expected_afterEquippingArmor = (1 + warrior.getPrimary() / 100.0) * weapon_damage_withReplacedWeapon;
        double actual_afterEquippingArmor = warrior.damage();

        /*Assertion with armor equipped */
        Assertions.assertEquals(expected_afterEquippingArmor, actual_afterEquippingArmor);
    }

    /* Testing display() */
    @Test
    public void test_display() {

        /* Forming a multiline string with
        values given from the created object. */
         String expected_display = """
                    Hero name: Garrosh Hellscream
                    Class: Warrior
                    Level: 1
                    Total Strength: 5
                    Total Dexterity: 2
                    Total Intelligence: 1
                    Damage: 1,050000""";

         /* Forming a second multiline string with
        values taken from respective fields */
         String actual_display = format("""
                    Hero name: %s
                    Class: %s
                    Level: %d
                    Total Strength: %d
                    Total Dexterity: %d
                    Total Intelligence: %d
                    Damage: %f""", warrior.heroName,
                    warrior.className, warrior.heroLevel,
                     warrior.levelAttributes.getStrength(), warrior.levelAttributes.getDexterity(),
                     warrior.levelAttributes.getIntelligence(), warrior.damage());

        Assertions.assertEquals(expected_display, actual_display);
    }


    /* Testing totalHeroAttributes() */
    @Test
    void test_totalHeroAttributes() throws InvalidArmorException {

        /* testing without any equipment */
        String expected_withNoEquipment = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                warrior.levelAttributes.getStrength(),
                warrior.levelAttributes.getDexterity(),
                warrior.levelAttributes.getIntelligence());

        String actual_withNoEquipment = warrior.totalHeroAttributes();

        /* Assertion without any equipment */
        Assertions.assertEquals(expected_withNoEquipment, actual_withNoEquipment);


        /* testing by equipping first piece of equipment */
        warrior.equipItem(bronze_plate_helmet);
        String expected_withOnePiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                warrior.levelAttributes.getStrength(),
                warrior.levelAttributes.getDexterity(),
                warrior.levelAttributes.getIntelligence());

        String actual_withOnePiece = warrior.totalHeroAttributes();

        /* Assertion with one piece of equipment */
        Assertions.assertEquals(expected_withOnePiece, actual_withOnePiece);

        /* testing by equipping a second piece of equipment */
        warrior.equipItem(glad_legguards);
        String expected_withTwoPieces = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                warrior.levelAttributes.getStrength(),
                warrior.levelAttributes.getDexterity(),
                warrior.levelAttributes.getIntelligence());

        String actual_withTwoPieces = warrior.totalHeroAttributes();
        /* Assertion with two pieces of equipment */
        Assertions.assertEquals(expected_withTwoPieces, actual_withTwoPieces);


        /* testing by replacing previous piece of equipment */
        warrior.equipItem(apprent_legguards);
        String expected_withReplacedPiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                warrior.levelAttributes.getStrength(),
                warrior.levelAttributes.getDexterity(),
                warrior.levelAttributes.getIntelligence());

        String actual_withReplacedPiece = warrior.totalHeroAttributes();

        /* Assertion with a replaced piece of equipment */
        Assertions.assertEquals(expected_withReplacedPiece, actual_withReplacedPiece);

    }

    /* Testing levelUP() */
    @Test
    public void test_levelUP(){

        /* Testing in a similar way with Setter methods of HeroAttributesTest.
        Getting the original value from getter to expected, adding (or subtracting)
        n to original value , adding (or subtracting) n to expected_afterLevelUp and multiplying it
        by the amount define by attributesPerLevel Array for each class. */

        int expectedStrength= warrior.levelAttributes.getStrength();
        int expectedDexterity = warrior.levelAttributes.getDexterity();
        int expectedIntelligence = warrior.levelAttributes.getIntelligence();

        warrior.levelUp(10);

        int expectedLevel_afterLevelUp = warrior.heroLevel += 10;
        int actualLevel = warrior.getHeroLevel();

        int expectedStrength_afterLevelUp = expectedStrength + 10 * warrior.attributesPerLevel[0];
        int actualStrength = warrior.getLevelAttributes().getStrength();

        int expectedDexterity_afterLevelUp = expectedDexterity + 10 * warrior.attributesPerLevel[1];
        int actualDexterity = warrior.getLevelAttributes().getDexterity();

        int expectedIntelligence_afterLevelUp = expectedIntelligence + 10 * warrior.attributesPerLevel[2];
        int actualIntelligence = warrior.getLevelAttributes().getIntelligence();


        Assertions.assertEquals(expectedLevel_afterLevelUp, actualLevel);
        Assertions.assertEquals(expectedStrength_afterLevelUp, actualStrength);
        Assertions.assertEquals(expectedDexterity_afterLevelUp, actualDexterity);
        Assertions.assertEquals(expectedIntelligence_afterLevelUp, actualIntelligence);

    }

/* Methods Testing End */

/* Getters and Setters Testing */

    /* Testing getHeroName() */
    @Test
    public void test_GetHeroName(){

        String expected = warrior.heroName;
        String actual = warrior.getHeroName();
        Assertions.assertEquals(expected, actual);
    }

    /* Testing getHeroClassName() */
    @Test
    public void test_GetHeroClassName() {

        String expected = warrior.className;
        String actual = warrior.getClassName();
        Assertions.assertEquals(expected, actual);

    }

    /* Testing getHeroLevel() */
    @Test
    public void test_GetHeroLevel() {

        int expected = warrior.heroLevel;
        int actual = warrior.getHeroLevel();
        Assertions.assertEquals(expected, actual);
    }

    /* Testing increaseHeroLevel() */
    @Test
    public void test_IncreaseHeroLevel() {

        int expected = warrior.heroLevel;
        warrior.increaseHeroLevel(7);
        int expected2 = expected + 7;
        int actual = warrior.getHeroLevel();

        Assertions.assertEquals(expected2, actual);
    }

    /* Testing getHeroEquipment() */
    @Test
    public void test_GetHeroEquipment() throws InvalidArmorException, InvalidWeaponException {

        /* First test with empty equipment */
        HashMap<Slot, Item> expected_equipment = warrior.heroEquipment;
        HashMap<Slot, Item> actual_equipment = warrior.getHeroEquipment();

        /* Assertion without equipment */
        Assertions.assertEquals(expected_equipment, actual_equipment);


        /* Second test with full set of valid equipment
        with created Armor and Weapon objects*/
        HashMap<Slot, Item> expected_equipment_afterEquipping = new HashMap<>();
        expected_equipment_afterEquipping.put(Slot.HEAD,bronze_plate_helmet);
        expected_equipment_afterEquipping.put(Slot.BODY,icebane_breastplate);
        expected_equipment_afterEquipping.put(Slot.LEGS,glad_legguards);
        expected_equipment_afterEquipping.put(Slot.WEAPON,ashbringer);

        warrior.levelUp(100);  // using levelUp() to equip higher level Items
        warrior.equipItem(icebane_breastplate);
        warrior.equipItem(bronze_plate_helmet);
        warrior.equipItem(glad_legguards);
        warrior.equipItem(ashbringer);

        HashMap<Slot, Item> actual_equipment_afterEquipping = warrior.getHeroEquipment();

        /* Assertion with equipment */
        Assertions.assertEquals(expected_equipment_afterEquipping, actual_equipment_afterEquipping);
    }

    /* Testing getValidArmor() */
    @Test
    public void test_GetValidArmor() {

        ArmorType armorType_expected = warrior.validArmor;
        ArmorType armorType_actual = warrior.getValidArmor();

        Assertions.assertEquals(armorType_expected, armorType_actual);
    }

    /* Testing getValidWeapon() */
    @Test
    public void test_GetValidWeapon() {

        ArrayList<WeaponType> weaponTypes_expected = warrior.validWeapon;
        ArrayList<WeaponType> weaponTypes_actual = warrior.getValidWeapon();

        Assertions.assertEquals(weaponTypes_expected, weaponTypes_actual);
    }

    /* Testing getLevelAttributes() */
    @Test
    public void test_GetLevelAttributes() {

        HeroAttributes heroAttributes_expected = warrior.levelAttributes;
        HeroAttributes heroAttributes_actual = warrior.getLevelAttributes();

        Assertions.assertEquals(heroAttributes_expected, heroAttributes_actual);
    }

    /* Testing getPrimary() */
    @Test
    public void test_GetPrimary() {

        int expected_primary = Math.max(Math.max(
                        warrior.getLevelAttributes().getStrength(),
                        warrior.getLevelAttributes().getDexterity()),
                        warrior.getLevelAttributes().getIntelligence());

        int actual_primary = warrior.getPrimary();

        Assertions.assertEquals(expected_primary, actual_primary);

    }

/* Getters and Setters Testing End */


}