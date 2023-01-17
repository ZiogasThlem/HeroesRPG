package Heroes;

import Exceptions.InvalidArmorException;
import Exceptions.InvalidWeaponException;
import Items.*;
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
            new HeroAttributes(1,1,10 ),ArmorType.CLOTH);
    Armor silver_plate_chest = new Armor("Bronze Plate Chest", 1, Slot.BODY,
            new HeroAttributes(40,25,5),ArmorType.PLATE);

    Armor frostfire_vest = new Armor("Frostfire Vest", 60, Slot.BODY,
            new HeroAttributes(10,20,100 ),ArmorType.CLOTH);

    Armor crown_of_eternal_winter = new Armor("Crown of Eternal Winter", 1, Slot.HEAD,
            new HeroAttributes(1,5,50 ),ArmorType.CLOTH);

    Armor common_headband = new Armor("Common Headband", 1, Slot.HEAD,
            new HeroAttributes(1,1,5 ),ArmorType.CLOTH);



    /* Methods Testing */

    /* Testing equip() for weapons*/
    @Test
    public void test_equip_forWeapons() throws InvalidWeaponException {


        mage.equipItem(appretice_wand);
        Item expected_equipped_weapon = mage.getHeroEquipment().get(Slot.WEAPON);

        Assertions.assertEquals(expected_equipped_weapon, appretice_wand);

        /* Assertion for Weapon of invalid WeaponType for Mage Class */
        Assertions.assertThrows(InvalidWeaponException.class, () -> mage.equipItem(shiv) );

        /* Assertion for Weapon of higher ItemLevel of Mage Class */
        Assertions.assertThrows(InvalidWeaponException.class, () -> mage.equipItem(atiesh) );


    }

    /* Testing equip() for armor*/
    @Test
    public void test_equip_forArmor() throws InvalidArmorException {

        mage.equipItem(linen_robe);
        Item expected_equipped_armor = mage.getHeroEquipment().get(Slot.BODY);

        Assertions.assertEquals(expected_equipped_armor, linen_robe);

        /*Assertion for armor of invalid ArmorType for Warrior Class*/
        Assertions.assertThrows(InvalidArmorException.class, () -> mage.equipItem(silver_plate_chest));

        /*Assertion for armor of higher ItemLevel of warrior */
        Assertions.assertThrows(InvalidArmorException.class, () -> mage.equipItem(frostfire_vest));

    }


    /* Testing damage() */
    @Test
    public void test_damage() throws InvalidWeaponException, InvalidArmorException {

        double expected_damage = (1 + mage.getPrimary() / 100.0);

        double actual_damage = mage.damage();

        /*Assertion without weapon */
        Assertions.assertEquals(expected_damage, actual_damage);


        mage.equipItem(appretice_wand);
        int weapon_damage = mage.getHeroEquipment().get(Slot.WEAPON).getWeaponDamage();
        double expected_with_weapon = expected_damage * weapon_damage;
        double actual_with_weapon = mage.damage();

        /*Assertion with weapon */
        Assertions.assertEquals(expected_with_weapon, actual_with_weapon);


        mage.levelUp(79); // using levelUp() to equip weapon of higher level
        double expected_afterLevelUp = (1 + mage.getPrimary() / 100.0);
        mage.equipItem(atiesh);
        int weapon_damage_withReplacedWeapon = mage.getHeroEquipment().get(Slot.WEAPON).getWeaponDamage();
        double expected_withReplacedWeapon = expected_afterLevelUp * weapon_damage_withReplacedWeapon;
        double actual_withReplacedWeapon = mage.damage();

        /*Assertion with replaced weapon */
        Assertions.assertEquals(expected_withReplacedWeapon, actual_withReplacedWeapon);

        mage.equipItem(frostfire_vest);
        double expected_afterEquippingArmor = (1 + mage.getPrimary() / 100.0) * weapon_damage_withReplacedWeapon;
        double actual_afterEquippingArmor = mage.damage();

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
                    Damage: %f""", "Archmage Kadgar", "Mage",
                1,1,1,8, (1 + mage.getPrimary() / 100.0));

        String actual_display = format("""
                    Hero name: %s
                    Class: %s
                    Level: %d
                    Total Strength: %d
                    Total Dexterity: %d
                    Total Intelligence: %d
                    Damage: %f""", mage.getHeroName(),
                mage.getClassName(), mage.getHeroLevel(),
                mage.getLevelAttributes().getStrength(), mage.getLevelAttributes().getDexterity(),
                mage.getLevelAttributes().getIntelligence(), mage.damage());

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
                1, 1, 8);

        String actual_withNoEquipment = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                mage.getLevelAttributes().getStrength(),
                mage.getLevelAttributes().getDexterity(),
                mage.getLevelAttributes().getIntelligence());

        /* Assertion without any equipment */
        Assertions.assertEquals(expected_withNoEquipment, actual_withNoEquipment);


        mage.equipItem(linen_robe);
        String expected_withOnePiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                2, 2, 18);

        String actual_withOnePiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                mage.getLevelAttributes().getStrength(),
                mage.getLevelAttributes().getDexterity(),
                mage.getLevelAttributes().getIntelligence());
        /* Assertion with one piece of equipment */
        Assertions.assertEquals(expected_withOnePiece, actual_withOnePiece);

        mage.equipItem(crown_of_eternal_winter);
        String expected_withTwoPieces = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                3, 7, 68);

        String actual_withTwoPieces = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                mage.getLevelAttributes().getStrength(),
                mage.getLevelAttributes().getDexterity(),
                mage.getLevelAttributes().getIntelligence());

        /* Assertion with two pieces of equipment */
        Assertions.assertEquals(expected_withTwoPieces, actual_withTwoPieces);


        mage.equipItem(common_headband);
        String expected_withReplacedPiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                3, 3, 23);

        String actual_withReplacedPiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                mage.getLevelAttributes().getStrength(),
                mage.getLevelAttributes().getDexterity(),
                mage.getLevelAttributes().getIntelligence());

        /* Assertion with a replaced piece of equipment */
        Assertions.assertEquals(expected_withReplacedPiece, actual_withReplacedPiece);

    }

    /* Testing levelUP() */
    @Test
    public void test_levelUP(){

        mage.levelUp(10);

        int expectedLevel = 11;
        int actualLevel = mage.getHeroLevel();

        int expectedStrength = 11;
        int actualStrength = mage.getLevelAttributes().getStrength();

        int expectedDexterity = 11;
        int actualDexterity = mage.getLevelAttributes().getDexterity();

        int expectedIntelligence = 58;
        int actualIntelligence = mage.getLevelAttributes().getIntelligence();


        Assertions.assertEquals(expectedLevel, actualLevel);
        Assertions.assertEquals(expectedStrength, actualStrength);
        Assertions.assertEquals(expectedDexterity, actualDexterity);
        Assertions.assertEquals(expectedIntelligence, actualIntelligence);

    }

    /* Methods Testing End */

    /* Getters and Setters Testing */

    @Test
    public void test_GetHeroName(){

        String expected = "Archmage Kadgar";
        String actual = mage.getHeroName();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_GetHeroClassName() {

        String expected = "Mage";
        String actual = mage.getClassName();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void test_GetHeroLevel() {

        int expected = 1;
        int actual = mage.getHeroLevel();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_IncreaseHeroLevel() {

        mage.increaseHeroLevel(4);
        int expected = 5;
        int actual = mage.getHeroLevel();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_GetHeroEquipment() {

        HashMap<Slot, Item> expected_equipment = new HashMap<>();
        expected_equipment.put(Slot.HEAD, null);
        expected_equipment.put(Slot.BODY, null);
        expected_equipment.put(Slot.LEGS, null);
        expected_equipment.put(Slot.WEAPON, null);

        HashMap<Slot, Item> actual_equipment = mage.getHeroEquipment();

        Assertions.assertEquals(expected_equipment, actual_equipment);
    }

    @Test
    public void test_GetValidArmor() {

        ArmorType armorType_expected = ArmorType.CLOTH;
        ArmorType armorType_actual = mage.getValidArmor();

        Assertions.assertEquals(armorType_expected, armorType_actual);
    }

    @Test
    public void test_GetValidWeapon() {

        ArrayList<WeaponType> weaponTypes_expected = new ArrayList<>();
        weaponTypes_expected.add(WeaponType.STAFF);
        weaponTypes_expected.add(WeaponType.WAND);

        ArrayList<WeaponType> weaponTypes_actual = mage.getValidWeapon();

        Assertions.assertEquals(weaponTypes_expected, weaponTypes_actual);
    }
    @Test
    public void test_GetLevelAttributes() {

        HeroAttributes heroAttributes_expected = new HeroAttributes(1,1,8);
        HeroAttributes heroAttributes_actual = mage.getLevelAttributes();

        Assertions.assertEquals(heroAttributes_expected, heroAttributes_actual);
    }

    @Test
    public void test_GetPrimary() {

        int expected_primary = 8;
        int actual_primary = Math.max(Math.max(
                        mage.getLevelAttributes().getStrength(),
                        mage.getLevelAttributes().getDexterity()),
                mage.getLevelAttributes().getIntelligence());

        Assertions.assertEquals(expected_primary, actual_primary);

    }

    /* Getters and Setters Testing End */


}