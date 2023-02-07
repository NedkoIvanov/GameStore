package bg.game.services.user;

import bg.game.dto.LoginDTO;
import bg.game.dto.RegisterDTO;
import bg.game.entities.Game;
import bg.game.entities.User;
import bg.game.exceptions.GameNotFoundException;
import bg.game.exceptions.UserLoggException;
import bg.game.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private User loggedUser;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.loggedUser = null;
    }

    @Override
    public User register(RegisterDTO register) {
        if(this.loggedUser!=null){
            throw new UserLoggException("User already logged in the system!");
        }
        User registered = this.mapper.map(register,User.class);
        if(this.userRepository.count()==0){
            registered.setAdmin(true);
        }else{
            registered.setAdmin(false);
        }
        System.out.println(registered.getFullName() + " was registered");
        return this.userRepository.save(registered);

    }

    @Override
    public User login(LoginDTO login) {
        if(this.loggedUser!=null){
            throw new UserLoggException("User already logged in the system!");
        }
        User isRegistered = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
        if(isRegistered!=null){
            this.loggedUser = isRegistered;
        }
        return loggedUser;
    }

    @Override
    public void logOut() {
        if(this.loggedUser!=null) {
            System.out.println("User " + this.loggedUser.getFullName() +" successfully logged out");
            this.loggedUser = null;
        }else{
            throw new UserLoggException("Cannot log out. No user was logged in.");
        }
    }

    @Override
    public User getLoggedUser() {
        if(this.loggedUser!=null) {
            return this.loggedUser;
        }else{
            throw new UserLoggException("No logged user");
        }
    }

    @Override
    public List<Game> ownedGames() {
        if(this.loggedUser==null){
            throw new UserLoggException("There is no logged user to database!");
        }
        List<User> allUsers = this.userRepository.findAll();
        List<Game> games = new ArrayList<>();
        for(int i=0;i<allUsers.size();i++) {
            games.addAll(allUsers.get(i).getGames());
        }
        if(games.isEmpty()){
            throw new GameNotFoundException("No games present to this user!");
        }
        return games;
    }
}
