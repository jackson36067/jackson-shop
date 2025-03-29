package  com.jackson.exception;
public class EmailExistException extends BaseException {
    public EmailExistException() {}
    public EmailExistException(String message) {
        super(message);
    }
}