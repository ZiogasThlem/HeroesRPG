package Heroes;

import Exceptions.InvalidArmorException;
import Exceptions.InvalidWeaponException;
import Items.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.String.format;

class RangerTest {


/* Creating Warrior object and all Armor and
 Weapon objects needed for testing */

    Hero ranger = new Ranger("Sylvanas Windrunner");

    Weapon training_bow = new Weapon("Training Bow",1, 10, WeaponType.BOW);
    Weapon blacksmiths_hammer = new Weapon("Blacksmith's Hammer", 1, 10, WeaponType.HAMMER);
    Weapon deaths_whisper = new Weapon("Death's Whisper",60, 1000, WeaponType.BOW);

    Armor trainers_mail_chestpiece = new Armor("Trainer's Mail Chestpiece", 1, Slot.BODY,
            new HeroAttributes(2,8,1),ArmorType.MAIL);

    Armor cryptstalkers_chestguard = new Armor("Cryptstalker's Chestguard", 100, Slot.BODY,
            new HeroAttributes(200,2500,100),ArmorType.MAIL);

    Armor steel_helmet = new Armor("Steel Helmet", 1, Slot.BODY,
            new HeroAttributes(20,8,7),ArmorType.PLATE);

    Armor chainmail_legguards = new Armor("Chain Mail Legguards", 1, Slot.LEGS,
            new HeroAttributes(3,18,4),ArmorType.MAIL);

    Armor  huntsmans_greaves = new Armor("Huntsman's Greaves", 1, Slot.LEGS,
            new HeroAttributes(5,16,4),ArmorType.MAIL);




    /* Methods Testing */

    /* Testing equip() for weapons*/
    @Test
    public void test_equip_forWeapons() throws InvalidWeaponException {


        ranger.equipItem(training_bow);
        Item expected_equipped_weapon = ranger.getHeroEquipment().get(Slot.WEAPON);

        Assertions.assertEquals(expected_equipped_weapon, training_bow);

        /* Assertion for Weapon of invalid WeaponType for Ranger Class */
        Assertions.assertThrows(InvalidWeaponException.class, () -> ranger.equipItem(blacksmiths_hammer) );

        /* Assertion for Weapon of higher ItemLevel of Ranger Class */
        Assertions.assertThrows(InvalidWeaponException.class, () -> ranger.equipItem(deaths_whisper) );

    }

    /* Testing equip() for armor*/
    @Test
    public void test_equip_forArmor() throws InvalidArmorException {

        ranger.equipItem(trainers_mail_chestpiece);
        Item expected_equipped_armor = ranger.getHeroEquipment().get(Slot.BODY);

        Assertions.assertEquals(expected_equipped_armor, trainers_mail_chestpiece);

        /*Assertion for armor of invalid ArmorType for Warrior Class*/
        Assertions.assertThrows(InvalidArmorException.class, () -> ranger.equipItem(steel_helmet));

        /*Assertion for armor of higher ItemLevel of warrior */
        Assertions.assertThrows(InvalidArmorException.class, () -> ranger.equipItem(cryptstalkers_chestguard));

    }


