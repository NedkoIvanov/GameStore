package bg.game.services.game;

import bg.game.dto.GameDTO;
import bg.game.entities.Game;
import bg.game.exceptions.GameNotFoundException;
import bg.game.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper mapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper mapper) {
        this.gameRepository = gameRepository;
        this.mapper = mapper;
    }

    @Override
    public void addGame(GameDTO gameDTO) {
        Game newGames = this.mapper.map(gameDTO,Game.class);
        System.out.println("Added " + newGames.getTitle());
    }

    @Override
    public void editGame(int id,String[] value) throws ParseException {
        Game edit = this.gameRepository.findById(id);
        if(edit==null){
            throw new GameNotFoundException("This game is not in the database.");
        }
            for(int i=0;i<value.length;i++){
                if(value[i].equals("price=")){
                    edit.setPrice(BigDecimal.valueOf(Double.parseDouble(value[i])));
                }else if(value[i].equals("size=")){
                    edit.setSize(Float.parseFloat(value[i]));
                }else if(value[i].equals("title=")){
                    edit.setTitle(value[i]);
                }else if(value[i].equals("trailerUrl=")){
                    edit.setTrailerUrl(value[i]);
                }else if(value[i].equals("thumbnailURL=")){
                    edit.setThumbnailURL(value[i]);
                }else if(value[i].equals("description=")){
                    edit.setDescription(value[i]);
                }else if(value[i].equals("date=")){
                    edit.setReleaseDate(new SimpleDateFormat("dd/MM/yyyy").parse(value[i]));
                }
            }

        System.out.println("Edited " + edit.getTitle());
    }

    @Override
    public void deleteGame(int id) {
        Game toDelete = this.gameRepository.findById(id);
        if(toDelete==null){
            throw new GameNotFoundException("This game cannot be deleted , because it's not present in the database.");
        }
        this.gameRepository.deleteById(id);
        System.out.println("Deleted" + toDelete.getTitle());
    }

    @Override
    public void printAll() {
        List<Game> games = this.gameRepository.findAll();
        if(games.isEmpty()){
            throw new GameNotFoundException("No games present in the database!");
        }
        for(int i=0;i<games.size();i++){
            System.out.println(games.get(i).getTitle() + " " + games.get(i).getPrice());
        }
    }

    @Override
    public void detailGame(String title) {
        Game wanted = this.gameRepository.findByTitle(title);
        if(wanted==null){
            throw new GameNotFoundException("This game is not present to the database!");
        }
        System.out.print(wanted);
    }

    @Override
    public Game findGameByTitle(String title) {
        return this.gameRepository.findByTitle(title);
    }
}
