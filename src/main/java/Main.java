import Heroes.Mage;
import Heroes.Ranger;
import Heroes.Rogue;
import Heroes.Warrior;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) {

        Mage mage = new Mage("Archmage Kadgar");
        Warrior warrior = new Warrior("Garrosh Hellscream");
        Ranger ranger = new Ranger("Sylvanas Windrunner");
        Rogue rogue = new Rogue("Valeera Sanguinar");

        Class a = mage.getClass();
        System.out.println(a.getSimpleName());



    }
}


