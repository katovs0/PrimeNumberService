package com.kdb.dao;


public class KdbTable {
    private String sym;
    private String name;
    private long price;

    public KdbTable() {
    }

    public KdbTable(String sym, String name, long price) {
        this.sym = sym;
        this.name = name;
        this.price = price;
    }

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "KdbTable{" +
                "sym='" + sym + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
