package bg.game.exceptions;

public class GameNotFoundException extends RuntimeException {

    private String reason;
    public GameNotFoundException(String reason) {
        super(reason);
    }
}
