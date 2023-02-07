package bg.game.services.order;


import bg.game.entities.Game;
import java.util.List;
public interface OrderService {
    void addGameForShopping(Game game);
    void removeGameFromShoppingCart(Game game);
    List<Game> purchaseGame();
}
