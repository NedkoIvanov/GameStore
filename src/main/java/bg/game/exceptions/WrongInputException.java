package bg.game.exceptions;

public class WrongInputException extends RuntimeException{
    String reason;
    public WrongInputException(String reason){
        super(reason);
    }

}
