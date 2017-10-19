package com.nstyle.Model;

/**
 * Created by mumarm45 on 19/10/2017.
 */
public class DealCountQuery {
    private String fromCurrency;
    private long count;

    public DealCountQuery(){}

    public DealCountQuery(String fromCurrency, long count) {
        this.fromCurrency = fromCurrency;
        this.count = count;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DealCountQuery{" +
                "fromCurrency='" + fromCurrency + '\'' +
                ", count=" + count +
                '}';
    }
}