    /* Testing damage() */
    @Test
    public void test_damage() throws InvalidWeaponException, InvalidArmorException {

        double expected_damage = (1 + ranger.getPrimary() / 100.0);

        double actual_damage = ranger.damage();

        /*Assertion without weapon */
        Assertions.assertEquals(expected_damage, actual_damage);


        ranger.equipItem(training_bow);
        int weapon_damage = ranger.getHeroEquipment().get(Slot.WEAPON).getWeaponDamage();
        double expected_with_weapon = expected_damage * weapon_damage;
        double actual_with_weapon = ranger.damage();

        /*Assertion with weapon */
        Assertions.assertEquals(expected_with_weapon, actual_with_weapon);


        ranger.levelUp(99); // using levelUp() to equip weapon of higher level
        double expected_afterLevelUp = (1 + ranger.getPrimary() / 100.0);
        ranger.equipItem(deaths_whisper);
        int weapon_damage_withReplacedWeapon = ranger.getHeroEquipment().get(Slot.WEAPON).getWeaponDamage();
        double expected_withReplacedWeapon = expected_afterLevelUp * weapon_damage_withReplacedWeapon;
        double actual_withReplacedWeapon = ranger.damage();

        /*Assertion with replaced weapon */
        Assertions.assertEquals(expected_withReplacedWeapon, actual_withReplacedWeapon);

        ranger.equipItem(cryptstalkers_chestguard);
        double expected_afterEquippingArmor = (1 + ranger.getPrimary() / 100.0) * weapon_damage_withReplacedWeapon;
        double actual_afterEquippingArmor = ranger.damage();

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
                    Damage: %f""", "Sylvanas Windrunner", "Ranger",
                1,1,7,1, (1 + ranger.getPrimary() / 100.0));

        String actual_display = format("""
                    Hero name: %s
                    Class: %s
                    Level: %d
                    Total Strength: %d
                    Total Dexterity: %d
                    Total Intelligence: %d
                    Damage: %f""", ranger.getHeroName(),
                ranger.getClassName(), ranger.getHeroLevel(),
                ranger.getLevelAttributes().getStrength(), ranger.getLevelAttributes().getDexterity(),
                ranger.getLevelAttributes().getIntelligence(), ranger.damage());

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
                1, 7, 1);

        String actual_withNoEquipment = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                ranger.getLevelAttributes().getStrength(),
                ranger.getLevelAttributes().getDexterity(),
                ranger.getLevelAttributes().getIntelligence());

        /* Assertion without any equipment */
        Assertions.assertEquals(expected_withNoEquipment, actual_withNoEquipment);


        ranger.equipItem(trainers_mail_chestpiece);
        String expected_withOnePiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                3, 15, 2);

        String actual_withOnePiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                ranger.getLevelAttributes().getStrength(),
                ranger.getLevelAttributes().getDexterity(),
                ranger.getLevelAttributes().getIntelligence());
        /* Assertion with one piece of equipment */
        Assertions.assertEquals(expected_withOnePiece, actual_withOnePiece);


        ranger.equipItem(chainmail_legguards);
        String expected_withTwoPieces = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                6, 33, 6);

        String actual_withTwoPieces = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                ranger.getLevelAttributes().getStrength(),
                ranger.getLevelAttributes().getDexterity(),
                ranger.getLevelAttributes().getIntelligence());

        /* Assertion with two pieces of equipment */
        Assertions.assertEquals(expected_withTwoPieces, actual_withTwoPieces);


        ranger.equipItem(huntsmans_greaves);
        String expected_withReplacedPiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                8, 31, 6);

        String actual_withReplacedPiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                ranger.getLevelAttributes().getStrength(),
                ranger.getLevelAttributes().getDexterity(),
                ranger.getLevelAttributes().getIntelligence());

        /* Assertion with a replaced piece of equipment */
        Assertions.assertEquals(expected_withReplacedPiece, actual_withReplacedPiece);

    }

    /* Testing levelUP() */
    @Test
    public void test_levelUP(){

        ranger.levelUp(10);

        int expectedLevel = 11;
        int actualLevel = ranger.getHeroLevel();

        int expectedStrength = 11;
        int actualStrength = ranger.getLevelAttributes().getStrength();

        int expectedDexterity = 57;
        int actualDexterity = ranger.getLevelAttributes().getDexterity();

        int expectedIntelligence = 11;
        int actualIntelligence = ranger.getLevelAttributes().getIntelligence();


        Assertions.assertEquals(expectedLevel, actualLevel);
        Assertions.assertEquals(expectedStrength, actualStrength);
        Assertions.assertEquals(expectedDexterity, actualDexterity);
        Assertions.assertEquals(expectedIntelligence, actualIntelligence);

    }

    /* Methods Testing End */

    /* Getters and Setters Testing */

    @Test
    public void test_GetHeroName(){

        String expected = "Sylvanas Windrunner";
        String actual = ranger.getHeroName();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_GetHeroClassName() {

        String expected = "Ranger";
        String actual = ranger.getClassName();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void test_GetHeroLevel() {

        int expected = 1;
        int actual = ranger.getHeroLevel();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_IncreaseHeroLevel() {

        ranger.increaseHeroLevel(4);
        int expected = 5;
        int actual = ranger.getHeroLevel();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_GetHeroEquipment() {

        HashMap<Slot, Item> expected_equipment = new HashMap<>();
        expected_equipment.put(Slot.HEAD, null);
        expected_equipment.put(Slot.BODY, null);
        expected_equipment.put(Slot.LEGS, null);
        expected_equipment.put(Slot.WEAPON, null);

        HashMap<Slot, Item> actual_equipment = ranger.getHeroEquipment();

        Assertions.assertEquals(expected_equipment, actual_equipment);
    }

    @Test
    public void test_GetValidArmor() {

        ArmorType armorType_expected = ArmorType.MAIL;
        ArmorType armorType_actual = ranger.getValidArmor();

        Assertions.assertEquals(armorType_expected, armorType_actual);
    }

    @Test
    public void test_GetValidWeapon() {

        ArrayList<WeaponType> weaponTypes_expected = new ArrayList<>();
        weaponTypes_expected.add(WeaponType.BOW);

        ArrayList<WeaponType> weaponTypes_actual = ranger.getValidWeapon();

        Assertions.assertEquals(weaponTypes_expected, weaponTypes_actual);
    }
    @Test
    public void test_GetLevelAttributes() {

        HeroAttributes heroAttributes_expected = new HeroAttributes(1,7,1);
        HeroAttributes heroAttributes_actual = ranger.getLevelAttributes();

        Assertions.assertEquals(heroAttributes_expected, heroAttributes_actual);
    }

    @Test
    public void test_GetPrimary() {

        int expected_primary = 7;
        int actual_primary = Math.max(Math.max(
                        ranger.getLevelAttributes().getStrength(),
                        ranger.getLevelAttributes().getDexterity()),
                ranger.getLevelAttributes().getIntelligence());

        Assertions.assertEquals(expected_primary, actual_primary);

    }

    /* Getters and Setters Testing End */


}