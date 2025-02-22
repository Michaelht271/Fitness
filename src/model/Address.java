package model;

public class Address {
    private String addressID;
    private String city;
    private String street;
    private int houseNumber;

    // Constructor không tham số
    public Address() {
    }

    /**
     * @param addressID
     * @param city
     * @param street
     * @param houseNumber
     */
    public Address(String addressID, String city, String street, int houseNumber) {
        this.addressID = addressID;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return String.format(" %-15s | %-20s | %-10d   |", city, street, houseNumber);
    }
}
