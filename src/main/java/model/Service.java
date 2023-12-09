package model;

public class Service {
    private int id;
    private int request;
    private int master;

    public Service(int id, int request, int master) {
        this.id = id;
        this.request = request;
        this.master = master;
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
