package Heroes;

import Items.Item;
import Items.Slot;

import java.util.Arrays;
import java.util.HashMap;

public abstract class Hero {
    protected String heroName;


    protected int heroLevel = 1;

    protected int lvl_strn, lvl_dextr, lvl_intl;
    protected HeroAttributes levelAttributes;

    protected HashMap<Slot, Item> equipment = new HashMap<>();

    protected static String validArmor;
    protected String[] validWeapon;

    Class cls = this.getClass();
    protected String className= cls.getSimpleName();

    public Hero(String heroName) {
        this.heroName = heroName;
    }
    public void levelUp(HeroAttributes levelAttributes){
        heroLevel+=1;
        //this.heroLevel+=1;
    }
    public void equipItem(){}; //for both weapon and armor
    // include try-catch with custom exceptions


    // public double damageCalc(){return dmg};  Hero damage = WeaponDamage * (1 + DamagingAttribute/100)

    //public HeroAttributes total(){}; total of stats in armor and levels



//    public void display() {
//        //include name argument for class
//        System.out.printf("Hero name: Class: Hero %s Level: %d " +
//                "Total Strength: %d Total Dexterity: %d" +
//                "Total Intelligence: %d Damage %f", heroName,
//                heroLevel, total.getStrength(), total.getDexterity(),
//                total.getIntelligence(), damage);
//    }


    public String display() {
        return "Hero{" +
                "heroName='" + heroName + '\'' +
                ", heroLevel=" + heroLevel +
                ", lvl_strn=" + lvl_strn +
                ", lvl_dextr=" + lvl_dextr +
                ", lvl_intl=" + lvl_intl +
                ", levelAttributes=" + levelAttributes +
                ", equipment=" + equipment +
                ", validWeapon=" + Arrays.toString(validWeapon) +
                ", className='" + className + '\'' +
                '}';
    }
    //      TODO Fields --DONE--
//    level attrs
//    leveling stats (ex str +1, dext +2, intel +5) can be set here as constants and defined at each class
//    equipment
//    valid weap type
//    valid armor type

//    TODO Methods
//    level up
//    equip (weapon - armor overload)
//    damage calc
//    total attr calc
//    display


}
