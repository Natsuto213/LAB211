package models;

public class Platform {

    private String code;
    private String platform;
    private String description;

    public Platform() {
    }

    public Platform(String code, String platform, String description) {
        this.code = code;
        this.platform = platform;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%-4s | %-8s | %-11s", this.code, this.platform, this.description);
    }
}
