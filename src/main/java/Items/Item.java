package Items;

import Heroes.HeroAttributes;

public abstract class Item {


    protected String itemName;

    protected int levelRequired;

    protected HeroAttributes armorAttributes = new HeroAttributes();

    protected Slot slot;


/* Constructors */

    /* Armor Constructor */
    public Item(String itemName, int levelRequired, Slot slot) {
        this.itemName = itemName;
        this.levelRequired = levelRequired;
        this.slot = slot;
    }

    /* Weapon Constructor */
    public Item(String itemName, int levelRequired) {
        this.itemName = itemName;
        this.levelRequired = levelRequired;
    }
/* Constructors End */
    public String getItemName() {
        return itemName;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    public Slot getSlot() {
        return this.slot;
    }
    public int getStrength() {
        return armorAttributes.getStrength();
    }
    public int getDexterity() {
        return armorAttributes.getDexterity();
    }
    public int getIntelligence() {
        return armorAttributes.getIntelligence();
    }

    public int getWeaponDamage(){return 0;}


}
