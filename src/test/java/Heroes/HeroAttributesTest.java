package Heroes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HeroAttributesTest {

    HeroAttributes attributes = new HeroAttributes(140, 18, 6);

    @Test
    public void getStrengthTest() {

        int expected = 140;
        int actual = attributes.getStrength();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void getDexterityTest() {

        int expected= 18;
        int actual = attributes.getDexterity();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void getIntelligenceTest() {

        int expected = 6;
        int actual = attributes.getIntelligence();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void increaseStrengthTest() {


        int expected = 150;
        attributes.increaseStrength(10);
        int actual = attributes.getStrength();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void increaseDexterityTest() {

        int expected = 30;
        attributes.increaseDexterity(12);
        int actual = attributes.getDexterity();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void increaseIntelligenceTest() {

        int expected = 16;
        attributes.increaseIntelligence(10);
        int actual = attributes.getIntelligence();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void decreaseStrengthTest() {


        int expected = 100;
        attributes.decreaseStrength(40);
        int actual = attributes.getStrength();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void decreaseDexterityTest() {

        int expected = 10;
        attributes.decreaseDexterity(8);
        int actual = attributes.getDexterity();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void decreaseIntelligenceTest() {

        int expected = 1;
        attributes.decreaseIntelligence(5);
        int actual = attributes.getIntelligence();

        Assertions.assertEquals(expected, actual);

    }

}