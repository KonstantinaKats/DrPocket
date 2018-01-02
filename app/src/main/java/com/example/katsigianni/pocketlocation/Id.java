package com.example.katsigianni.pocketlocation;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KonstantinaKats on 26-Nov-17.
 */

public class Id {

    @SerializedName("$oid")
    private String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
