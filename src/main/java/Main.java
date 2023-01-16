import Exceptions.InvalidArmorException;
import Exceptions.InvalidWeaponException;
import Heroes.*;
import Items.*;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InvalidArmorException, InvalidWeaponException {

        Hero mage = new Mage("Archmage Kadgar");
        Hero warrior = new Warrior("Garrosh Hellscream");
        Hero ranger = new Ranger("Sylvanas Windrunner");
        Hero rogue = new Rogue("Valeera Sanguinar");

        Weapon sword = new Weapon("Ashbringer",1,1000, WeaponType.SWORD);
        Armor head = new Armor("Cursed Vision of Sargaeras",1,Slot.HEAD,
                new HeroAttributes(5,100,1),ArmorType.CLOTH);

        Weapon staff = new Weapon("Atiesh", 1, 500, WeaponType.STAFF);



        warrior.equipItem(sword);
        System.out.println(warrior.damage());

        mage.equipItem(staff);
        System.out.println(mage.damage());






    }
}


