package model.master;

import java.sql.Date;

public class Service extends model.Service {
    private int client;
    private String clientName;
    private Date date;
    private String description;
    private int status;
    private String statusName;

    public Service(int request, int master) {
        this.request = request;
        this.master = master;
    }

    public Service(int id, int request, int master) {
        this(request, master);
        this.id = id;
    }

    public Service(int id, int request, int master, int client, String clientName, Date date, String description, String statusName) {
        this.id = id;
        this.request = request;
        this.master = master;
        this.client = client;
        this.clientName = clientName;
        this.date = date;
        this.description = description;
        this.statusName = statusName;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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
}
