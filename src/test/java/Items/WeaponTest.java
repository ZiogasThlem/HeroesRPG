package Items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class WeaponTest {

    Weapon test_weapon = new Weapon("Test Weapon",50,1000,WeaponType.BOW);

/* Testing Getters */


    @Test
    public void test_GetItemLevel() {

        int expected_level = test_weapon.itemLevel;
        int actual_level = test_weapon.getItemLevel();

        Assertions.assertEquals(expected_level, actual_level);
    }


    @Test
    public void test_GetItemName() {

        String expected_name = test_weapon.itemName;
        String actual_name = test_weapon.getItemName();
        Assertions.assertEquals(expected_name, actual_name);

    }

    @Test
    public void test_getWeaponDamage() {

        int expected_damage = test_weapon.weaponDamage;
        int actual_damage = test_weapon.getWeaponDamage();

        Assertions.assertEquals(expected_damage, actual_damage);
    }

    @Test void test_getWeaponType() {

        WeaponType expected_weaponType = test_weapon.weaponType;
        WeaponType actual_weaponType = test_weapon.getWeaponType();

        Assertions.assertEquals(expected_weaponType, actual_weaponType);

    }

/* Testing Getters End*/


}