package Heroes;

import Exceptions.*;
import Items.*;
import java.util.HashMap;

public abstract class Hero {

/*Hero Fields*/
    protected String heroName;
    protected int heroLevel = 1;
    protected HeroAttributes levelAttributes = new HeroAttributes();

    protected HashMap<Slot, Item> HeroEquipment;
    {
        HeroEquipment = new HashMap<>();
        HeroEquipment.put(Slot.HEAD, null);
        HeroEquipment.put(Slot.BODY, null);
        HeroEquipment.put(Slot.LEGS, null);
        HeroEquipment.put(Slot.WEAPON, null);

    }
    protected ArmorType validArmor;
    protected WeaponType[] validWeapon;

    /*
    Getting
     */
    Class<? extends Hero> cls = this.getClass();
    protected String className = cls.getSimpleName();

/*Hero Fields End*/


/* Constructor */

    public Hero(String heroName) {
        this.heroName = heroName;
    }

/* Constructor End */

/* Hero Methods*/

    public void equipItem(Weapon weapon) throws InvalidWeaponException {

        if (heroLevel >= weapon.getLevelRequired()) {
            HeroEquipment.put(Slot.WEAPON, weapon);
        }
        throw new InvalidWeaponException("Your level is too low. You need " +
                (weapon.getLevelRequired() - heroLevel) + " more levels to be" +
                " able to equip " + weapon.getItemName());
    }


    public void equipItem(Armor armor) throws InvalidArmorException {
        if (heroLevel >= armor.getLevelRequired()) {
            HeroEquipment.put(armor.getSlot(), armor);
        }
        throw new InvalidArmorException("Your level is too low. You need " +
                (armor.getLevelRequired() - heroLevel) + " more levels to be " +
                "able to equip " + armor.getItemName());
    }

    public double damage(){

         int primary = Math.max(Math.max(levelAttributes.getStrength(),
                levelAttributes.getDexterity()), levelAttributes.getIntelligence());

        return HeroEquipment.get(Slot.WEAPON).getWeaponDamage() * (1 + primary/100.0);

     }

    /*

     */
    int totalStrength = levelAttributes.getStrength() + HeroEquipment.get(Slot.HEAD).getStrength() +
            HeroEquipment.get(Slot.BODY).getStrength() + HeroEquipment.get(Slot.LEGS).getStrength();
    int totalDexterity = levelAttributes.getDexterity() + HeroEquipment.get(Slot.HEAD).getDexterity() +
            HeroEquipment.get(Slot.BODY).getDexterity() + HeroEquipment.get(Slot.LEGS).getDexterity();

    int totalIntelligence = levelAttributes.getIntelligence() + HeroEquipment.get(Slot.HEAD).getIntelligence() +
            HeroEquipment.get(Slot.BODY).getIntelligence() + HeroEquipment.get(Slot.LEGS).getIntelligence();
    public String totalHeroAttributes() { //total of stats in armor and levels
        return "Strength: " + totalStrength + "\nDexterity: " +
                totalDexterity + "\nIntelligence: " + totalIntelligence; }

    public void display() {
        //include name argument for class
        System.out.printf("""
                        Hero name: %s
                        Class: %s
                        Level: %d
                        Total Strength: %d
                        Total Dexterity: %d
                        Total Intelligence: %d
                        Damage %f""", heroName,
                className, heroLevel, totalStrength, totalDexterity,
                totalIntelligence, damage());
      }

/* Hero Methods End*/


/*Getters and Setters*/

    public int getHeroLevel() {
        return heroLevel;
    }

    public void setHeroLevel(int heroLevel) {
        this.heroLevel += heroLevel;
    }

    public ArmorType getValidArmor() {
        return validArmor;
    }

    public String getLevelAttributes() {
        return levelAttributes.getHeroAttributes();
    }

    public String getHeroName() {
        return heroName;
    }

    public WeaponType[] getValidWeapon() {
        return validWeapon;
    }

    public String getClassName() {
        return className;
    }

    public HashMap<Slot, Item> getHeroEquipment() {
        return HeroEquipment;
    }

/*Getters and Setters End*/

}


