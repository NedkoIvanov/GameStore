package bg.game.services.user;

import bg.game.dto.LoginDTO;
import bg.game.dto.RegisterDTO;
import bg.game.entities.Game;
import bg.game.entities.User;
import java.util.List;

public interface UserService {
    User register(RegisterDTO register);
    User login(LoginDTO login);
    void logOut();

    User getLoggedUser();

    List<Game> ownedGames();

}
