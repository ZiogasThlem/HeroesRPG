package Items;


public class Weapon extends Item{

/* Constructor */
    public Weapon(String itemName, int levelRequired,int weaponDamage, WeaponType weaponType) {
        super(itemName, levelRequired, weaponDamage);
        this.weaponType = weaponType;
        this.slot = Slot.WEAPON;
    }

}
