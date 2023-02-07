package bg.game.entities;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //domain-driven design
    @ManyToOne
    private User buyer;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> products;

    public Order() {}

    public Order(User buyer, Set<Game> products) {
        this.buyer = buyer;
        this.products = products;
    }

    public int getId(){
        return id;
    }

    public User getBuyer() {
        return buyer;
    }

    public Set<Game> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public void setProducts(Set<Game> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return products.equals(order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }
}

