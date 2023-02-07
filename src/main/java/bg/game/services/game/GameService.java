package bg.game.services.game;

import bg.game.dto.GameDTO;
import bg.game.entities.Game;

import java.text.ParseException;

public interface GameService {
    void addGame(GameDTO gameDTO);
    void editGame(int id,String[] value) throws ParseException;

    void deleteGame(int id);

    void printAll();

    void detailGame(String title);

    Game findGameByTitle(String title);
}
