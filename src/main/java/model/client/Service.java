package model.client;

public class Service extends model.Service {

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

    public Service(int id, int request, String masterName) {
        this.request = request;
        this.id = id;
        this.masterName = masterName;
    }

    public Service(int id, int request, int master, String masterName) {
        this.request = request;
        this.master = master;
        this.id = id;
        this.masterName = masterName;
    }
}
