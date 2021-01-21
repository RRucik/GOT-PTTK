package com.example.gotpttk.model.dbModels;

public class Spot {
    int IdSp;
    String Name;
    int Height;
    String Desc;

    public Spot(){

    }

    public Spot(int IdSp, String Name, int Height, String Desc){
        this.IdSp = IdSp;
        this.Name = Name;
        this.Height = Height;
        this.Desc = Desc;
    }

    public int getIdSp() {
        return IdSp;
    }

    public void setIdSp(int idSp) {
        IdSp = idSp;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}
