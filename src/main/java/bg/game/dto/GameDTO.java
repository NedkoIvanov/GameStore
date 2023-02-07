package bg.game.dto;

import bg.game.exceptions.WrongInputException;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameDTO {

    private String title;
    private BigDecimal price;
    private float size;
    private String trailerUrl;
    private String thumbnailURL;
    private String description;

    public GameDTO(String[] info){
        setTitle(info[1]);
        setPrice(BigDecimal.valueOf(Double.parseDouble(info[2])));
        setSize(Float.parseFloat(info[3]));
        setTrailerUrl(youtubeId(info[4]));
        setThumbnailURL(info[5]);
        setDescription(info[6]);
        this.validate();
    }


    private void setTitle(String title) {
        this.title = title;
    }

    private void setPrice(BigDecimal price) {
        this.price = price;
    }

    private void setSize(float size) {
        this.size = size;
    }

    private void setTrailerUrl(String trailer) {
        this.trailerUrl = trailer;
    }

    private void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public float getSize() {
        return size;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public String getDescription() {
        return description;
    }
    /*
 o	Title – has to begin with uppercase letter and has length between 3 and 100 symbols (inclusive)
 o	Price –  must be a positive number with precision up to 2 digits after floating point
 o	Size – must be a positive number with precision up to 1 digit after floating point
 o	Trailer –  only videos from YouTube are allowed and only their ID should be saved to the database which is a string of exactly 11 characters.
     For example, if the URL to the trailer is https://www.youtube.com/watch?v=edYCtaNueQY, the required part that must be saved into the database is edYCtaNueQY. That would be always the last 11 characters from the provided URL.
 o	Thumbnail URL – it should be a plain text starting with http://, https:// or null
 o	Description – must be at least 20 symbols

  */
    private void validate() {
        if(!Character.isUpperCase(this.title.toCharArray()[0]) || this.title.length()<3 || this.title.length()>100){
            throw new WrongInputException("Wrong title!");
        }
        if(this.price.doubleValue()<0 || this.price.scale()>2){
            throw new WrongInputException("Wrong price!");
        }
        if(this.size<0 || BigDecimal.valueOf(this.size).scale()>1){
            throw new WrongInputException("Wrong size!");
        }
        if(!this.thumbnailURL.startsWith("http://") || !this.thumbnailURL.startsWith("https://")){
            throw new WrongInputException("Wrong thumbnailURL.");
        }
        if(this.description.length()<20){
            throw new WrongInputException("More symbols needed!At least 20.");
        }

    }

    private String youtubeId(String trailer){
        String id = "";
        String pattern = "https?:\\/\\/(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/" +
                "|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})(?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w]*";

        Pattern compiledPattern = Pattern.compile(pattern,
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(trailer);
        if (matcher.find()) {
            id =  matcher.group(1);
        }else{
            throw new WrongInputException("Wrong url");
        }
        return id;
    }
}
