package bg.game.repositories;

import bg.game.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game,Integer> {
    Game findById(int id);
    Game findByTitle(String title);
}
