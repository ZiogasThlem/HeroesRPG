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







        Armor armor4 = new Armor("Gladiator's Legguards", 1, Slot.LEGS,
                new HeroAttributes(300,100,50),ArmorType.PLATE);

        Armor armor5 = new Armor("Apprentice's Legguards", 1, Slot.LEGS,
                new HeroAttributes(4,1,1),ArmorType.PLATE);


        warrior.equipItem(armor4);
        System.out.println(warrior.getHeroEquipment().get(Slot.LEGS));
        System.out.println(warrior.totalHeroAttributes());

        warrior.equipItem(armor5);
        System.out.println(warrior.getHeroEquipment().get(Slot.LEGS));
        System.out.println(warrior.totalHeroAttributes());









    }
}


