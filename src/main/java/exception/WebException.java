package exception;

public class WebException extends Exception {
    private int code;

    public WebException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
