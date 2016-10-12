package server.home.exeption;

/**
 * Created by lterminiello on 12/10/16.
 */
public class NotAllowedActionExeption extends RuntimeException{
    public NotAllowedActionExeption() {
    }

    public NotAllowedActionExeption(String message) {
        super(message);
    }

    public NotAllowedActionExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAllowedActionExeption(Throwable cause) {
        super(cause);
    }

    public NotAllowedActionExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
