package server.home.exeption;

public class UnknownArtifactExeption extends RuntimeException {
    public UnknownArtifactExeption() {
    }

    public UnknownArtifactExeption(String message) {
        super(message);
    }

    public UnknownArtifactExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownArtifactExeption(Throwable cause) {
        super(cause);
    }

    public UnknownArtifactExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
