package alex.disco.ball.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Person extends AbstractEntity {

    @Id
    @GeneratedValue
    @Column(name = "person_id", updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;


    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    private List<Product> products;

    public Person(){super();}

    public Person(Long id, String login, String password, String firstName, String lastName, List<Product> products) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.products = products;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getRegistrationDay() {
        return createdAt;
    }

    public void setRegistrationDay(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Person))
            return false;
        if (!super.equals(o))
            return false;
        Person person = (Person) o;
        return id.equals(person.id) && firstName.equals(person.firstName) && lastName.equals(person.lastName);
    }

    @Override
    public String toString() {
        return "Person{" + "login='" + login + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", registrationDay=" + createdAt + '}';
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
