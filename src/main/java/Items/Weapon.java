package Items;


public class Weapon extends Item{

/* Weapon specific Fields */
    private final Slot slot = Slot.WEAPON;

    private final WeaponType weaponType;

/* Constructor */
    public Weapon(String itemName, int levelRequired,int weaponDamage, WeaponType weaponType) {
        super(itemName, levelRequired, weaponDamage);
        this.weaponType = weaponType;
    }


    /* Overridden Getter for Weapon specific Slot */
    @Override
    public Slot getSlot() {
        return slot;
    }

    @Override
    public int getWeaponDamage() {
        return weaponDamage;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }
}
