package cloud.stegmann.casino.model;

import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "bank")
public class Bank {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Integer id;
    @NotNull
    @Size(min = 3, max = 3, message = "currencyname should have 3 characters")
    private String currencyname;
    @NotNull
    @Size(min = 8, max = 20, message = "enter valid bankcard number")
    private String bankCard;
    @NotNull
    private Double credit;


    public Bank(String currencyname, Double credit, String bankCard){
        this.currencyname = currencyname;
        this.credit = credit;
        this.bankCard = bankCard;
    }
    public Bank(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(id, bank.id) && Objects.equals(currencyname, bank.currencyname) && Objects.equals(bankCard, bank.bankCard) && Objects.equals(credit, bank.credit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currencyname, credit);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrency() {
        return currencyname;
    }

    public void setCurrency(String currencyname) {
        this.currencyname = currencyname;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }


}
