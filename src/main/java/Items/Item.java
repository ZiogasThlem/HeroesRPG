package Items;

import Heroes.HeroAttributes;

public abstract class Item {

/* Item Fields */

    /* Field for Item's name. */
    protected String itemName;

    /* Field for Item's Level. */
    protected int itemLevel;

    /* Field for Item's Attributes.
    Specific for Armor subclass. */
    protected HeroAttributes armorAttributes = new HeroAttributes();

    /* Field for Item's damage.
    Specific for Weapon subclass. */
    protected int weaponDamage;

    /* Field for Item's Slot. */
    protected Slot slot;

/* Item Fields End */

/* Constructors */

    /* Armor subclass Constructor */
    public Item(String itemName, int itemLevel, Slot slot) {
        this.itemName = itemName;
        this.itemLevel = itemLevel;
        this.slot = slot;
    }

    /* Weapon subclass Constructor */
    public Item(String itemName, int itemLevel, int weaponDamage) {
        this.itemName = itemName;
        this.itemLevel = itemLevel;
        this.weaponDamage = weaponDamage;
    }

/* Constructors End */

    /* Getter to return Item's name */
    public String getItemName() {
        return itemName;
    }

    /* Getter to return Item's Level */
    public int getItemLevel() {
        return itemLevel;
    }

    /* Getter to return Item's Slot.
    Specific for Weapon subclass.*/
    public Slot getSlot() {
        return this.slot;
    }

    /* Getter to return Item's Strength
    Overridden by Armor subclass. */
    public int getStrength() {
        return armorAttributes.getStrength();
    }

    /* Getter to return Item's Dexterity
    Overridden by Armor subclass. */
    public int getDexterity() {
        return armorAttributes.getDexterity();
    }

    /* Getter to return Item's Intelligence
    Overridden by Armor subclass. */
    public int getIntelligence() {
        return armorAttributes.getIntelligence();
    }

    /* Getter to return Item's damage.
    Specific for Weapon subclass. */
    public int getWeaponDamage(){
        return weaponDamage;}

}
