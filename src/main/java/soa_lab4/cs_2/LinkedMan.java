package soa_lab4.cs_2;

public class LinkedMan{
    private int ID;
    private String name;
    private String address;
    private String phoneNumber;

    public LinkedMan(int ID, String name, String address, String phoneNumber) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public LinkedMan() {
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
