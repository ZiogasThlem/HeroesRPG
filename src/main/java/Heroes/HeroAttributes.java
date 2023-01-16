package Heroes;

import java.util.Arrays;

public class HeroAttributes{

/* Fields */

    /* Hero Attributes */
    private int strength;
    private int dexterity;
    private int intelligence;

/* Fields End */

/* Constructors */

    /* Default Constructor with no
    arguments for parent class */
    public HeroAttributes() {

    }

    /* Constructor with all Hero
    Attributes for subclasses */
    public HeroAttributes(int strength, int dexterity, int intelligence) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }
/* Constructors End*/

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

    /* Getter to return all three
    Attributes in a formatted String.  */
    public String getHeroAttributesString(){

        return "Strength: "+ getStrength() +
                "\nDexterity: "+ getDexterity()+
                "\nIntelligence: "+ getIntelligence();
    }



    /* Setter to increase Strength Attribute */
    public void setStrength(int strength, int ... ints) {
        this.strength = strength + Arrays.stream(ints).sum();
    }

    /* Setter to increase Dexterity Attribute */
    public void setDexterity(int dexterity, int ... ints) {
        this.dexterity = dexterity + Arrays.stream(ints).sum();
    }

    /* Setter to increase Intelligence Attribute */
    public void setIntelligence(int intelligence, int ... ints) {
        this.intelligence = intelligence + Arrays.stream(ints).sum();
    }


/* Getters and Setters */


}
