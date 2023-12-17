package model;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.concurrent.locks.ReadWriteLock;

public class Request {
    private int id;
    private int client;
    private Date date;
    private String description;
    private int status;

    public Request(int client, String description) {
        this.client = client;
        this.description = description;
    }
    public Request(int client, String description, int status) {
        this(client, description);
        this.status = status;
    }

    public Request(int client, Date date, String description, int status) {
        this(client, description, status);
        this.date = date;
    }

    public Request(int id, int client, Date date, String description, int status) {
        this(client, date, description, status);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
