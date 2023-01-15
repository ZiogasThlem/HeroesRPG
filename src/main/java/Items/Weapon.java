package Items;


public class Weapon extends Item{

/* Weapon specific Fields */
    private final Slot slot = Slot.WEAPON;

/* Constructor */
    public Weapon(String itemName, int levelRequired,int weaponDamage) {
        super(itemName, levelRequired,weaponDamage);
    }


    /* Overridden Getter for Weapon specific Slot */
    @Override
    public Slot getSlot() {
        return slot;
    }
}
