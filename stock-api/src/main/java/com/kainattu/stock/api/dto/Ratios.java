package com.kainattu.stock.api.dto;

public class Ratios {

    private double priceToBook;
    private double bookValue;
    private double debtToEquity;

    public double getPriceToBook() {
        return priceToBook;
    }

    public double getBookValue() {
        return bookValue;
    }

    public void setBookValue(double bookValue) {
        this.bookValue = bookValue;
    }

    public void setPriceToBook(double priceToBook) {
        this.priceToBook = priceToBook;
    }

    public double getDebtToEquity() {
        return debtToEquity;
    }

    public void setDebtToEquity(double debtToEquity) {
        this.debtToEquity = debtToEquity;
    }


    @Override
    public String toString() {
        return "Ratios{" +
                "priceToBook=" + priceToBook +
                ", bookValue=" + bookValue +
                ", debtToEquity=" + debtToEquity +
                '}';
    }
}
