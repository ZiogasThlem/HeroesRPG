package Heroes;

import Exceptions.InvalidArmorException;
import Exceptions.InvalidWeaponException;
import Heroes.subclasses.Mage;
import Items.*;
import Items.enums.ArmorType;
import Items.enums.Slot;
import Items.enums.WeaponType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.String.format;

class MageTest {


/* Creating Mage object and all Armor and
 Weapon objects needed for testing */

    Hero mage = new Mage("Archmage Kadgar");

    Weapon appretice_wand = new Weapon("Apprentice Wand", 1, 5, WeaponType.WAND);
    Weapon atiesh = new Weapon("Atiesh",15,1000, WeaponType.STAFF);
    Weapon shiv = new Weapon("Shiv", 1, 15, WeaponType.DAGGER);

    Armor linen_robe = new Armor("Linen Robe", 1, Slot.BODY,
            new HeroAttributes(1,1,10 ), ArmorType.CLOTH);
    Armor silver_plate_chest = new Armor("Bronze Plate Chest", 1, Slot.BODY,
            new HeroAttributes(40,25,5),ArmorType.PLATE);

    Armor frostfire_kilt = new Armor("Frostfire Kilt", 60, Slot.LEGS,
            new HeroAttributes(10,20,100 ),ArmorType.CLOTH);

    Armor crown_of_eternal_winter = new Armor("Crown of Eternal Winter", 1, Slot.HEAD,
            new HeroAttributes(1,5,50 ),ArmorType.CLOTH);

    Armor common_headband = new Armor("Common Headband", 1, Slot.HEAD,
            new HeroAttributes(1,1,5 ),ArmorType.CLOTH);



    /* Methods Testing */

    /* Testing equip() for weapons*/
    @Test
    public void test_equip_forWeapons() throws InvalidWeaponException {

        /* Testing by attempting to equip a valid weapon */
        mage.equipItem(appretice_wand);
        Item expected_equipped_weapon = mage.heroEquipment.get(Slot.WEAPON);

        Assertions.assertEquals(expected_equipped_weapon, appretice_wand);

        /* Assertion for Weapon of invalid WeaponType for Mage Class */
        Assertions.assertThrows(InvalidWeaponException.class, () -> mage.equipItem(shiv) );

        /* Assertion for Weapon of higher ItemLevel of Mage Class */
        Assertions.assertThrows(InvalidWeaponException.class, () -> mage.equipItem(atiesh) );


    }

    /* Testing equip() for armor*/
    @Test
    public void test_equip_forArmor() throws InvalidArmorException {

        /* Testing by attempting to equip a valid piece of armor */
        mage.equipItem(linen_robe);
        Item expected_equipped_armor = mage.heroEquipment.get(Slot.BODY);

        Assertions.assertEquals(expected_equipped_armor, linen_robe);

        /*Assertion for armor of invalid ArmorType for Mage Class*/
        Assertions.assertThrows(InvalidArmorException.class, () -> mage.equipItem(silver_plate_chest));

        /*Assertion for armor of higher ItemLevel of Mage */
        Assertions.assertThrows(InvalidArmorException.class, () -> mage.equipItem(frostfire_kilt));

    }


