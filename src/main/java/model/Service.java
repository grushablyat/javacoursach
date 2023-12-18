package model;

public class Service {
    protected int id;
    protected int request;
    protected int master;

    public Service() {}

    public Service(int request, int master) {
        this.request = request;
        this.master = master;
    }

    public Service(int id, int request, int master) {
        this(request, master);
        this.id = id;
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
