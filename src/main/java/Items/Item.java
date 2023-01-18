package Items;

import Heroes.HeroAttributes;
import Items.enums.ArmorType;
import Items.enums.Slot;
import Items.enums.WeaponType;

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

    /* Field for Item's WeaponType.
    Specific for Weapon. */
    protected WeaponType weaponType;

    /* Field for Item's ArmorType.
    Specific for Armor. */
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

/* Getters */

    /* Getter to return Item's name */
    public String getItemName() {
        return itemName;
    }

    /* Getter to return Item's Level */
    public int getItemLevel() {
        return itemLevel;
    }

    /* Getter to return Item's Slot.*/
    public Slot getSlot() {
        return slot;
    }

    /* Getter to return Item's HeroAttributes.
    Specific to Armor. */
    public HeroAttributes getArmorAttributes() {
        return armorAttributes;
    }

    /* Getter to return Item's Strength.
    Specific to Armor. */
    public int getStrength() {
        return armorAttributes.getStrength();
    }

    /* Getter to return Item's Dexterity.
    Specific to Armor. */
    public int getDexterity() {
        return armorAttributes.getDexterity();
    }

    /* Getter to return Item's Intelligence.
    Specific to Armor.*/
    public int getIntelligence() {
        return armorAttributes.getIntelligence();
    }

    /* Getter to return Item's ArmorType.
    Specific to Armor. */
    public ArmorType getArmorType() {
        return armorType;
    }

    /* Getter to return Item's damage.
    Specific to Weapon.*/
    public int getWeaponDamage(){
        return weaponDamage;
    }


    /* Getter to return Item's WeaponType.
    Specific to Weapon. */
    public WeaponType getWeaponType() {
        return weaponType;
    }

/* Getters End */


    /* Overriding equals() as it
    is required for Testing*/
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
