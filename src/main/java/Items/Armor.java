package Items;

import Heroes.HeroAttributes;

public class Armor extends Item{

    private final HeroAttributes armorAttributes;
    public Armor(String itemName, int levelRequired, Slot slot,HeroAttributes armorAttributes) {
        super(itemName, levelRequired, slot);
        this.armorAttributes = armorAttributes;
    }

    public String getArmorAttributes() {
        return armorAttributes.getHeroAttributes();
    }

    @Override
    public int getStrength() {
        return armorAttributes.getStrength();
    }

    @Override
    public int getDexterity() {
        return armorAttributes.getDexterity();
    }

    @Override
    public int getIntelligence() {
        return armorAttributes.getIntelligence();
    }
}


