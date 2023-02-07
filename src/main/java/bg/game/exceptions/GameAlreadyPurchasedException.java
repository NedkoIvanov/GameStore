package bg.game.exceptions;

public class GameAlreadyPurchasedException extends RuntimeException {
    private String s;
    public GameAlreadyPurchasedException(String s) {
        super(s);
    }
}
