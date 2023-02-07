package bg.game.entities;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,name = "full_name")
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean admin;

    @ManyToMany
    private Set<Game> games;

    @OneToMany(targetEntity = Order.class,mappedBy = "buyer")
    private Set<Order> orders;

    public User() {}

    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.games = new HashSet<>();
        this.orders = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public Set<Game> getGames() {
        return Collections.unmodifiableSet(games);
    }

    public Set<Order> getOrders() {
        return Collections.unmodifiableSet(orders);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

  
}
