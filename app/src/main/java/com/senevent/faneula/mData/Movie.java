package com.senevent.faneula.mData;

import org.parceler.Parcel;

/**
 * Created by Oclemmy on 4/12/2016 for ProgrammingWizards Channel.
 */

@Parcel
public class Movie  {

    private String name;
    private String url;
    private String detail;
    private String comment;

    public Movie() {
    }

    public Movie(String name, String url, String detail) {
        this.name = name;
        this.url = url;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String toString() {
        return "myVar: " + name + " | myOtherVar: " + url;
    }
}
