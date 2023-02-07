package bg.game.services.order;

import bg.game.entities.Game;
import bg.game.entities.Order;
import bg.game.repositories.GameRepository;
import bg.game.repositories.OrderRepository;
import bg.game.services.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private GameRepository gameRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, GameRepository gameRepository) {
        this.orderRepository = orderRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public void addGameForShopping(Game game) {

       List<Order> orders = this.orderRepository.findAll();
       for(int i=0;i< orders.size();i++){
           orders.get(i).getProducts().add(game);
       }


    }

    @Override
    public void removeGameFromShoppingCart(Game game) {

        List<Order> orders = this.orderRepository.findAll();
        for(int i=0;i<orders.size();i++){
            orders.get(i).getProducts().remove(game);
        }

    }

    @Override
    public List<Game> purchaseGame() {

        List<Order> orders = this.orderRepository.findAll();
        List<Game> purchasedGames = new ArrayList<>();
        for(int i=0;i<orders.size();i++){
            purchasedGames.addAll(orders.get(i).getProducts());
        }
        return purchasedGames;
    }
}
