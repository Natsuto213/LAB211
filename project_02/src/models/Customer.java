package models;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Customer {

    private String nationalID;
    private String name;
    private Date birthdate;
    private String gender;
    private String phone;
    private String roomID;
    private int rentalDays;
    private Date startDate;
    private String coTenant;

    public Customer() {
    }

    public Customer(String nationalID, String name, Date birthdate, String gender, String phone, String roomID, int rentalDays, Date startDate, String coTenant) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

    public String getCheckOutDate() {
        LocalDate checkIn = this.startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate checkOut = checkIn.plusDays(rentalDays);
        return checkOut.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("%-12s | %-12s | %-8s | %-6s | %-10s | %-6s | %10d | %-9s | %-8s",
                nationalID, name, sdf.format(birthdate), gender, phone, roomID, rentalDays, sdf.format(startDate), coTenant);
    }
}
