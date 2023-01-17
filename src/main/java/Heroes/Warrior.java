package Heroes;

import Items.ArmorType;
import Items.WeaponType;
import java.util.ArrayList;

public class Warrior extends Hero{


/* Inherited Constructor */
    public Warrior(String heroName) {
        super(heroName);
        this.levelAttributes = new HeroAttributes(5,2,1);
        this.validArmor = ArmorType.PLATE;
        this.validWeapon = new ArrayList<>();
        validWeapon.add(WeaponType.AXE);
        validWeapon.add(WeaponType.SWORD);
        validWeapon.add(WeaponType.HAMMER);
        this.attributesPerLevel = new int[]{3,2,1};

    }

/* Overridden Method */

//    @Override
//    public void levelUp(int levelsGained) {
//
//        super.increaseHeroLevel(levelsGained);
//        getLevelAttributes().increaseStrength(3*levelsGained);
//        getLevelAttributes().increaseDexterity(2*levelsGained);
//        getLevelAttributes().increaseIntelligence(levelsGained);
//
//        System.out.printf("""
//                Congrats! You leveled up!
//                You are now level %d
//                """,getHeroLevel());
//    }

}
