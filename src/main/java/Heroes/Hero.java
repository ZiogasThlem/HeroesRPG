package Heroes;

import Exceptions.*;
import Items.*;
import java.util.HashMap;
import java.util.ArrayList;

import static java.lang.String.format;


public abstract class Hero {


/* Hero Fields */

    /* Field for Hero's name. */
    protected String heroName;

    /* Field for Hero's starting level. */
    protected int heroLevel = 1;

    /* Field for Hero's AttributesPerLevel.
    Array always has three elements for the
    amount of each attribute gained when
    using levelUp() method, in this order:
    attributesPerLevel[0] is for Strength
    attributesPerLevel[1] is for Dexterity
    attributesPerLevel[2] is for Intelligence */
    protected int[] attributesPerLevel;

    /* Field for Hero's starting Attributes,
    defined at the respective subclass constructor */
    protected HeroAttributes levelAttributes;

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
    armor a Hero is able to equip,defined
    at the respective subclass constructor*/
    protected ArmorType validArmor;

    /* Field to display which type of weapons a Hero is able
    to equip. Using String Array as many Hero subclasses
    can equip multiple types of weapons, defined
    at the respective subclass constructor*/
    protected ArrayList<WeaponType> validWeapon;

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
    each use by a predetermined amount, and it's level by 1.*/
    public void levelUp(int levelsGained){

        // Increasing Hero level
        increaseHeroLevel(levelsGained);
        // Increasing Hero Strength accordingly for each class
        levelAttributes.increaseStrength(attributesPerLevel[0]*levelsGained);
        // Increasing Hero Dexterity accordingly for each class
        levelAttributes.increaseDexterity(attributesPerLevel[1]*levelsGained);
        // Increasing Hero Intelligence accordingly for each class
        levelAttributes.increaseIntelligence(attributesPerLevel[2]*levelsGained);

        // Printing an appropriate message
        System.out.printf("""
                Congrats! You leveled up!
                You are now level %d
                """,heroLevel);
    }

    /* "Equip" method used for when a Hero needs to equip a piece of armor.
    Using two conditions, one for armorType and one for level, with this order,
    because even if an Item's level is higher than that of Hero's, if Hero level's
    up he may equip it then, but if the armorTypes don't match Hero can never equip the Item. */
    public  void equipItem(Armor armor) throws InvalidArmorException{

        /* Checking if Item's armorType is same as Hero's
        and throwing the respective custom exception if not */
        if (validArmor != armor.getArmorType()) {
            throw new InvalidArmorException("Your cannot equip armor of " + armor.getArmorType()+ " .");
        }
        /* Also checking if Item's level is same or lower with
        that of Hero's and throwing the respective custom exception if not */
        else if (heroLevel < armor.getItemLevel()) {
            throw new InvalidArmorException("Your level is too low. You need to be "
                    + "at least " + armor.getItemLevel() +  " level to be " +
                    "able to equip " + armor.getItemName());
        }
        /* If not, the Hero may equip the piece of armor */
        else {

            /* If the Hero already has an armor piece equipped in the
             same slot, old item's attributes have to be subtracted from
             the total before equipping a new one*/
            if (getHeroEquipment().get(armor.getSlot())!=null){
                levelAttributes.decreaseStrength(heroEquipment.get(armor.getSlot()).getStrength());
                levelAttributes.decreaseDexterity(heroEquipment.get(armor.getSlot()).getDexterity());
                levelAttributes.decreaseIntelligence(heroEquipment.get(armor.getSlot()).getIntelligence());
            }

            /* Finally equipping the item, hence "putting" it in the equipment
            HashMap, displaying an appropriate message and gaining its attributes */
            getHeroEquipment().put(armor.getSlot(), armor);
            System.out.println("Equipped "+ armor.getItemName());
            levelAttributes.increaseStrength(heroEquipment.get(armor.getSlot()).getStrength());
            levelAttributes.increaseDexterity(heroEquipment.get(armor.getSlot()).getDexterity());
            levelAttributes.increaseIntelligence(heroEquipment.get(armor.getSlot()).getIntelligence());
        }

    }

