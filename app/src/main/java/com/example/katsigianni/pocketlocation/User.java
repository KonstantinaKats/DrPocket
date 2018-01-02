package com.example.katsigianni.pocketlocation;

/**
 * Created by KonstantinaKats on 26-Nov-17.
 */

public class User {

    private Id _id;
    private String name;
    private String surname;
    private String personal_number;
    private String blood;
    private String oxygen;
    private String mainDisease;
    private String specifiedDisease;

    public Id get_id() {
        return _id;
    }

    public void set_id(Id _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPersonal_number() {
        return personal_number;
    }

    public void setPersonal_number(String personal_number) {
        this.personal_number = personal_number;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getOxygen() {
        return oxygen;
    }

    public void setOxygen(String oxygen) {
        this.oxygen = oxygen;
    }

    public String getMainDisease() {
        return mainDisease;
    }

    public void setMainDisease(String mainDisease) {
        this.mainDisease = mainDisease;
    }

    public String getSpecifiedDisease() {
        return specifiedDisease;
    }

    public void setSpecifiedDisease(String specifiedDisease) {
        this.specifiedDisease = specifiedDisease;
    }
}
