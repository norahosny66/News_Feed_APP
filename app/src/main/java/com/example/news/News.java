package com.example.news;

import java.util.List;

public class News {
    String title;
    String SectionID;
    String url;
    String date;
    String pillarName;
    String SectionName;

    public String getSectionName() {
        return SectionName;
    }

    public void setSectionName(String name) {
        SectionName = name;
    }

    public String getSectionID() {
        return SectionID;
    }

    public String getPillarName() {
        return pillarName;
    }

    public void setPillarName(String pillarName) {
        this.pillarName = pillarName;
    }

    public void setSectionID(String sectionID) {
        this.SectionID = sectionID;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
