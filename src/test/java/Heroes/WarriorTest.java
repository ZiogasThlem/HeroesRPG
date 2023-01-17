package Heroes;

import Exceptions.InvalidArmorException;
import Exceptions.InvalidWeaponException;
import Items.*;
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


    Armor bronze_plate_chest = new Armor("Bronze Plate Chest", 1, Slot.BODY,
            new HeroAttributes(4,2,1),ArmorType.PLATE);
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


        warrior.equipItem(bronzeLongblade);
        Item expected_equipped_weapon = warrior.getHeroEquipment().get(Slot.WEAPON);

        Assertions.assertEquals(expected_equipped_weapon, bronzeLongblade);

        /* Assertion for Weapon of invalid WeaponType for Warrior Class */
        Assertions.assertThrows(InvalidWeaponException.class, () -> warrior.equipItem(kings_bane) );

        /* Assertion for Weapon of higher ItemLevel of Warrior Class */
        Assertions.assertThrows(InvalidWeaponException.class, () -> warrior.equipItem(ashbringer) );

    }

    /* Testing equip() for armor*/
    @Test
    public void test_equip_forArmor() throws InvalidArmorException {

        warrior.equipItem(bronze_plate_chest);
        Item expected_equipped_armor = warrior.getHeroEquipment().get(Slot.BODY);

        Assertions.assertEquals(expected_equipped_armor, bronze_plate_chest);

        /*Assertion for armor of invalid ArmorType for Warrior Class*/
        Assertions.assertThrows(InvalidArmorException.class, () -> warrior.equipItem(simple_cloth_shirt));

        /*Assertion for armor of higher ItemLevel of warrior */
        Assertions.assertThrows(InvalidArmorException.class, () -> warrior.equipItem(icebane_breastplate));

    }


    /* Testing damage() */
    @Test
    public void test_damage() throws InvalidWeaponException, InvalidArmorException {

        double expected_damage = (1 + warrior.getPrimary() / 100.0);

        double actual_damage = warrior.damage();

        /*Assertion without weapon */
        Assertions.assertEquals(expected_damage, actual_damage);


        warrior.equipItem(bronzeLongblade);
        int weapon_damage = warrior.getHeroEquipment().get(Slot.WEAPON).getWeaponDamage();
        double expected_with_weapon = expected_damage * weapon_damage;
        double actual_with_weapon = warrior.damage();

        /*Assertion with weapon */
        Assertions.assertEquals(expected_with_weapon, actual_with_weapon);


        warrior.levelUp(79); // using levelUp() to equip weapon of higher level
        double expected_afterLevelUp = (1 + warrior.getPrimary() / 100.0);
        warrior.equipItem(ashbringer);
        int weapon_damage_withReplacedWeapon = warrior.getHeroEquipment().get(Slot.WEAPON).getWeaponDamage();
        double expected_withReplacedWeapon = expected_afterLevelUp * weapon_damage_withReplacedWeapon;
        double actual_withReplacedWeapon = warrior.damage();

        /*Assertion with replaced weapon */
        Assertions.assertEquals(expected_withReplacedWeapon, actual_withReplacedWeapon);

        warrior.equipItem(icebane_breastplate);
        double expected_afterEquippingArmor = (1 + warrior.getPrimary() / 100.0) * weapon_damage_withReplacedWeapon;
        double actual_afterEquippingArmor = warrior.damage();

        /*Assertion with armor equipped */
        Assertions.assertEquals(expected_afterEquippingArmor, actual_afterEquippingArmor);
    }

    /* Testing display() */
    @Test
    public void test_display() {

     String expected_display = format("""
                    Hero name: %s
                    Class: %s
                    Level: %d
                    Total Strength: %d
                    Total Dexterity: %d
                    Total Intelligence: %d
                    Damage: %f""", "Garrosh Hellscream", "Warrior",
                    1,5,2,1, (1 + warrior.getPrimary() / 100.0));

     String actual_display = format("""
                    Hero name: %s
                    Class: %s
                    Level: %d
                    Total Strength: %d
                    Total Dexterity: %d
                    Total Intelligence: %d
                    Damage: %f""", warrior.getHeroName(),
                    warrior.getClassName(), warrior.getHeroLevel(),
                     warrior.getLevelAttributes().getStrength(), warrior.getLevelAttributes().getDexterity(),
                     warrior.getLevelAttributes().getIntelligence(), warrior.damage());

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
                5, 2, 1);

        String actual_withNoEquipment = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                warrior.getLevelAttributes().getStrength(),
                warrior.getLevelAttributes().getDexterity(),
                warrior.getLevelAttributes().getIntelligence());

        /* Assertion without any equipment */
        Assertions.assertEquals(expected_withNoEquipment, actual_withNoEquipment);


        warrior.equipItem(bronze_plate_chest);
        String expected_withOnePiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                9, 4, 2);

        String actual_withOnePiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                warrior.getLevelAttributes().getStrength(),
                warrior.getLevelAttributes().getDexterity(),
                warrior.getLevelAttributes().getIntelligence());
        /* Assertion with one piece of equipment */
        Assertions.assertEquals(expected_withOnePiece, actual_withOnePiece);
        //two pieces of equipment

        warrior.equipItem(glad_legguards);
        String expected_withTwoPieces = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                309, 104, 52);

        String actual_withTwoPieces = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                warrior.getLevelAttributes().getStrength(),
                warrior.getLevelAttributes().getDexterity(),
                warrior.getLevelAttributes().getIntelligence());

        /* Assertion with two pieces of equipment */
        Assertions.assertEquals(expected_withTwoPieces, actual_withTwoPieces);


        warrior.equipItem(apprent_legguards);
        String expected_withReplacedPiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                13, 5, 3);

        String actual_withReplacedPiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                warrior.getLevelAttributes().getStrength(),
                warrior.getLevelAttributes().getDexterity(),
                warrior.getLevelAttributes().getIntelligence());

        /* Assertion with a replaced piece of equipment */
        Assertions.assertEquals(expected_withReplacedPiece, actual_withReplacedPiece);

    }

    /* Testing levelUP() */
    @Test
    public void test_levelUP(){

        warrior.levelUp(10);

        int expectedLevel = 11;
        int actualLevel = warrior.getHeroLevel();

        int expectedStrength = 35;
        int actualStrength = warrior.getLevelAttributes().getStrength();

        int expectedDexterity = 22;
        int actualDexterity = warrior.getLevelAttributes().getDexterity();

        int expectedIntelligence = 11;
        int actualIntelligence = warrior.getLevelAttributes().getIntelligence();


        Assertions.assertEquals(expectedLevel, actualLevel);
        Assertions.assertEquals(expectedStrength, actualStrength);
        Assertions.assertEquals(expectedDexterity, actualDexterity);
        Assertions.assertEquals(expectedIntelligence, actualIntelligence);

    }

