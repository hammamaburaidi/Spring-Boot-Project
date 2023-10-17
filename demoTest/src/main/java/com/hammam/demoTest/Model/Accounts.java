package com.hammam.demoTest.Model;
import jakarta.persistence.*;

@Entity
@Table
public class Accounts {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    private Long userId;
    private double balance;
    private String currency;

    public Accounts() {
    }

    public Accounts(Long userId, double balance, String currency) {
        this.userId = userId;
        this.balance = balance;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "id=" + id +
                ", userId=" + userId +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }
}
