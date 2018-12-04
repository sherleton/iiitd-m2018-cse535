package com.example.nikhi.project;

public class Medicine {

    private int id;
    private String name, med;
    private int roll, quant;
    public String uname;
    public String oquant;

    public Medicine(int id, String name, String med, int roll, int quant, String uname, String oquant) {
        this.id = id;
        this.name = name;
        this.med = med;
        this.roll = roll;
        this.quant = quant;
        this.uname = uname;
        this.oquant = oquant;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMed() {
        return med;
    }

    public int getRoll() {
        return roll;
    }

    public int getQuant() {
        return quant;
    }
}
