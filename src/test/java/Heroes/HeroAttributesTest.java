package Heroes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HeroAttributesTest {

    HeroAttributes attributes = new HeroAttributes(140, 18, 6);

    @Test
    public void getStrengthTest() {

        int expected = attributes.getStrength();
        int input = 140;
        int input2 = 20;

        Assertions.assertEquals(expected, input);
        Assertions.assertNotEquals(expected, input2);

    }

    @Test
    public void getDexterityTest() {

        int expected = attributes.getDexterity();
        int input = 18;
        int input2 = 10;

        Assertions.assertEquals(expected, input);
        Assertions.assertNotEquals(expected, input2);
    }

    @Test
    public void getIntelligenceTest() {

        int expected = attributes.getIntelligence();
        int input = 6;
        int input2 = 10;

        Assertions.assertEquals(expected, input);
        Assertions.assertNotEquals(expected, input2);
    }

    @Test
    public void increaseStrengthTest() {


        int expected = 150;
        attributes.increaseStrength(10);
        int input = attributes.getStrength();

        Assertions.assertEquals(expected, input);


    }

    @Test
    public void increaseDexterityTest() {

        int expected = 30;
        attributes.increaseDexterity(12);
        int input = attributes.getDexterity();

        Assertions.assertEquals(expected, input);



    }

    @Test
    public void increaseIntelligenceTest() {

        int expected = 16;
        attributes.increaseIntelligence(10);
        int input = attributes.getIntelligence();

        Assertions.assertEquals(expected, input);

    }

    @Test
    public void decreaseStrengthTest() {


        int expected = 100;
        attributes.decreaseStrength(40);
        int input = attributes.getStrength();

        Assertions.assertEquals(expected, input);


    }

    @Test
    public void decreaseDexterityTest() {

        int expected = 10;
        attributes.decreaseDexterity(8);
        int input = attributes.getDexterity();

        Assertions.assertEquals(expected, input);



    }

    @Test
    public void decreaseIntelligenceTest() {

        int expected = 1;
        attributes.decreaseIntelligence(5);
        int input = attributes.getIntelligence();

        Assertions.assertEquals(expected, input);

    }

}