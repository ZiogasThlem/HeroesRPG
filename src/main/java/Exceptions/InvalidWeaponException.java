package Exceptions;

public class InvalidWeaponException extends Exception{

    /* Custom Exception for when a Hero tries
     to equip a weapon of too high level.*/
    public InvalidWeaponException(String errorMessage){
        super(errorMessage);
    }
}
