package Heroes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HeroAttributesTest {

    HeroAttributes attributes = new HeroAttributes(140, 18, 6);


/* Testing Getters with object set values */

    /* Testing getStrength */
    @Test
    public void getStrengthTest() {

        int expected = 140;
        int actual = attributes.getStrength();

        Assertions.assertEquals(expected, actual);

    }
    /* Testing getDexterity */
    @Test
    public void getDexterityTest() {

        int expected= 18;
        int actual = attributes.getDexterity();

        Assertions.assertEquals(expected, actual);

    }

    /* Testing getIntelligence */
    @Test
    public void getIntelligenceTest() {

        int expected = 6;
        int actual = attributes.getIntelligence();

        Assertions.assertEquals(expected, actual);

    }

/* Testing Getters End */

/* Testing Setters */

    /* The way that I run tests here is the following:
    Get the original value save it in expected,increase
    (or decrease) by n amount, add (or subtract) n to
    expected value and get the actual value from the getter. */

    /* Testing increaseStrength */
    @Test
    public void increaseStrengthTest() {


        int expected = attributes.getStrength();
        attributes.increaseStrength(10);
        int expected_afterIncrease = expected + 10;
        int actual = attributes.getStrength();

        Assertions.assertEquals(expected_afterIncrease, actual);

    }

    /* Testing increaseDexterity */
    @Test
    public void increaseDexterityTest() {

        int expected = attributes.getDexterity();
        attributes.increaseDexterity(12);
        int expected_afterIncrease = expected + 12;
        int actual = attributes.getDexterity();

        Assertions.assertEquals(expected_afterIncrease, actual);

    }
    /* Testing increaseIntelligence */
    @Test
    public void increaseIntelligenceTest() {

        int expected = attributes.getIntelligence();
        attributes.increaseIntelligence(105);
        int expected_afterIncrease = expected + 105;
        int actual = attributes.getIntelligence();

        Assertions.assertEquals(expected_afterIncrease, actual);

    }

    /* Testing decreaseStrength */
    @Test
    public void decreaseStrengthTest() {

        /* Testing decreaseStrength */
        int expected = attributes.getStrength();
        attributes.decreaseStrength(40);
        int expected_afterDecrease = expected - 40;
        int actual = attributes.getStrength();

        Assertions.assertEquals(expected_afterDecrease, actual);

    }

    /* Testing decreaseDexterity */
    @Test
    public void decreaseDexterityTest() {

        int expected = attributes.getDexterity();
        attributes.decreaseDexterity(8);
        int expected_afterDecrease = expected - 8;
        int actual = attributes.getDexterity();

        Assertions.assertEquals(expected_afterDecrease, actual);

    }

    /* Testing decreaseIntelligence */
    @Test
    public void decreaseIntelligenceTest() {

        int expected = attributes.getIntelligence();
        attributes.decreaseIntelligence(5);
        int expected_afterDecrease = expected - 5;
        int actual = attributes.getIntelligence();

        Assertions.assertEquals(expected_afterDecrease, actual);

    }

/* Testing Setters End */

}