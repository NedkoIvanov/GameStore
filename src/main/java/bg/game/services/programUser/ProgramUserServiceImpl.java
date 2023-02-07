package bg.game.services.programUser;

import bg.game.dto.GameDTO;
import bg.game.dto.LoginDTO;
import bg.game.dto.RegisterDTO;
import bg.game.entities.Game;
import bg.game.entities.User;
import bg.game.exceptions.GameAlreadyPurchasedException;
import bg.game.exceptions.GameNotFoundException;
import bg.game.exceptions.UserNotAdminException;
import bg.game.services.user.UserService;
import bg.game.services.game.GameService;
import bg.game.services.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

@Service
public class ProgramUserServiceImpl implements ProgramUserService {
    private UserService userService;
    private GameService gameService;
    private OrderService orderService;

    @Autowired
    public ProgramUserServiceImpl(UserService userService, GameService gameService, OrderService orderServivce) {
        this.userService = userService;
        this.gameService = gameService;
        this.orderService = orderServivce;
    }

    @Override
    public void commandsFromUser() {
        Scanner s = new Scanner(System.in);
        this.informationForUser();
        String[] info = s.nextLine().split("\\|");

        while (!info[0].equals("End of program")) {

            String command = info[0];

            if (command.equals("RegisterUser")) {

                this.registerUser(info);

            } else if (command.equals("LoginUser")) {

                this.loginUser(info);

            } else if (command.equals("LogOut")) {

                userService.logOut();

            } else if (command.equals("AddGame")) {

                this.addGame(info);

            } else if (command.equals("EditGame")) {

                this.editGame(info);

            } else if (command.equals("DeleteGame")) {

                this.throwingNotAdmin("delete");
                this.gameService.deleteGame(Integer.parseInt(info[1]));

            } else if (command.equals("AllGame")) {

                this.gameService.printAll();

            } else if (command.equals("DetailGame")) {

                this.gameService.detailGame(info[1]);

            } else if (command.equals("OwnedGame")) {

                this.ownedGame(info);

            } else if (command.equals("AddItem")) {

                this.addItem(info);

            } else if (command.equals("RemoveItem")) {

                this.removeItem(info);

            } else if (command.equals("BuyItem")) {

                this.buyItem(info);

            } else {

                System.out.println("Wrong command input.Try again!");

            }

            info = s.nextLine().split("\\|");
        }
    }

    private void informationForUser(){
        System.out.println("Your options:");
        System.out.println("Register");
        System.out.println("Login");
        System.out.println("Log out");
        System.out.println("Add game");
        System.out.println("Edit game");
        System.out.println("Delete game");
        System.out.println("All games");
        System.out.println("Details about games");
        System.out.println("Owned games");
        System.out.println("Add game to shopping cart");
        System.out.println("Remove game from shopping cart");
        System.out.println("Buy game");
        System.out.println("Type - 'end of program' to stop the program.");
    }

    private void throwingNotAdmin(String cause) {

        if(!this.userService.getLoggedUser().isAdmin()){
            throw new UserNotAdminException("User is not admin , he cannot " + cause  + " games");
        }

    }

    private void registerUser(String[] info){
        RegisterDTO register = new RegisterDTO(info);
        this.userService.register(register);
    }

    private void loginUser(String[] info){
        LoginDTO login = new LoginDTO(info);
        User logged = this.userService.login(login);
        if (logged == null) {
            System.out.println("Error logging user!");
        } else {
            System.out.println("Successfully logged in " + logged.getFullName());
        }
    }

    private void addGame(String[] info){
        this.throwingNotAdmin("add");
        GameDTO games = new GameDTO(info);
        this.gameService.addGame(games);

    }

    private void editGame(String[] info){
        this.throwingNotAdmin("edit");
        try {
            this.gameService.editGame(Integer.parseInt(info[1]),info);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void ownedGame(String[] info){
        List<Game> ownedGames = this.userService.ownedGames();
        for(int i=0;i<ownedGames.size();i++){
            System.out.println(ownedGames.get(i).getTitle());
        }
    }

    private void addItem(String[] info){
        if(this.userService.ownedGames().contains(this.gameService.findGameByTitle(info[1]))){
            throw new GameAlreadyPurchasedException("Game has been added to shopping cart.");
        }
        this.orderService.addGameForShopping(this.gameService.findGameByTitle(info[1]));
        System.out.println(info[1] + " added to cart.");
    }

    private void removeItem(String[] info) {
        if(this.userService.ownedGames().contains(this.gameService.findGameByTitle(info[1]))){
            throw new GameNotFoundException("This game is not present to the shopping cart.");
        }
        this.orderService.removeGameFromShoppingCart(this.gameService.findGameByTitle(info[1]));
        System.out.println(info[1] + " removed from cart.");
    }

    private void buyItem(String[] info) {
        List<Game> purchasedGames = this.orderService.purchaseGame();
        if(purchasedGames.isEmpty()){
            throw new GameNotFoundException("No games added to shopping cart");
        }
        System.out.println("Successfully bought games:");
        for(Game games : purchasedGames){
            System.out.println(" -" + games.getTitle());
        }
    }


}
