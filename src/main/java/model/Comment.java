package model;

public class Comment {
    private int id;
    private int service;
    private String text;

    public Comment(int id, int masterService, String text) {
        this.id = id;
        this.service = masterService;
        this.text = text;
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
}
