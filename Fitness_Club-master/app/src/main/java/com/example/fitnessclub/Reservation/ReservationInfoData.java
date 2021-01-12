package com.example.fitnessclub.Reservation;


public class ReservationInfoData {

    private String name, membership, startDate, endDate;
    private int pic;

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public ReservationInfoData(String name, String membership, String startDate, String endDate, int pic) {
        this.name = name;
        this.membership = membership;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pic = pic;

    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
