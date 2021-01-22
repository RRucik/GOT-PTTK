package com.example.gotpttk.model.dbModels;

public class Section {
    Integer idSe;
    Integer idSpStart;
    Integer idSpEnd;
    Integer length;
    Integer pointsTo;
    Integer pointsFrom;
    Integer heightDiff;
    String activeFrom;
    String mountainRange;
    String desc;
    Boolean open;

    public Section()
    {}

    public Section(Integer idSe, Integer idSpStart, Integer idSpEnd, Integer length, Integer pointsTo, Integer pointsFrom, String desc) {
        this.idSe = idSe;
        this.idSpStart = idSpStart;
        this.idSpEnd = idSpEnd;
        this.length = length;
        this.pointsTo = pointsTo;
        this.pointsFrom = pointsFrom;
        this.desc = desc;
        this.open = true;
    }

    public int getIdSe() {
        return idSe;
    }

    public void setIdSe(int idSe) {
        this.idSe = idSe;
    }

    public int getIdSpStart() {
        return idSpStart;
    }

    public void setIdSpStart(int idSpStart) {
        this.idSpStart = idSpStart;
    }

    public int getIdSpEnd() {
        return idSpEnd;
    }

    public void setIdSpEnd(int idSpEnd) {
        this.idSpEnd = idSpEnd;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPointsTo() {
        return pointsTo;
    }

    public void setPointsTo(int pointsTo) {
        this.pointsTo = pointsTo;
    }

    public int getPointsFrom() {
        return pointsFrom;
    }

    public void setPointsFrom(int pointsFrom) {
        this.pointsFrom = pointsFrom;
    }

    public int getHeightDiff() {
        return heightDiff;
    }

    public void setHeightDiff(int heightDiff) {
        this.heightDiff = heightDiff;
    }

    public String getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(String activeFrom) {
        this.activeFrom = activeFrom;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getMountainRange() {
        return mountainRange;
    }

    public void setMountainRange(String mountainRange) {
        this.mountainRange = mountainRange;
    }
}
