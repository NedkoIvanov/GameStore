package bg.game.dto;


import bg.game.exceptions.WrongInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterDTO {
    private String email;
    private String password;

    private String confirmPass;
    private String fullName;

    public RegisterDTO(){}

    public RegisterDTO(String[] info){
        this.setEmail(info[1]);
        this.setPassword(info[2]);
        this.setConfirmPass(info[3]);
        this.setFullName(info[4]);
        this.validate();
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getConfirmPass(){
        return confirmPass;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    private void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void validate() {
        if(!this.email.contains("@") || !this.email.contains(".")
           || this.email.indexOf("@")>this.email.indexOf(".")){
            throw new WrongInputException("Wrong email input!");
        }
        String correctPass =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        Pattern pattern = Pattern.compile(correctPass);
        Matcher matcher = pattern.matcher(this.password);
        if(!this.password.equals(this.confirmPass) && !matcher.matches()) {
                throw new WrongInputException("Wrong password input!");
        }
    }




}
