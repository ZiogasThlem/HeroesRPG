package Items;


import Items.enums.Slot;
import Items.enums.WeaponType;

public class Weapon extends Item{

/* Inherited Constructor */
    public Weapon(String itemName, int levelRequired,int weaponDamage, WeaponType weaponType) {
        super(itemName, levelRequired, weaponDamage);
        /* Weapon type is specified when Weapon is created */
        this.weaponType = weaponType;
        /* Item Slot for Weapon is always weapon */
        this.slot = Slot.WEAPON;
    }

}
