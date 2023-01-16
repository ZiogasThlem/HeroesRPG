package Heroes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    public void setStrengthTest() {

        int expected = attributes.getStrength();
        int input = 140;
        int input2 = 20;
        attributes.setStrength(input);

        Assertions.assertEquals(expected, input);
        Assertions.assertNotEquals(expected, input2);

    }

    @Test
    public void setDexterityTest() {

        int expected = attributes.getDexterity();
        int input = 18;
        int input2 = 20;


        attributes.setDexterity(input);
        Assertions.assertEquals(expected, input);
        Assertions.assertNotEquals(expected, input2);

    }

    @Test
    public void setIntelligenceTest() {

        int expected = attributes.getIntelligence();
        int input = 6;
        int input2 = 20;

        attributes.setIntelligence(input);
        Assertions.assertEquals(expected, input);
        Assertions.assertNotEquals(expected, input2);

    }




}