package models;

public class UserModelTable {
    String name;
    String email;
    String address;
    String status;

    public UserModelTable(String name, String email, String address, String status) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.status = status;
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

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
