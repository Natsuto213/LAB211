package models;

import java.util.Date;

public class Customer {

    private String nationalID;
    private String name;
    private Date birthdate;
    private String gender;
    private int phone;
    private String roomID;
    private int rentalDays;
    private Date startDate;
    private String coTenant;

    public Customer() {
    }

    public Customer(String nationalID, String name, Date birthdate, String gender, int phone, String roomID, int rentalDays, Date startDate, String coTenant) {
        this.nationalID = nationalID;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phone = phone;
        this.roomID = roomID;
        this.rentalDays = rentalDays;
        this.startDate = startDate;
        this.coTenant = coTenant;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCoTenant() {
        return coTenant;
    }

    public void setCoTenant(String coTenant) {
        this.coTenant = coTenant;
    }

    @Override
    public String toString() {
        return "Customer{" + "nationalID=" + nationalID + ", name=" + name + ", birthdate=" + birthdate + ", gender=" + gender + ", phone=" + phone + ", roomID=" + roomID + ", rentalDays=" + rentalDays + ", startDate=" + startDate + ", coTenant=" + coTenant + '}';
    }
}
