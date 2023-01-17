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

    protected int[] attributesPerLevel;

    /* Field for Hero's starting Attributes */
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
    armor a Hero is able to equip. */
    protected ArmorType validArmor;

    /* Field to display which type of weapons a Hero is able
     to equip. Using String Array as many Hero subclasses
     can equip multiple types of weapons*/
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

        increaseHeroLevel(levelsGained);
        getLevelAttributes().increaseStrength(getAttributesPerLevel()[0]*levelsGained);
        getLevelAttributes().increaseDexterity(getAttributesPerLevel()[1]*levelsGained);
        getLevelAttributes().increaseIntelligence(getAttributesPerLevel()[2]*levelsGained);

        System.out.printf("""
                Congrats! You leveled up!
                You are now level %d
                """,getHeroLevel());
    }

    /* "Equip" method used for when a Hero needs to equip a piece of armor */
    public  void equipItem(Armor armor) throws InvalidArmorException{

        if (getValidArmor() != armor.getArmorType()) {
            throw new InvalidArmorException("Your cannot equip armor of " + armor.getArmorType()+ " .");
        }
        else if (getHeroLevel() < armor.getItemLevel()) {
            throw new InvalidArmorException("Your level is too low. You need to be "
                    + "at least " + armor.getItemLevel() +  " level to be " +
                    "able to equip " + armor.getItemName());
        }
        /* If not, the Hero may equip the piece of armor */
        else {

            if (getHeroEquipment().get(armor.getSlot())!=null){
                levelAttributes.decreaseStrength(getHeroEquipment().get(armor.getSlot()).getStrength());
                levelAttributes.decreaseDexterity(getHeroEquipment().get(armor.getSlot()).getDexterity());
                levelAttributes.decreaseIntelligence(getHeroEquipment().get(armor.getSlot()).getIntelligence());
            }

            getHeroEquipment().put(armor.getSlot(), armor);
            System.out.println("Equipped "+ armor.getItemName());
            levelAttributes.increaseStrength(getHeroEquipment().get(armor.getSlot()).getStrength());
            levelAttributes.increaseDexterity(getHeroEquipment().get(armor.getSlot()).getDexterity());
            levelAttributes.increaseIntelligence(getHeroEquipment().get(armor.getSlot()).getIntelligence());
        }

    }

    /* "Equip" method used for when a Hero needs to equip a weapon*/
    public void equipItem(Weapon weapon) throws InvalidWeaponException {

        /*
        Checking if Hero's level is lower than that
        of the Weapon, and if the Hero can equip weapons of this type
        If one of them is true, an exception is throw to inform the user that
        his Hero's level is too low to equip this weapon */
        if (!getValidWeapon().contains(weapon.getWeaponType()))
        {
            throw new InvalidWeaponException("Your cannot equip of "
                    + weapon.getWeaponType()+ " type.");
        }
        else if (getHeroLevel() < weapon.getItemLevel()) {
            throw new InvalidWeaponException("Your level is too low. You need to be "
                    + "at least" + weapon.getItemLevel() + " level to be " +
                    "able to equip " + weapon.getItemName());
            }
        /* If not, Hero may equip the weapon */
        else {
            getHeroEquipment().put(Slot.WEAPON, weapon);
            System.out.println("Equipped "+ weapon.getItemName());
        }

    }

    /*
    Calculating the total of each Hero Attribute as a sum of the
    Hero's Level Attributes and Armor Attributes for all pieces
    of equipment ,excluding weapons as they have none. Storing
    them into variables as they are required for multiple methods.
    */


    /*Returning the value of the total of each Hero Attribute.*/
    public String totalHeroAttributes(){
        return format("""
                      Strength: %d
                      Dexterity: %d
                      Intelligence: %d
                      """,
                getLevelAttributes().getStrength(), getLevelAttributes().getDexterity(),
                getLevelAttributes().getIntelligence());
    }

    /* Calculating the damage a Hero does */
    public double damage() {


        /* Accessing the weapon's damage from the respective Slot
        and calculating the damage with the given equation*/
        double totalDamage = (1 + getPrimary() / 100.0);


        try {
            totalDamage *= getHeroEquipment().get(Slot.WEAPON).getWeaponDamage();
        } catch (NullPointerException ignored) {
        }
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
                        Damage %f""", getHeroName(),
                getClassName(), getHeroLevel(), getLevelAttributes().getStrength(), getLevelAttributes().getDexterity(),
                getLevelAttributes().getIntelligence(), damage());

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

    /* Getter to return Hero's current Attributes
    Overridden in each subclass*/

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
    public ArrayList<WeaponType> getValidWeapon() {
        return validWeapon;
    }

    /* Getter to return Hero's class's name */
    public String getClassName() {
        return className;
    }

    public HeroAttributes getLevelAttributes(){
        return levelAttributes;
    }


    /* Getting a Hero's primary attribute by using the Math.max() method */
    public int getPrimary(){
        return Math.max(Math.max(getLevelAttributes().getStrength(),
                getLevelAttributes().getDexterity()), getLevelAttributes().getIntelligence());
    }

    public int[] getAttributesPerLevel() {
        return attributesPerLevel;
    }

    /*Getters and Setters End*/


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}


