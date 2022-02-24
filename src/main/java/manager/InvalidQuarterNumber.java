package manager;

public class InvalidQuarterNumber extends RuntimeException {
    public InvalidQuarterNumber() {
    }

    public InvalidQuarterNumber(String message) {
        super(message);
    }

    public InvalidQuarterNumber(String message, Throwable cause) {
        super(message, cause);
    }

    public void invalidQuarterNumber(int quarterNumber){
        if(quarterNumber > 10 || quarterNumber < 1 ){
            throw new InvalidQuarterNumber("You enter a wrong quarter number!");
        }
    }
}
