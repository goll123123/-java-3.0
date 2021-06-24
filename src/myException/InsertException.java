package myException;

public class InsertException extends RuntimeException{
    public InsertException() {
    }

    public InsertException(String message) {
        super(message);
    }
}