    /* Testing damage() */
    @Test
    public void test_damage() throws InvalidWeaponException, InvalidArmorException {

        /* From the first part of damage() method, testing damage
        with just the primary attribute through the given equation */
        double expected_damage = (1 + mage.getPrimary() / 100.0);
        double actual_damage = mage.damage();

        /*Assertion without weapon */
        Assertions.assertEquals(expected_damage, actual_damage);


        /* equipping weapon and testing */
        mage.equipItem(appretice_wand);
        int weapon_damage = mage.heroEquipment.get(Slot.WEAPON).getWeaponDamage();
        double expected_with_weapon = expected_damage * weapon_damage;
        double actual_with_weapon = mage.damage();

        /*Assertion with weapon */
        Assertions.assertEquals(expected_with_weapon, actual_with_weapon);

        /* replacing weapon  and testing again */
        mage.levelUp(79); // using levelUp() to equip weapon of higher level
        double expected_afterLevelUp = (1 + mage.getPrimary() / 100.0);
        mage.equipItem(atiesh);
        int weapon_damage_withReplacedWeapon = mage.heroEquipment.get(Slot.WEAPON).getWeaponDamage();
        double expected_withReplacedWeapon = expected_afterLevelUp * weapon_damage_withReplacedWeapon;
        double actual_withReplacedWeapon = mage.damage();

        /*Assertion with replaced weapon */
        Assertions.assertEquals(expected_withReplacedWeapon, actual_withReplacedWeapon);

        /* equipping piece of armor and testing */
        mage.equipItem(frostfire_kilt);
        double expected_afterEquippingArmor = (1 + mage.getPrimary() / 100.0) * weapon_damage_withReplacedWeapon;
        double actual_afterEquippingArmor = mage.damage();

        /*Assertion with armor equipped */
        Assertions.assertEquals(expected_afterEquippingArmor, actual_afterEquippingArmor);
    }

    /* Testing display() */
    @Test
    public void test_display() {

        /* Forming a multiline string with
        values given from the created object. */
        String expected_display = """
                    Hero name: Archmage Kadgar
                    Class: Mage
                    Level: 1
                    Total Strength: 1
                    Total Dexterity: 1
                    Total Intelligence: 8
                    Damage: 1,080000""";


        /* Forming a second multiline string with
        values taken from respective fields */
        String actual_display = format("""
                    Hero name: %s
                    Class: %s
                    Level: %d
                    Total Strength: %d
                    Total Dexterity: %d
                    Total Intelligence: %d
                    Damage: %f""", mage.heroName,
                mage.className, mage.heroLevel,
                mage.levelAttributes.getStrength(), mage.levelAttributes.getDexterity(),
                mage.levelAttributes.getIntelligence(), mage.damage());

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
                mage.levelAttributes.getStrength(),
                mage.levelAttributes.getDexterity(),
                mage.levelAttributes.getIntelligence());

        String actual_withNoEquipment = mage.totalHeroAttributes();

        /* Assertion without any equipment */
        Assertions.assertEquals(expected_withNoEquipment, actual_withNoEquipment);


