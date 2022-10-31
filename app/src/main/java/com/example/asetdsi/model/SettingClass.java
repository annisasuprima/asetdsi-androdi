
package com.example.asetdsi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SettingClass {

    @SerializedName("data")
    @Expose
    private SettingData settingData;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;

    public SettingData getData() {
        return settingData;
    }

    public void setData(SettingData settingData) {
        this.settingData = settingData;
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
