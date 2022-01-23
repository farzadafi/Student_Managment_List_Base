package Manager;

public class InvalidUnitNumber extends RuntimeException{
    public InvalidUnitNumber() {
    }

    public InvalidUnitNumber(String message) {
        super(message);
    }

    public InvalidUnitNumber(String message, Throwable cause) {
        super(message, cause);
    }

    public void unitNumberChecker(int unitNumber){
        if(unitNumber > 4 || unitNumber < 1 )
            throw new InvalidUnitNumber("You enter a wrong unit number!");
    }
}
