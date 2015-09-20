package com.tripster.tripster.ui.finder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trip_finder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void submitTrip(View view) {
        EditText trackingNumText = (EditText) findViewById(R.id.editText);
        String trackingNumber = trackingNumText.getText().toString();
        ServletWriter servletWriter = new ServletWriter(ServletWriter.DEFAULT_SERVLET_URL);
        servletWriter.writeTrackingNumber(trackingNumber,this.getApplicationContext());
    }
}
