package bg.game.repositories;

import bg.game.entities.Game;
import bg.game.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
     User findByEmailAndPassword(String email,String password);


}
