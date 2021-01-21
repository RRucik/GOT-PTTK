package com.example.gotpttk.model.dbModels;

public class Spot {
    Integer idSp;
    String name;
    Integer height;
    String desc;

    public Spot(){
    }

    public Spot(String name, Integer height, String desc)
    {
        this.name = name;
        this.height = height;
        this.desc = desc;
    }

    public Spot(Integer idSp, String name, Integer height, String desc)
    {
        this.idSp = idSp;
        this.name = name;
        this.height = height;
        this.desc = desc;
    }

    public Integer getIdSp() {
        return idSp;
    }

    public void setIdSp(Integer idSp) {
        this.idSp = idSp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
