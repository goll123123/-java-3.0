package myException;

public class RegisterException extends RuntimeException {
    public RegisterException() {
    }

    public RegisterException(String message) {
        super(message);
    }
}
