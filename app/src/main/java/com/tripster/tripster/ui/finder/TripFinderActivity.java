package com.tripster.tripster.ui.finder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.tripster.tripster.R;
import com.tripster.tripster.ServletWriter;

public class TripFinderActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_finder);
    }

    public void submitTrip(View view) {
        ServletWriter servletWriter = new ServletWriter(ServletWriter.DEFAULT_SERVLET_URL);

        servletWriter.writeTrackingNumber(getTrackingNumberFromText());
        servletWriter.writePhoneNumber(getPhoneNumberFromText());
    }

    private String getTrackingNumberFromText() {
        EditText trackingNumText = (EditText) findViewById(R.id.tracking_number);
        return trackingNumText.getText().toString();
    }

    private String getPhoneNumberFromText() {
        EditText phoneNumText = (EditText) findViewById(R.id.phone_number);
        return phoneNumText.getText().toString();
    }
}
