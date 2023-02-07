package bg.game.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @Column(name = "mb")
    private float size;

    private String thumbnailURL;
    @Column(nullable = false)
    private BigDecimal price;
    @Column
    private String description;
    @Column(name = "release_date")
    private Date releaseDate;

    public Game(){}

    public Game(String title, String trailerUrl, float size,
                String thumbnailURL, BigDecimal price, String description, Date releaseDate) {
        this.title = title;
        this.trailerUrl = trailerUrl;
        this.size = size;
        this.thumbnailURL = thumbnailURL;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public float getSize() {
        return size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "TrailerUrl: " + trailerUrl + "\n" +
                "Size: " + size +
                "ThumbnailURL: " + thumbnailURL + "\n" +
                "Price: " + price + "\n" +
                "Description='" + description + "\n" +
                "ReleaseDate=" + releaseDate;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}

