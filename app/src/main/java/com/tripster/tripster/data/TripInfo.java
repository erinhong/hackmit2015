package com.tripster.tripster.data;

/**
 * @author Aaron Zalewski
 */
public class TripInfo {

    private String trackingNumber;

    public TripInfo(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
