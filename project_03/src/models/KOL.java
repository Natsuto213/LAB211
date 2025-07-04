package models;

public class KOL {

    private String id;
    private String name;
    private String phone;
    private String email;
    private String platformCode;
    private int follow;
    private int rate;

    public KOL() {
    }

    public KOL(String id, String name, String phone, String email, String platformCode, int follow, int rate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.platformCode = platformCode;
        this.follow = follow;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-20s | %-10s | %-8s | %-,9d | %-10d%\n",
                this.id, this.name, this.phone, this.platformCode, this.follow, this.rate);
    }

}
