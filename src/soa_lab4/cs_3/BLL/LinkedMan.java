package soa_lab4.cs_3.BLL;

public class LinkedMan{
    private int ID;
    private String Name;
    private String address;
    private String phoneNumber;

    public LinkedMan(int ID, String name, String phoneNumber) {
        this.ID = ID;
        Name = name;
        this.phoneNumber = phoneNumber;
    }

    public LinkedMan(int ID, String name, String address, String phoneNumber) {
        this.ID = ID;
        Name = name;
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
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
