package server.home.exeption;

/**
 * Created by lterminiello on 12/10/16.
 */
public class UnknownRoomExeption extends RuntimeException {
    public UnknownRoomExeption() {
    }

    public UnknownRoomExeption(String message) {
        super(message);
    }

    public UnknownRoomExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownRoomExeption(Throwable cause) {
        super(cause);
    }

    public UnknownRoomExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
