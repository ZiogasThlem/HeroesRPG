package Heroes;

import Exceptions.InvalidArmorException;
import Exceptions.InvalidWeaponException;
import Heroes.subclasses.Ranger;
import Items.*;
import Items.enums.ArmorType;
import Items.enums.Slot;
import Items.enums.WeaponType;
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
            new HeroAttributes(2,8,1), ArmorType.MAIL);

    Armor cryptstalkers_helmet = new Armor("Cryptstalker's Helmet", 100, Slot.HEAD,
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

        /* Testing by attempting to equip a valid weapon */
        ranger.equipItem(training_bow);
        Item expected_equipped_weapon = ranger.heroEquipment.get(Slot.WEAPON);

        Assertions.assertEquals(expected_equipped_weapon, training_bow);

        /* Assertion for Weapon of invalid WeaponType for Ranger Class */
        Assertions.assertThrows(InvalidWeaponException.class, () -> ranger.equipItem(blacksmiths_hammer) );

        /* Assertion for Weapon of higher ItemLevel of Ranger Class */
        Assertions.assertThrows(InvalidWeaponException.class, () -> ranger.equipItem(deaths_whisper) );

    }

    /* Testing equip() for armor*/
    @Test
    public void test_equip_forArmor() throws InvalidArmorException {

        /* Testing by attempting to equip a valid piece of armor */
        ranger.equipItem(trainers_mail_chestpiece);
        Item expected_equipped_armor = ranger.heroEquipment.get(Slot.BODY);

        Assertions.assertEquals(expected_equipped_armor, trainers_mail_chestpiece);

        /*Assertion for armor of invalid ArmorType for Warrior Class*/
        Assertions.assertThrows(InvalidArmorException.class, () -> ranger.equipItem(steel_helmet));

        /*Assertion for armor of higher ItemLevel of warrior */
        Assertions.assertThrows(InvalidArmorException.class, () -> ranger.equipItem(cryptstalkers_helmet));

    }


    /* Testing damage() */
    @Test
    public void test_damage() throws InvalidWeaponException, InvalidArmorException {

         /* From the first part of damage() method, testing damage
        with just the primary attribute through the given equation. */
        double expected_damage = (1 + ranger.getPrimary() / 100.0);
        double actual_damage = ranger.damage();

        /*Assertion without weapon */
        Assertions.assertEquals(expected_damage, actual_damage);

        /* equipping weapon and testing */
        ranger.equipItem(training_bow);
        int weapon_damage = ranger.heroEquipment.get(Slot.WEAPON).getWeaponDamage();
        double expected_with_weapon = expected_damage * weapon_damage;
        double actual_with_weapon = ranger.damage();

        /*Assertion with weapon */
        Assertions.assertEquals(expected_with_weapon, actual_with_weapon);

        /* replacing weapon  and testing again */
        ranger.levelUp(99); // using levelUp() to equip weapon of higher level
        double expected_afterLevelUp = (1 + ranger.getPrimary() / 100.0);
        ranger.equipItem(deaths_whisper);
        int weapon_damage_withReplacedWeapon = ranger.heroEquipment.get(Slot.WEAPON).getWeaponDamage();
        double expected_withReplacedWeapon = expected_afterLevelUp * weapon_damage_withReplacedWeapon;
        double actual_withReplacedWeapon = ranger.damage();

        /*Assertion with replaced weapon */
        Assertions.assertEquals(expected_withReplacedWeapon, actual_withReplacedWeapon);

        /* equipping piece of armor and testing */
        ranger.equipItem(cryptstalkers_helmet);
        double expected_afterEquippingArmor = (1 + ranger.getPrimary() / 100.0) * weapon_damage_withReplacedWeapon;
        double actual_afterEquippingArmor = ranger.damage();

        /*Assertion with armor equipped */
        Assertions.assertEquals(expected_afterEquippingArmor, actual_afterEquippingArmor);
    }

    /* Testing display() */
    @Test
    public void test_display() {

        /* Forming a multiline string with
        values given from the created object. */
        String expected_display = """
                    Hero name: Sylvanas Windrunner
                    Class: Ranger
                    Level: 1
                    Total Strength: 1
                    Total Dexterity: 7
                    Total Intelligence: 1
                    Damage: 1,070000""";

        /* Forming a second multiline string with
        values taken from respective fields. */
        String actual_display = format("""
                    Hero name: %s
                    Class: %s
                    Level: %d
                    Total Strength: %d
                    Total Dexterity: %d
                    Total Intelligence: %d
                    Damage: %f""", ranger.heroName,
                ranger.className, ranger.heroLevel,
                ranger.levelAttributes.getStrength(), ranger.levelAttributes.getDexterity(),
                ranger.levelAttributes.getIntelligence(), ranger.damage());

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
                ranger.levelAttributes.getStrength(),
                ranger.levelAttributes.getDexterity(),
                ranger.levelAttributes.getIntelligence());

        String actual_withNoEquipment = ranger.totalHeroAttributes();

        /* Assertion without any equipment */
        Assertions.assertEquals(expected_withNoEquipment, actual_withNoEquipment);

        /* testing by equipping first piece of equipment */
        ranger.equipItem(trainers_mail_chestpiece);
        String expected_withOnePiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                ranger.levelAttributes.getStrength(),
                ranger.levelAttributes.getDexterity(),
                ranger.levelAttributes.getIntelligence());

        String actual_withOnePiece = ranger.totalHeroAttributes();

        /* Assertion with one piece of equipment */
        Assertions.assertEquals(expected_withOnePiece, actual_withOnePiece);

        /* testing by equipping a second piece of equipment */
        ranger.equipItem(chainmail_legguards);
        String expected_withTwoPieces = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                ranger.levelAttributes.getStrength(),
                ranger.levelAttributes.getDexterity(),
                ranger.levelAttributes.getIntelligence());

        String actual_withTwoPieces = ranger.totalHeroAttributes();

        /* Assertion with two pieces of equipment */
        Assertions.assertEquals(expected_withTwoPieces, actual_withTwoPieces);

        /* testing by replacing previous piece of equipment */
        ranger.equipItem(huntsmans_greaves);
        String expected_withReplacedPiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                ranger.levelAttributes.getStrength(),
                ranger.levelAttributes.getDexterity(),
                ranger.levelAttributes.getIntelligence());

        String actual_withReplacedPiece = ranger.totalHeroAttributes();

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

        int expectedStrength= ranger.levelAttributes.getStrength();
        int expectedDexterity = ranger.levelAttributes.getDexterity();
        int expectedIntelligence = ranger.levelAttributes.getIntelligence();

        ranger.levelUp(10);

        int expectedLevel_afterLevelUp = ranger.heroLevel += 10;
        int actualLevel = ranger.getHeroLevel();

        int expectedStrength_afterLevelUp = expectedStrength + 10 * ranger.attributesPerLevel[0];
        int actualStrength = ranger.getLevelAttributes().getStrength();

        int expectedDexterity_afterLevelUp = expectedDexterity + 10 * ranger.attributesPerLevel[1];
        int actualDexterity = ranger.getLevelAttributes().getDexterity();

        int expectedIntelligence_afterLevelUp = expectedIntelligence + 10 * ranger.attributesPerLevel[2];
        int actualIntelligence = ranger.getLevelAttributes().getIntelligence();


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

        String expected = ranger.heroName;
        String actual = ranger.getHeroName();
        Assertions.assertEquals(expected, actual);
    }

    /* Testing getHeroClassName() */
    @Test
    public void test_GetHeroClassName() {

        String expected = ranger.className;
        String actual = ranger.getClassName();
        Assertions.assertEquals(expected, actual);

    }
    /* Testing getHeroLevel() */
    @Test
    public void test_GetHeroLevel() {

        int expected = ranger.heroLevel;
        int actual = ranger.getHeroLevel();
        Assertions.assertEquals(expected, actual);
    }

    /* Testing increaseHeroLevel() */
    @Test
    public void test_IncreaseHeroLevel() {

        int expected = ranger.heroLevel;
        ranger.increaseHeroLevel(4);

        int expected2 = expected + 4;
        int actual = ranger.getHeroLevel();
        Assertions.assertEquals(expected2, actual);
    }

    /* Testing getHeroEquipment() */
    @Test
    public void test_GetHeroEquipment() throws InvalidWeaponException, InvalidArmorException {

        /* First test with empty equipment */
        HashMap<Slot, Item> expected_equipment = ranger.heroEquipment;
        HashMap<Slot, Item> actual_equipment = ranger.getHeroEquipment();

        Assertions.assertEquals(expected_equipment, actual_equipment);


        /* Second test with full set of valid equipment
        with created Armor and Weapon objects*/
        HashMap<Slot, Item> expected_equipment_afterEquipping = new HashMap<>();
        expected_equipment_afterEquipping.put(Slot.HEAD,cryptstalkers_helmet);
        expected_equipment_afterEquipping.put(Slot.BODY,trainers_mail_chestpiece);
        expected_equipment_afterEquipping.put(Slot.LEGS,chainmail_legguards);
        expected_equipment_afterEquipping.put(Slot.WEAPON,deaths_whisper);

        ranger.levelUp(100); // using levelUp() to equip higher level Items
        ranger.equipItem(cryptstalkers_helmet);
        ranger.equipItem(trainers_mail_chestpiece);
        ranger.equipItem(chainmail_legguards);
        ranger.equipItem(deaths_whisper);

        HashMap<Slot, Item> actual_equipment_afterEquipping = ranger.getHeroEquipment();
        Assertions.assertEquals(expected_equipment_afterEquipping, actual_equipment_afterEquipping);
    }

    /* Testing getValidArmor() */
    @Test
    public void test_GetValidArmor() {

        ArmorType armorType_expected = ranger.validArmor;
        ArmorType armorType_actual = ranger.getValidArmor();

        Assertions.assertEquals(armorType_expected, armorType_actual);
    }

    /* Testing getValidWeapon() */
    @Test
    public void test_GetValidWeapon() {

        ArrayList<WeaponType> weaponTypes_expected = ranger.validWeapon;

        ArrayList<WeaponType> weaponTypes_actual = ranger.getValidWeapon();

        Assertions.assertEquals(weaponTypes_expected, weaponTypes_actual);
    }

    /* Testing getLevelAttributes() */
    @Test
    public void test_GetLevelAttributes() {

        HeroAttributes heroAttributes_expected = ranger.levelAttributes;
        HeroAttributes heroAttributes_actual = ranger.getLevelAttributes();

        Assertions.assertEquals(heroAttributes_expected, heroAttributes_actual);
    }

    /* Testing getPrimary() */
    @Test
    public void test_GetPrimary() {

        int expected_primary = Math.max(Math.max(
                        ranger.getLevelAttributes().getStrength(),
                        ranger.getLevelAttributes().getDexterity()),
                        ranger.getLevelAttributes().getIntelligence());

        int actual_primary = ranger.getPrimary();

        Assertions.assertEquals(expected_primary, actual_primary);

    }

/* Getters and Setters Testing End */


}