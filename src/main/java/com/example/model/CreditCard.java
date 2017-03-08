package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CreditCard {
    private String bankName;
    private String cardNumber;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MMM-yyyy")
    private Date expieryDate;

    public CreditCard(String bankName, String cardNumber, Date expieryDate) {
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.expieryDate = expieryDate;
    }

    public String getBankName() {
        return bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Date getExpieryDate() {
        return expieryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        if (!bankName.equals(that.bankName)) return false;
        if (!cardNumber.equals(that.cardNumber)) return false;
        return expieryDate.equals(that.expieryDate);
    }

    @Override
    public int hashCode() {
        int result = bankName.hashCode();
        result = 31 * result + cardNumber.hashCode();
        result = 31 * result + expieryDate.hashCode();
        return result;
    }
}
