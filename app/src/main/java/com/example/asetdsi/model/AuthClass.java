
package com.example.asetdsi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthClass {

    @SerializedName("data")
    @Expose
    private AuthData authData;

    public AuthData getData() {
        return authData;
    }

    public void setData(AuthData authData) {
        this.authData = authData;
    }

}
