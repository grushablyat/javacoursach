package model;

import java.sql.Date;

public class RequestExtended {
    private int id;
    private int client;
    private Date date;
    private String description;
    private int status;
    private String statusName;

    private String name;

    public RequestExtended(int client, String description) {
        this.client = client;
        this.description = description;
    }

    public RequestExtended(int id, Date date, String description, String statusName) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.statusName = statusName;
    }

    public RequestExtended(int id, int client, Date date, String description, int status, String statusName, String name) {
        this(client, description);
        this.id = id;
        this.date = date;
        this.description = description;
        this.status = status;
        this.statusName = statusName;
        this.name = name;
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
