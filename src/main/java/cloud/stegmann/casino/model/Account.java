package cloud.stegmann.casino.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;


@Entity
@Table(name = "account")
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Integer id;

    @NotNull
    @Size(min = 5, max = 30, message = "username should be between 5 and 30 characters")
    @Column(unique = true)
    private String username;
    @NotNull
    @Size(min = 8, max = 100, message = "password should have at least 8 characters")
    private String password;
    @NotNull
    @Column(unique = true)
    @Size(min = 5, max = 50, message = "enter valid email address")
    private String email;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "bank_id")
    private @OneToOne Bank bank;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private @ManyToOne User user;

    public Account(String username, String password, String email, Bank bank, User user){
        this.username = username;
        this.password = password;
        this.email = email;
        this.bank = bank;
        this.user = user;
    }

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Account (){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(username, account.username) && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
