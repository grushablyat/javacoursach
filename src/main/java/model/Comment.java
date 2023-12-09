package model;

public class Comment {
    private int id;
    private int masterService;
    private String text;

    public Comment(int id, int masterService, String text) {
        this.id = id;
        this.masterService = masterService;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMasterService() {
        return masterService;
    }

    public void setMasterService(int masterService) {
        this.masterService = masterService;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
