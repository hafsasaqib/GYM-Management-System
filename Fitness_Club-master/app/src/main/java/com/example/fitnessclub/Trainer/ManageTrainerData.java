package com.example.fitnessclub.Trainer;

public class ManageTrainerData {
    private String name, phno, email,timeslot,excerciseType;

    public ManageTrainerData(String name, String phno, String email,String timeslot,String excerciseType) {
        this.name = name;
        this.phno = phno;
        this.email = email;
        this.timeslot = timeslot;
        this.excerciseType = excerciseType;
    }

    public ManageTrainerData() {
    }

    public ManageTrainerData(String name, String phno, String email) {
        this.name = name;
        this.phno = phno;
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getExcerciseType() {
        return excerciseType;
    }

    public void setExcerciseType(String excerciseType) {
        this.excerciseType = excerciseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

