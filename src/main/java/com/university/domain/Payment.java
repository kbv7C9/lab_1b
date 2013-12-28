package com.university.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Автор: Касьяненко Борис
 * Entity объекта платеж
 */

@Entity
@Table(name="payments")
public class Payment {

    private long id;  // Идентификатор платежа
    private String payer;  // Человек, совершивший платеж
    private String recipient; // Человек, получивший деньги
    private String number;  // Количество денег в платеже

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    @Column(name="payer")
    public String getPayer(){
        return this.payer;
    }

    public void setPayer(String payer){
        this.payer = payer;
    }

    @Column(name="recipient")
    public String getRecipient(){
        return this.recipient;
    }

    public void setRecipient(String recipient){
        this.payer = recipient;
    }

    @Column(name="number")
    public String getNumber(){
        return this.number;
    }

    public void setNumber(String number){
        this.number = number;
    }
}
