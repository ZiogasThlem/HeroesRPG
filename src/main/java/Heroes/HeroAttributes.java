package Heroes;

import java.util.Objects;

public class HeroAttributes{

/* Fields */

    /* Hero Attributes */
    private int strength;
    private int dexterity;
    private int intelligence;

/* Fields End */


    /* Constructor with all Hero
    Attributes for subclasses */
    public HeroAttributes(int strength, int dexterity, int intelligence) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

/* Getters and Setters */

    /* Getter to return Strength Attribute */
    public int getStrength() {
        return strength;
    }

    /* Getter to return Dexterity Attribute */
    public int getDexterity() {
        return dexterity;
    }

    /* Getter to return Intelligence Attribute */
    public int getIntelligence() {
        return intelligence;
    }



    /* Setter to increase Strength Attribute */
    public void increaseStrength(int strength) {
        this.strength += strength;
    }

    /* Setter to increase Dexterity Attribute */
    public void increaseDexterity(int dexterity) {
        this.dexterity += dexterity;
    }

    /* Setter to increase Intelligence Attribute */
    public void increaseIntelligence(int intelligence) {
        this.intelligence += intelligence;
    }


    /* Setter to decrease Strength Attribute */
    public void decreaseStrength(int strength) {
        this.strength -= strength;
    }

    /* Setter to decrease Dexterity Attribute */
    public void decreaseDexterity(int dexterity) {
        this.dexterity -= dexterity;
    }

    /* Setter to decrease Intelligence Attribute */
    public void decreaseIntelligence(int intelligence) {
        this.intelligence -= intelligence;
    }

/* Getters and Setters */

    /* Overriding equals() as it
     is required for Testing*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroAttributes that = (HeroAttributes) o;
        return strength == that.strength && dexterity == that.dexterity && intelligence == that.intelligence;
    }
}
