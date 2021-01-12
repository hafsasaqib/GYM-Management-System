package com.example.fitnessclub.Transection;


public class TransectionInfoData {

    private String name, id, status;
    private int pic;

    public int getPic() {
        return pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public TransectionInfoData(String name, String id, String status, int pic) {
        this.name = name;
        this.id = id;
        this.status = status;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

