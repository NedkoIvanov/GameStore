package bg.game.exceptions;

public class UserNotAdminException extends RuntimeException {

    private String s;
    public UserNotAdminException(String s) {
        super(s);
    }
}
