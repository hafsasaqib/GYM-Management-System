package com.example.fitnessclub.Schedule;


public class ViewSceduleData {

    private String name, time;
    private int pic;

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public ViewSceduleData(String name, String time, int pic) {
        this.name = name;
        this.time = time;
        this.pic = pic;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

