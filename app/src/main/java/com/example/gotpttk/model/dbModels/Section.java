package com.example.gotpttk.model.dbModels;

import java.util.Date;

public class Section {
    int IdSe;
    int IdMAFK; // Mountain area foreign key
    int IdSpStart;
    int IdSpEnd;
    int Length;
    int PointsTo;
    int PointsFrom;
    int HeightDiff;
    String ActiveFrom;
    String Desc;
    Boolean Open;

    public Section(){

    }

    public Section(int idSe, int idMAFK, int idSpStart, int idSpEnd, int length, int pointsTo, int pointsFrom, String desc) {
        IdSe = idSe;
        IdMAFK = idMAFK;
        IdSpStart = idSpStart;
        IdSpEnd = idSpEnd;
        Length = length;
        PointsTo = pointsTo;
        PointsFrom = pointsFrom;
        Desc = desc;
        this.Open = true;
    }

    public int getIdSe() {
        return IdSe;
    }

    public void setIdSe(int idSe) {
        IdSe = idSe;
    }

    public int getIdMAFK() {
        return IdMAFK;
    }

    public void setIdMAFK(int idMAFK) {
        IdMAFK = idMAFK;
    }

    public int getIdSpStart() {
        return IdSpStart;
    }

    public void setIdSpStart(int idSpStart) {
        IdSpStart = idSpStart;
    }

    public int getIdSpEnd() {
        return IdSpEnd;
    }

    public void setIdSpEnd(int idSpEnd) {
        IdSpEnd = idSpEnd;
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int length) {
        Length = length;
    }

    public int getPointsTo() {
        return PointsTo;
    }

    public void setPointsTo(int pointsTo) {
        PointsTo = pointsTo;
    }

    public int getPointsFrom() {
        return PointsFrom;
    }

    public void setPointsFrom(int pointsFrom) {
        PointsFrom = pointsFrom;
    }

    public int getHeightDiff() {
        return HeightDiff;
    }

    public void setHeightDiff(int heightDiff) {
        HeightDiff = heightDiff;
    }

    public String getActiveFrom() {
        return ActiveFrom;
    }

    public void setActiveFrom(String activeFrom) {
        ActiveFrom = activeFrom;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public Boolean getOpen() {
        return Open;
    }

    public void setOpen(Boolean open) {
        Open = open;
    }
}
