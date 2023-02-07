package bg.game.exceptions;

public class UserLoggException extends RuntimeException {
    String reason;
    public UserLoggException(String reason){
        super(reason);
    }


}