        /* testing by equipping first piece of equipment */
        mage.equipItem(linen_robe);
        String expected_withOnePiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                mage.levelAttributes.getStrength(),
                mage.levelAttributes.getDexterity(),
                mage.levelAttributes.getIntelligence());

        String actual_withOnePiece = mage.totalHeroAttributes();

        /* Assertion with one piece of equipment */
        Assertions.assertEquals(expected_withOnePiece, actual_withOnePiece);

        /* testing by equipping a second piece of equipment */
        mage.equipItem(crown_of_eternal_winter);
        String expected_withTwoPieces = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                mage.levelAttributes.getStrength(),
                mage.levelAttributes.getDexterity(),
                mage.levelAttributes.getIntelligence());

        String actual_withTwoPieces = mage.totalHeroAttributes();
        /* Assertion with two pieces of equipment */
        Assertions.assertEquals(expected_withTwoPieces, actual_withTwoPieces);


        /* testing by replacing previous piece of equipment */
        mage.equipItem(common_headband);
        String expected_withReplacedPiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                mage.levelAttributes.getStrength(),
                mage.levelAttributes.getDexterity(),
                mage.levelAttributes.getIntelligence());

        String actual_withReplacedPiece = mage.totalHeroAttributes();

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

        int expectedStrength= mage.levelAttributes.getStrength();
        int expectedDexterity = mage.levelAttributes.getDexterity();
        int expectedIntelligence = mage.levelAttributes.getIntelligence();

        mage.levelUp(10);

        int expectedLevel_afterLevelUp = mage.heroLevel += 10;
        int actualLevel = mage.getHeroLevel();

        int expectedStrength_afterLevelUp = expectedStrength + 10 * mage.attributesPerLevel[0];
        int actualStrength = mage.getLevelAttributes().getStrength();

        int expectedDexterity_afterLevelUp = expectedDexterity + 10 * mage.attributesPerLevel[1];
        int actualDexterity = mage.getLevelAttributes().getDexterity();

        int expectedIntelligence_afterLevelUp = expectedIntelligence + 10 * mage.attributesPerLevel[2];
        int actualIntelligence = mage.getLevelAttributes().getIntelligence();



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

        String expected = mage.heroName;
        String actual = mage.getHeroName();
        Assertions.assertEquals(expected, actual);
    }

    /* Testing getHeroClassName() */
    @Test
    public void test_GetHeroClassName() {

        String expected = mage.className;
        String actual = mage.getClassName();
        Assertions.assertEquals(expected, actual);

    }

    /* Testing getHeroLevel() */
    @Test
    public void test_GetHeroLevel() {

        int expected = mage.heroLevel;
        int actual = mage.getHeroLevel();
        Assertions.assertEquals(expected, actual);
    }

    /* Testing increaseHeroLevel() */
    @Test
    public void test_IncreaseHeroLevel() {

        int expected = mage.heroLevel;
        mage.increaseHeroLevel(4);
        int expected2 = expected + 4;
        int actual = mage.getHeroLevel();

        Assertions.assertEquals(expected2, actual);
    }

    /* Testing getHeroEquipment() */
    @Test
    public void test_GetHeroEquipment() throws InvalidWeaponException, InvalidArmorException {

        /* First test with empty equipment */
        HashMap<Slot, Item> expected_equipment = mage.heroEquipment;
        HashMap<Slot, Item> actual_equipment = mage.getHeroEquipment();

        Assertions.assertEquals(expected_equipment, actual_equipment);


        /* Second test with full set of valid equipment
        with created Armor and Weapon objects*/
        HashMap<Slot, Item> expected_equipment_afterEquipping = new HashMap<>();
        expected_equipment_afterEquipping.put(Slot.HEAD,crown_of_eternal_winter);
        expected_equipment_afterEquipping.put(Slot.BODY,linen_robe);
        expected_equipment_afterEquipping.put(Slot.LEGS,frostfire_kilt);
        expected_equipment_afterEquipping.put(Slot.WEAPON,atiesh);

        mage.levelUp(100); // using levelUp() to equip higher level Items
        mage.equipItem(atiesh);
        mage.equipItem(frostfire_kilt);
        mage.equipItem(linen_robe);
        mage.equipItem(crown_of_eternal_winter);

        HashMap<Slot, Item> actual_equipment_afterEquipping = mage.getHeroEquipment();
        Assertions.assertEquals(expected_equipment_afterEquipping, actual_equipment_afterEquipping);

    }

    /* Testing getValidArmor() */
    @Test
    public void test_GetValidArmor() {

        ArmorType armorType_expected = mage.validArmor;
        ArmorType armorType_actual = mage.getValidArmor();

        Assertions.assertEquals(armorType_expected, armorType_actual);
    }

    /* Testing getValidWeapon() */
    @Test
    public void test_GetValidWeapon() {

        ArrayList<WeaponType> weaponTypes_expected = mage.validWeapon;
        ArrayList<WeaponType> weaponTypes_actual = mage.getValidWeapon();

        Assertions.assertEquals(weaponTypes_expected, weaponTypes_actual);
    }

    /* Testing getLevelAttributes() */
    @Test
    public void test_GetLevelAttributes() {

        HeroAttributes heroAttributes_expected = mage.levelAttributes;
        HeroAttributes heroAttributes_actual = mage.getLevelAttributes();

        Assertions.assertEquals(heroAttributes_expected, heroAttributes_actual);
    }

    /* Testing getPrimary() */
    @Test
    public void test_GetPrimary() {

        int expected_primary = Math.max(Math.max(
                        mage.getLevelAttributes().getStrength(),
                        mage.getLevelAttributes().getDexterity()),
                        mage.getLevelAttributes().getIntelligence());

        int actual_primary = mage.getPrimary();

        Assertions.assertEquals(expected_primary, actual_primary);

    }

/* Getters and Setters Testing End */
}