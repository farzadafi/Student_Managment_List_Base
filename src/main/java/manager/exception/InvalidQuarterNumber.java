package manager.exception;

public class InvalidQuarterNumber extends RuntimeException {
    public InvalidQuarterNumber() {
    }

    public InvalidQuarterNumber(String message) {
        super(message);
    }

    public InvalidQuarterNumber(String message, Throwable cause) {
        super(message, cause);
    }


}
