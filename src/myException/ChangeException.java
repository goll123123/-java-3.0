package myException;

public class ChangeException extends RuntimeException{
    public ChangeException() {
    }

    public ChangeException(String message) {
        super(message);
    }
}
