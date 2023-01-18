package Exceptions;

public class InvalidArmorException extends Exception{

    /* Custom Exception for when a Hero tries
    to equip a piece of armor of too high level
    or one of invalid ArmorType.*/

    public InvalidArmorException(String errorMessage){
        super(errorMessage);
    }

}
