package com.kainattu.stock.api.dto;

public class IncomeStatement {

    private Integer year;
    private Long revenue;
    private Long incomeFromOperation;
    private Long interestPaid;
    private Long netIncome;
    private Double ebs;

    public IncomeStatement(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    public Long getIncomeFromOperation() {
        return incomeFromOperation;
    }

    public void setIncomeFromOperation(Long incomeFromOperation) {
        this.incomeFromOperation = incomeFromOperation;
    }

    public Long getInterestPaid() {
        return interestPaid;
    }

    public void setInterestPaid(Long interestPaid) {
        this.interestPaid = interestPaid;
    }

    public Long getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(Long netIncome) {
        this.netIncome = netIncome;
    }

    public Double getEbs() {
        return ebs;
    }

    public void setEbs(Double ebs) {
        this.ebs = ebs;
    }

    @Override
    public String toString() {
        return "IncomeStatement{" +
                "year=" + (year==-1? "TTM":year) +
                ", revenue=" + revenue +
                ", incomeFromOperation=" + incomeFromOperation +
                ", interestPaid=" + interestPaid +
                ", netIncome=" + netIncome +
                ", ebs=" + ebs +
                '}';
    }
}