/* Methods Testing End */

/* Getters and Setters Testing */

    @Test
    public void test_GetHeroName(){

        String expected = "Garrosh Hellscream";
        String actual = warrior.getHeroName();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_GetHeroClassName() {

        String expected = "Warrior";
        String actual = warrior.getClassName();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void test_GetHeroLevel() {

        int expected = 1;
        int actual = warrior.getHeroLevel();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_IncreaseHeroLevel() {

        warrior.increaseHeroLevel(4);
        int expected = 5;
        int actual = warrior.getHeroLevel();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_GetHeroEquipment() {

        HashMap<Slot, Item> expected_equipment = new HashMap<>();
        expected_equipment.put(Slot.HEAD, null);
        expected_equipment.put(Slot.BODY, null);
        expected_equipment.put(Slot.LEGS, null);
        expected_equipment.put(Slot.WEAPON, null);

        HashMap<Slot, Item> actual_equipment = warrior.getHeroEquipment();

        Assertions.assertEquals(expected_equipment, actual_equipment);
    }

    @Test
    public void test_GetValidArmor() {

        ArmorType armorType_expected = ArmorType.PLATE;
        ArmorType armorType_actual = warrior.getValidArmor();

        Assertions.assertEquals(armorType_expected, armorType_actual);
    }

    @Test
    public void test_GetValidWeapon() {

        ArrayList<WeaponType> weaponTypes_expected = new ArrayList<>();
        weaponTypes_expected.add(WeaponType.AXE);
        weaponTypes_expected.add(WeaponType.SWORD);
        weaponTypes_expected.add(WeaponType.HAMMER);

        ArrayList<WeaponType> weaponTypes_actual = warrior.getValidWeapon();

        Assertions.assertEquals(weaponTypes_expected, weaponTypes_actual);
    }
    @Test
    public void test_GetLevelAttributes() {

        HeroAttributes heroAttributes_expected = new HeroAttributes(5,2,1);
        HeroAttributes heroAttributes_actual = warrior.getLevelAttributes();

        Assertions.assertEquals(heroAttributes_expected, heroAttributes_actual);
    }

    @Test
    public void test_GetPrimary() {

        int expected_primary = 5;
        int actual_primary = Math.max(Math.max(
                warrior.getLevelAttributes().getStrength(),
                warrior.getLevelAttributes().getDexterity()),
                warrior.getLevelAttributes().getIntelligence());

        Assertions.assertEquals(expected_primary, actual_primary);

    }

/* Getters and Setters Testing End */


}