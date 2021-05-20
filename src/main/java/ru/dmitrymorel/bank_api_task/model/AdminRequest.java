package ru.dmitrymorel.bank_api_task.model;

public class AdminRequest {

    private int id;

    public AdminRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
