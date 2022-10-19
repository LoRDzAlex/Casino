package cloud.stegmann.casino.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Integer id;
    @NotNull
    @Size(min = 3, max = 50, message = "first name should be between 3 and 50 characters")
    private String fname;
    @NotNull
    @Size(min = 3, max = 50, message = "last name should be between 3 and 50 characters")
    private String lname;
    @NotNull
    private Date bday;


    public User() {
    }

    public User(String fname, String lname, Date bday){
        this.fname = fname;
        this.lname = lname;
        this.bday = bday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(fname, user.fname) && Objects.equals(lname, user.lname) && Objects.equals(bday, user.bday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fname, lname, bday);
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
