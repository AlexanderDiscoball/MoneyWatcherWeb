package alex.disco.ball.entity;

import alex.disco.ball.util.CategoryConverter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Product extends AbstractEntity {

    @Id
    @GeneratedValue
    @Column(name = "product_id", updatable = false)
    protected Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;
    @Column(nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "person_id", updatable = false)
    private Person person;

    public Product(){super();}

    public Product(Long productid, String name, Category category, Integer price, LocalDate createdAt){
        this(productid,name,category,price,createdAt,createdAt);
    }
    public Product(Long productid, String name, Category category, Integer price, LocalDate createdAt, LocalDate modifiedAt) {
        this(productid,name,category,price,createdAt,modifiedAt,null);
    }

    public Product(Long productid, String name, Category category, Integer price, LocalDate createdAt, LocalDate modifiedAt, Person person){
        this.id = productid;
        this.name = name;
        this.category = category;
        this.price = price;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {this.category = category;}

    public void setCategory(String category) {this.category = Category.valueOf(category);}

    public Long getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", category=" + category + ", price=" + price + ", date=" + createdAt + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Product))
            return false;
        Product product = (Product) o;
        return getId().equals(product.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
