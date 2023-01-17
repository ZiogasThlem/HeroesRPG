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
        Item expected_equipped_weapon = rogue.getHeroEquipment().get(Slot.WEAPON);

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
        Item expected_equipped_armor = rogue.getHeroEquipment().get(Slot.BODY);

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
        int weapon_damage = rogue.getHeroEquipment().get(Slot.WEAPON).getWeaponDamage();
        double expected_with_weapon = expected_damage * weapon_damage;
        double actual_with_weapon = rogue.damage();

        /*Assertion with weapon */
        Assertions.assertEquals(expected_with_weapon, actual_with_weapon);


        rogue.levelUp(89); // using levelUp() to equip weapon of higher level
        double expected_afterLevelUp = (1 + rogue.getPrimary() / 100.0);
        rogue.equipItem(wolf_fangs);
        int weapon_damage_withReplacedWeapon = rogue.getHeroEquipment().get(Slot.WEAPON).getWeaponDamage();
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

        String expected_display = format("""
                    Hero name: %s
                    Class: %s
                    Level: %d
                    Total Strength: %d
                    Total Dexterity: %d
                    Total Intelligence: %d
                    Damage: %f""", "Valeera Sanguinar", "Rogue",
                1,2,6,1, (1 + rogue.getPrimary() / 100.0));

        String actual_display = format("""
                    Hero name: %s
                    Class: %s
                    Level: %d
                    Total Strength: %d
                    Total Dexterity: %d
                    Total Intelligence: %d
                    Damage: %f""", rogue.getHeroName(),
                rogue.getClassName(), rogue.getHeroLevel(),
                rogue.getLevelAttributes().getStrength(), rogue.getLevelAttributes().getDexterity(),
                rogue.getLevelAttributes().getIntelligence(), rogue.damage());

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
                2, 6, 1);

        String actual_withNoEquipment = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                rogue.getLevelAttributes().getStrength(),
                rogue.getLevelAttributes().getDexterity(),
                rogue.getLevelAttributes().getIntelligence());

        /* Assertion without any equipment */
        Assertions.assertEquals(expected_withNoEquipment, actual_withNoEquipment);


        rogue.equipItem(wildboar_pelt);
        String expected_withOnePiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                5, 16, 2);

        String actual_withOnePiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                rogue.getLevelAttributes().getStrength(),
                rogue.getLevelAttributes().getDexterity(),
                rogue.getLevelAttributes().getIntelligence());
        /* Assertion with one piece of equipment */
        Assertions.assertEquals(expected_withOnePiece, actual_withOnePiece);


        rogue.equipItem(bloodfang_pants);
        String expected_withTwoPieces = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                15, 36, 7);

        String actual_withTwoPieces = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                rogue.getLevelAttributes().getStrength(),
                rogue.getLevelAttributes().getDexterity(),
                rogue.getLevelAttributes().getIntelligence());

        /* Assertion with two pieces of equipment */
        Assertions.assertEquals(expected_withTwoPieces, actual_withTwoPieces);


        rogue.equipItem(grizzly_pants);
        String expected_withReplacedPiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                13, 31, 5);

        String actual_withReplacedPiece = format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                rogue.getLevelAttributes().getStrength(),
                rogue.getLevelAttributes().getDexterity(),
                rogue.getLevelAttributes().getIntelligence());

        /* Assertion with a replaced piece of equipment */
        Assertions.assertEquals(expected_withReplacedPiece, actual_withReplacedPiece);

    }

    /* Testing levelUP() */
    @Test
    public void test_levelUP(){

        rogue.levelUp(10);

        int expectedLevel = 11;
        int actualLevel = rogue.getHeroLevel();

        int expectedStrength = 12;
        int actualStrength = rogue.getLevelAttributes().getStrength();

        int expectedDexterity = 46;
        int actualDexterity = rogue.getLevelAttributes().getDexterity();

        int expectedIntelligence = 11;
        int actualIntelligence = rogue.getLevelAttributes().getIntelligence();


        Assertions.assertEquals(expectedLevel, actualLevel);
        Assertions.assertEquals(expectedStrength, actualStrength);
        Assertions.assertEquals(expectedDexterity, actualDexterity);
        Assertions.assertEquals(expectedIntelligence, actualIntelligence);

    }

    /* Methods Testing End */

    /* Getters and Setters Testing */

    @Test
    public void test_GetHeroName(){

        String expected = "Valeera Sanguinar";
        String actual = rogue.getHeroName();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_GetHeroClassName() {

        String expected = "Rogue";
        String actual = rogue.getClassName();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void test_GetHeroLevel() {

        int expected = 1;
        int actual = rogue.getHeroLevel();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_IncreaseHeroLevel() {

        rogue.increaseHeroLevel(4);
        int expected = 5;
        int actual = rogue.getHeroLevel();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_GetHeroEquipment() {

        HashMap<Slot, Item> expected_equipment = new HashMap<>();
        expected_equipment.put(Slot.HEAD, null);
        expected_equipment.put(Slot.BODY, null);
        expected_equipment.put(Slot.LEGS, null);
        expected_equipment.put(Slot.WEAPON, null);

        HashMap<Slot, Item> actual_equipment = rogue.getHeroEquipment();

        Assertions.assertEquals(expected_equipment, actual_equipment);
    }

    @Test
    public void test_GetValidArmor() {

        ArmorType armorType_expected = ArmorType.LEATHER;
        ArmorType armorType_actual = rogue.getValidArmor();

        Assertions.assertEquals(armorType_expected, armorType_actual);
    }

    @Test
    public void test_GetValidWeapon() {

        ArrayList<WeaponType> weaponTypes_expected = new ArrayList<>();
        weaponTypes_expected.add(WeaponType.DAGGER);
        weaponTypes_expected.add(WeaponType.SWORD);


        ArrayList<WeaponType> weaponTypes_actual = rogue.getValidWeapon();

        Assertions.assertEquals(weaponTypes_expected, weaponTypes_actual);
    }
    @Test
    public void test_GetLevelAttributes() {

        HeroAttributes heroAttributes_expected = new HeroAttributes(2,6,1);
        HeroAttributes heroAttributes_actual = rogue.getLevelAttributes();

        Assertions.assertEquals(heroAttributes_expected, heroAttributes_actual);
    }

    @Test
    public void test_GetPrimary() {

        int expected_primary = 6;
        int actual_primary = Math.max(Math.max(
                        rogue.getLevelAttributes().getStrength(),
                        rogue.getLevelAttributes().getDexterity()),
                rogue.getLevelAttributes().getIntelligence());

        Assertions.assertEquals(expected_primary, actual_primary);

    }

    /* Getters and Setters Testing End */


}