package ua.kiev.prog;


import javax.persistence.*;

@Entity
@Table(name = "Menu")
@NamedQueries({
        @NamedQuery(name = "Menu.All", query = "select a from Food a"),
        @NamedQuery(name = "Menu.Discount", query = "SELECT a FROM Food a WHERE a.discount = true"),
        @NamedQuery(name = "Menu.Price", query = "select a from Food a where a.price >= :min and a.price <= :max "),
        @NamedQuery(name = "Menu.weight", query = "select a from Food a where a.weight <= :max"),
        @NamedQuery(name = "Menu.Id", query = "select a from Food a where a.id = :id")
})
public class Food {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;
    private int price;
    private double weight;
    private boolean discount;

    public Food() {
    }

    public Food(String name, int price, double weight, boolean discount) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", discount=" + discount +
                '}';
    }
}

