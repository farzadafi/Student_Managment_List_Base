package manager;

public class InvalidGrade extends RuntimeException {
    public InvalidGrade() {
    }

    public InvalidGrade(String message) {
        super(message);
    }

    public InvalidGrade(String message, Throwable cause) {
        super(message, cause);
    }

    public void invalidGrade(int grade){
        if(grade > 20 || grade < 0 )
            throw new InvalidGrade("You enter a wrong grade");
    }
}
