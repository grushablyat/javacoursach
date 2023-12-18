package model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Comment {
    private int id;
    private int service;
    private String text;
    private Date date;

    public Comment(int service, String text) {
        this.service = service;
        this.text = text;
    }

    public Comment(int id, int service, String text, Date date) {
        this.id = id;
        this.service = service;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
