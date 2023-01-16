package Heroes;

import Exceptions.InvalidArmorException;
import Exceptions.InvalidWeaponException;
import Items.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {

    Warrior warrior = new Warrior("Garrosh Hellscream");
    Weapon weapon = new Weapon("Ashbringer",1,1000, WeaponType.SWORD);
    Armor armor = new Armor("Cloth Chest", 1, Slot.BODY,
            new HeroAttributes(1,2,6),ArmorType.PLATE);


    @Test
    public void test_equip_forWeapons() throws InvalidWeaponException {


        warrior.equipItem(weapon);
        Item expected = warrior.getHeroEquipment().get(Slot.WEAPON);

        Assertions.assertEquals(expected,weapon);


        Weapon weapon1 = new Weapon("Ashbringer1",3,1000, WeaponType.SWORD);
        Assertions.assertThrows(InvalidWeaponException.class, () -> warrior.equipItem(weapon1) );

        Weapon weapon2 = new Weapon("Ashbringer2",1,1000, WeaponType.DAGGER);
        Assertions.assertThrows(InvalidWeaponException.class, () -> warrior.equipItem(weapon2) );

    }

    @Test
    public void test_equip_forArmor() throws InvalidArmorException {

        warrior.equipItem(armor);
        Item expected = warrior.getHeroEquipment().get(Slot.BODY);
        Assertions.assertEquals(expected, armor);

        Armor armor1 = new Armor("Cloth Chest", 1, Slot.BODY,
                new HeroAttributes(1,2,6),ArmorType.CLOTH);
        Assertions.assertThrows(InvalidArmorException.class, () -> warrior.equipItem(armor1));

        Armor armor2 = new Armor("Cloth Chest", 20, Slot.BODY,
                new HeroAttributes(1,2,6),ArmorType.PLATE);
        Assertions.assertThrows(InvalidArmorException.class, () -> warrior.equipItem(armor2));

    }


    @Test
    public void test_damage() throws InvalidWeaponException {

        int primary = Math.max(Math.max(warrior.getLevelAttributes().getStrength(),
                warrior.getLevelAttributes().getDexterity()), warrior.getLevelAttributes().getIntelligence());

        double expected = (1 + primary / 100.0);
        double input = warrior.damage();
        Assertions.assertEquals(expected, input);


        warrior.equipItem(weapon);
        int weapon_damage = warrior.getHeroEquipment().get(Slot.WEAPON).getWeaponDamage();
        double expected_with_weapon = expected * weapon_damage;
        double input_with_weapon = warrior.damage();

        Assertions.assertEquals(expected_with_weapon, input_with_weapon);

    }

    @Test
    public void test_display() {

     String expected = format("""
                    Hero name: %s
                    Class: %s
                    Level: %d
                    Total Strength: %d
                    Total Dexterity: %d
                    Total Intelligence: %d
                    Damage: %f""", "Garrosh Hellscream", "Warrior",
                    1,5,2,1, (1 + warrior.getLevelAttributes().getStrength() / 100.0));

     String input = format("""
                    Hero name: %s
                    Class: %s
                    Level: %d
                    Total Strength: %d
                    Total Dexterity: %d
                    Total Intelligence: %d
                    Damage: %f""", warrior.getHeroName(),
                    warrior.getClassName(), warrior.getHeroLevel(), warrior.getLevelAttributes().getStrength(), warrior.getLevelAttributes().getDexterity(),
                    warrior.getLevelAttributes().getIntelligence(), warrior.damage());

     Assertions.assertEquals(expected, input);


    }

    @Test
    void test_totalHeroAttributes() {

    }

    @Test
    void test_levelUP(){

    }





}