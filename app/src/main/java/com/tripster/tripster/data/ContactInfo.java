package com.tripster.tripster.data;

/**
 * @author Aaron Zalewski
 */
public class ContactInfo {

    private String mobileNumber;

    public ContactInfo(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
