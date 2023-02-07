package bg.game.dto;

public class LoginDTO {
    private String email;
    private String password;

    public LoginDTO(String[] info){
        this.setEmail(info[1]);
        this.setPassword(info[2]);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}
