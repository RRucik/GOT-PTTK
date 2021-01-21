package com.example.gotpttk.model.dbModels;

public class MountainArea {
    int IdMA;
    int IdMAFK;
    String Name;

    public MountainArea(){

    }

    public MountainArea(int idMA, String name) {
        IdMA = idMA;
        Name = name;
    }

    public MountainArea(int idMA, int idMAFK, String name) {
        IdMA = idMA;
        IdMAFK = idMAFK;
        Name = name;
    }

    public int getIdMA() {
        return IdMA;
    }

    public void setIdMA(int idMA) {
        IdMA = idMA;
    }

    public int getIdMAFK() {
        return IdMAFK;
    }

    public void setIdMAFK(int idMAFK) {
        IdMAFK = idMAFK;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
