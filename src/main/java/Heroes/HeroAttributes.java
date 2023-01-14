package Heroes;

public class HeroAttributes{

    private int strength,dexterity,intelligence;


    public HeroAttributes(int strength, int dexterity, int intelligence) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }
    public int getIntelligence() {
        return intelligence;
    }

//    to be modified to have extra parameter to add specific amount for each class
//    public void setStrength(int strength) {
//        this.strength = strength;
//    }
//
//    public void setDexterity(int dexterity) {
//        this.dexterity = dexterity;
//    }
//
//    public void setIntelligence(int intelligence) {
//        this.intelligence = intelligence;
//    }


}