    /* "Equip" method used for when a Hero needs to equip a weapon. Again,
    same as above, using two conditions, one for armorType and one for level,
    with this order, because even if an Item's level is higher than that of Hero's,
    if Hero level's up he may equip it then, but if the armorTypes don't match
    Hero can never equip the Item.*/
    public void equipItem(Weapon weapon) throws InvalidWeaponException {

        /* Checking if Item's weaponType is same as Hero's
        and throwing the respective custom exception if not. */
        if (!validWeapon.contains(weapon.getWeaponType()))
        {
            throw new InvalidWeaponException("Your cannot equip of "
                    + weapon.getWeaponType()+ " type.");
        }
        /* Also checking if Item's level is same or lower with that of
         Hero's and throwing the respective custom exception if not. */
        else if (heroLevel < weapon.getItemLevel()) {
            throw new InvalidWeaponException("Your level is too low. You need to be "
                    + "at least" + weapon.getItemLevel() + " level to be " +
                    "able to equip " + weapon.getItemName());
            }
        /* If not, Hero may equip the weapon */
        else {
            /* Finally equipping the item, hence "putting" it in the equipment HashMap
            directly in the WEAPON slot and displaying an appropriate message */
            heroEquipment.put(Slot.WEAPON, weapon);
            System.out.println("Equipped "+ weapon.getItemName());
        }

    }

    /*Returning the value of the total of each Hero Attribute.*/
    public String totalHeroAttributes(){

        return format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                levelAttributes.getStrength(),
                levelAttributes.getDexterity(),
                levelAttributes.getIntelligence());
    }

    /* Calculating the damage a Hero does */
    public double damage() {


        /* Calculating the damage Hero does with just its primary attribute. */
        double totalDamage = (1 + getPrimary() / 100.0);

        /* Accessing the weapon's damage from the respective Slot
        and calculating the damage with weapon */
        try {
            totalDamage *= heroEquipment.get(Slot.WEAPON).getWeaponDamage();
        }
        /* if Hero doesn't have a weapon equipped, an NullPointerException
        will be thrown, which is caught bellow, because a hero can do damage
        even without a weapon equipped */
        catch (NullPointerException ignored) {}

        return totalDamage;


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
                className, heroLevel, levelAttributes.getStrength(), levelAttributes.getDexterity(),
                levelAttributes.getIntelligence(), damage());

      }

/* Hero Methods End*/

/*Getters and Setters*/

    /* Getter to return Hero's name */
    public String getHeroName() {
        return heroName;
    }

    /* Setter to increase Hero's Level by one */
    public void increaseHeroLevel(int heroLevel) {
        this.heroLevel += heroLevel;
    }

    /* Getter to return Hero's Level */
    public int getHeroLevel() {
        return heroLevel;
    }

    /* Getter to return a HashMap of Slot,Item pairs. */
    public HashMap<Slot, Item> getHeroEquipment() {
        return heroEquipment;
    }

    /* Getter to return Hero's valid ArmorType.*/
    public ArmorType getValidArmor() {
        return validArmor;
    }

    /* Getter to return Hero's valid Weapon type(s). */
    public ArrayList<WeaponType> getValidWeapon() {
        return validWeapon;
    }

    /* Getter to return Hero's class's name */
    public String getClassName() {
        return className;
    }

    /* Getter to return Hero's LevelAttributes */
    public HeroAttributes getLevelAttributes(){
        return levelAttributes;
    }

    /* Getting a Hero's primary attribute by using the Math.max() method */
    public int getPrimary(){

        return Math.max(Math.max(levelAttributes.getStrength(),
                levelAttributes.getDexterity()), levelAttributes.getIntelligence());
    }
    /*Getters and Setters End*/

    /* Overriding equals() as it
    is required for Testing*/
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}


