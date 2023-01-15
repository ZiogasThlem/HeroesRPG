package Heroes;

import Exceptions.*;
import Items.*;
import java.util.HashMap;

public abstract class Hero {


/* Hero Fields */

    /* Field for Hero's name. */
    protected String heroName;

    /* Field for Hero's starting level. */
    protected int heroLevel = 1;

    /* Field for Hero's starting Attributes */
    protected HeroAttributes levelAttributes = new HeroAttributes();

    /* HashMap for items to be equipped. Slots are
       predefined as they are not going to change,
       and starting value of each Item is null. */
    protected HashMap<Slot, Item> heroEquipment;
    {
        heroEquipment = new HashMap<>();
        heroEquipment.put(Slot.HEAD, null);
        heroEquipment.put(Slot.BODY, null);
        heroEquipment.put(Slot.LEGS, null);
        heroEquipment.put(Slot.WEAPON, null);

    }

    /* Field to display which type of
    armor a Hero is able to equip. */
    protected ArmorType validArmor;

    /* Field to display which type of weapons a Hero is able
     to equip. Using String Array as many Hero subclasses
     can equip multiple types of weapons*/
    protected WeaponType[] validWeapon;

    /*
    Getting Class name from a "Class" class object
    and assigning it to a variable for flexibility
     */
    Class<? extends Hero> cls = this.getClass();
    protected String className = cls.getSimpleName();

/* Hero Fields End */


/* Constructor */
    public Hero(String heroName) {
        this.heroName = heroName;
    }


/* Hero Methods*/

    /* Level Up method. A Hero's attributes are increased with
    each use by a predetermined amount, and it's level by 1.
    Implemented as abstract as it adds up different amounts
    for each subclass. */
    public abstract void levelUp();

    /* "Equip" method used for when a Hero needs to equip a piece of armor */
    public void equipItem(Armor armor) throws InvalidArmorException {

        /*
        Checking if Hero's level is equal or greater than that
        of the Armor. If it is, the Hero may equip this item
         */
        if (heroLevel >= armor.getItemLevel()) {
            heroEquipment.put(armor.getSlot(), armor);
        }
        /* If not, an exception is throw to inform the user that
        his Hero's level is too low to equip this item */
        else {
            throw new InvalidArmorException("Your level is too low. You need to be "
                    + "at least" + (armor.getItemLevel() + " level to be " +
                    "able to equip " + armor.getItemName()));
        }

    }

    /* "Equip" method used for when a Hero needs to equip a  weapon*/
    public void equipItem(Weapon weapon) throws InvalidWeaponException {

        /*
        Checking if Hero's level is equal or greater than that
        of the Weapon. If it is, the Hero may equip this item
         */
        if (heroLevel >= weapon.getItemLevel()) {
            heroEquipment.put(Slot.WEAPON, weapon);
        }
       /* If not, an exception is throw to inform the user that
        his Hero's level is too low to equip this item */
        else {
            throw new InvalidWeaponException("Your level is too low. You need to be "
                    + "at least" + (weapon.getItemLevel() + " level to be " +
                    "able to equip " + weapon.getItemName()));
        }

    }

    /*
    Calculating the total of each Hero Attribute as a sum of the
    Hero's Level Attributes and Armor Attributes for all pieces
    of equipment ,excluding weapons as they have none. Storing
    them into variables as they are required for multiple methods.
    */
    int totalStrength = levelAttributes.getStrength() + heroEquipment.get(Slot.HEAD).getStrength() +
            heroEquipment.get(Slot.BODY).getStrength() + heroEquipment.get(Slot.LEGS).getStrength();
    int totalDexterity = levelAttributes.getDexterity() + heroEquipment.get(Slot.HEAD).getDexterity() +
            heroEquipment.get(Slot.BODY).getDexterity() + heroEquipment.get(Slot.LEGS).getDexterity();

    int totalIntelligence = levelAttributes.getIntelligence() + heroEquipment.get(Slot.HEAD).getIntelligence() +
            heroEquipment.get(Slot.BODY).getIntelligence() + heroEquipment.get(Slot.LEGS).getIntelligence();

    /*Returning the value of the total of each Hero Attribute.*/
    public String totalHeroAttributes() {

        return "Strength: " + totalStrength + "\nDexterity: " +
                totalDexterity + "\nIntelligence: " + totalIntelligence;

    }

    /* Calculating the damage a Hero does */
    public double damage(){

        /* Getting a Hero's primary attribute by using the Math.max() method */
        int primary = Math.max(Math.max(totalStrength, totalDexterity),totalIntelligence);

        /* Accessing the weapon's damage from the respective Slot
        and calculating the damage with the given equation*/
        return heroEquipment.get(Slot.WEAPON).getWeaponDamage() * (1 + primary/100.0);

    }

    /* Displaying all the required fields
     with the use of a formatted String */
    public void display() {

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

    /* Getter to return Hero's name */
    public String getHeroName() {
        return heroName;
    }

    /* Setter to increase Hero's Level by one */
    public void setHeroLevel(int heroLevel) {
        this.heroLevel += heroLevel;
    }

    /* Getter to return Hero's Level */
    public int getHeroLevel() {
        return heroLevel;
    }

    /* Getter to return Hero's current Attributes
    Overridden in each subclass*/
    public String getLevelAttributes() {
        return levelAttributes.getHeroAttributes();
    }

    /* Getter to return a HashMap of Slot,Item
     pairs.Overridden in each subclass. */
    public HashMap<Slot, Item> getHeroEquipment() {
        return heroEquipment;
    }

    /* Getter to return Hero's valid Armor
    Type. Overridden in each subclass. */
    public ArmorType getValidArmor() {
        return validArmor;
    }

    /* Getter to return Hero's valid Weapon
    type(s). Overridden in each subclass. */
    public WeaponType[] getValidWeapon() {
        return validWeapon;
    }

    /* Getter to return Hero's class's name */
    public String getClassName() {
        return className;
    }

/*Getters and Setters End*/
}


