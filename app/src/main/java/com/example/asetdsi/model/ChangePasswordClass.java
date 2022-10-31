
package com.example.asetdsi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordClass {

    @SerializedName("data")
    @Expose
    private ChangePasswordData changePasswordData;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;

    public ChangePasswordData getData() {
        return changePasswordData;
    }

    public void setData(ChangePasswordData changePasswordData) {
        this.changePasswordData = changePasswordData;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
