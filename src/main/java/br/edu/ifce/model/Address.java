package br.edu.ifce.model;

public class Address {
    public Integer id = 0;
    public String street;
    public String number;
    public String district;
    public String city;
    public String state;
    public String zipcode;
    public Integer user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUseId(int user_id) {
        this.user_id = user_id;
    }

    public String toString() {
        String str = street + ", " + number + ", " + district + "\n";
        str += city + " - " + state;
        str += "\n CEP: " + zipcode;

        return str;
    }
}
