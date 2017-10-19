package com.nstyle.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by mumarm45 on 16/10/2017.
 */
@Entity
public class Deal {
    @Id
    @Column(unique = true)
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;

    @NotNull
    private String fromCurrency;
    @NotNull
    private String toCurrency;


    @NotNull
    private Timestamp createdDate;

    @NotNull
    private Long amount;

    @NotNull
    private String fileName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
