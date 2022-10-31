
package com.example.asetdsi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditProfileClass {

    @SerializedName("data")
    @Expose
    private EditProfileData editProfileData;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;

    public EditProfileData getData() {
        return editProfileData;
    }

    public void setData(EditProfileData editProfileData) {
        this.editProfileData = editProfileData;
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
