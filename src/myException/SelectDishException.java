package myException;

public class SelectDishException extends RuntimeException {
    public SelectDishException() {
    }

    public SelectDishException(String message) {
        super(message);
    }
}
