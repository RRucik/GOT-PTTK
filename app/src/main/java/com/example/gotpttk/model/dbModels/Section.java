package com.example.gotpttk.model.dbModels;

public class Section {
    Integer idSe;
    Integer idSpStart;
    Integer idSpEnd;
    Integer length;
    String mountainRange;
    Integer pointsTo;
    Integer pointsFrom;
    String activeSince;
    String desc;
    Integer heightDiff;
    Boolean open;

    public Section()
    {}

    public Section(Integer idSpStart, Integer idSpEnd, Integer length, String mountainRange, Integer pointsTo, Integer pointsFrom, String activeSince, String desc, Integer heightDiff)
    {
        this.idSe = idSe;
        this.idSpStart = idSpStart;
        this.idSpEnd = idSpEnd;
        this.length = length;
        this.mountainRange = mountainRange;
        this.pointsTo = pointsTo;
        this.pointsFrom = pointsFrom;
        this.activeSince = activeSince;
        this.desc = desc;
        this.heightDiff = heightDiff;
        this.open = true;
    }

    public Section(Integer idSe, Integer idSpStart, Integer idSpEnd, Integer length, String mountainRange, Integer pointsTo, Integer pointsFrom, String activeSince, String desc, Integer heightDiff)
    {
        this.idSe = idSe;
        this.idSpStart = idSpStart;
        this.idSpEnd = idSpEnd;
        this.mountainRange = mountainRange;
        this.length = length;
        this.pointsTo = pointsTo;
        this.pointsFrom = pointsFrom;
        this.heightDiff = heightDiff;
        this.activeSince = activeSince;
        this.desc = desc;
        this.open = true;
    }

    public Integer getIdSe() {
        return idSe;
    }

    public void setIdSe(Integer idSe) {
        this.idSe = idSe;
    }

    public Integer getIdSpStart() {
        return idSpStart;
    }

    public void setIdSpStart(Integer idSpStart) {
        this.idSpStart = idSpStart;
    }

    public Integer getIdSpEnd() {
        return idSpEnd;
    }

    public void setIdSpEnd(Integer idSpEnd) {
        this.idSpEnd = idSpEnd;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getPointsTo() {
        return pointsTo;
    }

    public void setPointsTo(Integer pointsTo) {
        this.pointsTo = pointsTo;
    }

    public Integer getPointsFrom() {
        return pointsFrom;
    }

    public void setPointsFrom(Integer pointsFrom) {
        this.pointsFrom = pointsFrom;
    }

    public Integer getHeightDiff() {
        return heightDiff;
    }

    public void setHeightDiff(Integer heightDiff) {
        this.heightDiff = heightDiff;
    }

    public String getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(String activeSince) {
        this.activeSince = activeSince;
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
