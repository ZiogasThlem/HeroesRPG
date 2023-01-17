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
    protected HeroAttributes armorAttributes;

    /* Field for Item's damage.
    Specific for Weapon subclass. */
    protected int weaponDamage;

    /* Field for Item's Slot. */
    protected Slot slot;

    protected WeaponType weaponType;
    protected ArmorType armorType;

    /* Item Fields End */

/* Constructors */

    /* Armor subclass Constructor */
    public Item(String itemName, int itemLevel, Slot slot, ArmorType armorType) {
        this.itemName = itemName;
        this.itemLevel = itemLevel;
        this.slot = slot;
        this.armorType = armorType;

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
        return slot;
    }

    public HeroAttributes getArmorAttributes() {
        return armorAttributes;
    }

    /* Getter to return Item's Strength
        Overridden by Armor subclass. */
    public int getStrength() {
        return getArmorAttributes().getStrength();
    }

    /* Getter to return Item's Dexterity
    Overridden by Armor subclass. */
    public int getDexterity() {
        return getArmorAttributes().getDexterity();
    }

    /* Getter to return Item's Intelligence
    Overridden by Armor subclass. */
    public int getIntelligence() {
        return getArmorAttributes().getIntelligence();
    }

    /* Getter to return Item's damage.
    Specific for Weapon subclass. */
    public int getWeaponDamage(){
        return weaponDamage;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
