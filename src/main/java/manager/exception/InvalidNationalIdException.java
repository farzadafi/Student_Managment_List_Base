package manager.exception;

public class InvalidNationalIdException extends RuntimeException {
    public InvalidNationalIdException() {
    }

    public InvalidNationalIdException(String message) {
        super(message);
    }

    public InvalidNationalIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public void nationalIdChecker(String nationalId){
        if(nationalId.length() > 10 )
            throw new InvalidNationalIdException();
        if(nationalId.equals(""))
            throw new InvalidNationalIdException();
        for (Character ch:nationalId.toCharArray()) {
            if(!Character.isDigit(ch))
                throw new InvalidNationalIdException();
        }
    }
}
