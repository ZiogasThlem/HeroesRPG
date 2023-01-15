package Items;


public class Weapon extends Item{

    private final int weaponDamage;
    private final Slot slot = Slot.WEAPON;
    public Weapon(String itemName, int levelRequired,int weaponDamage) {
        super(itemName, levelRequired);
        this.weaponDamage = weaponDamage;
    }
    public int getWeaponDamage() {
        return weaponDamage;
    }
    @Override
    public Slot getSlot() {
        return slot;
    }
}
