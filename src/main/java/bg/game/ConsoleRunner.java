package bg.game;
import bg.game.services.programUser.ProgramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;



@Controller
public class ConsoleRunner implements CommandLineRunner {

    private ProgramUserService programUserService;

    @Autowired
    public ConsoleRunner(ProgramUserService programUserService) {
        this.programUserService = programUserService;
    }

    @Override
    public void run(String... args) {
       this.programUserService.commandsFromUser();
    }

}
