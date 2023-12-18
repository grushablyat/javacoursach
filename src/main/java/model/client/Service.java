package model.client;

public class Service {
    private int id;
    private int request;
    private int master;

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    private String masterName;

    public Service(int id, String masterName) {
        this.id = id;
        this.masterName = masterName;
    }

    public Service(int id, int request, int master, String masterName) {
        this.request = request;
        this.master = master;
        this.id = id;
        this.masterName = masterName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public int getMaster() {
        return master;
    }

    public void setMaster(int master) {
        this.master = master;
    }
}
